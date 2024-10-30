package com.example.videojuego;

// Todas las implementaciones necesarias para las diferentes funcionalidades
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class VideojuegoController implements Initializable {
    // Aquí creamos todos los elementos que están en el fxml para que puedan
    // Aparecer en la escena
    @FXML
    Pane panelimagenes;

    @FXML
    AnchorPane panelfondo;

    @FXML
    Label xsalir;

    @FXML
    ImageView fondo;

    @FXML
    ImageView seleccionaragua;

    @FXML
    ImageView seleccionardragon;

    @FXML
    ImageView seleccionarfuego;

    @FXML
    ImageView seleccionarveneno;

    @FXML
    Label tipopelea;

    @FXML
    Label numeroyo;

    @FXML
    Label numerootro;

    @FXML
    ImageView imagenyo;

    @FXML
    Label nombreyo;

    @FXML
    Label fuerzayo;

    @FXML
    Label destrezayo;

    @FXML
    Label suerteyo;

    @FXML
    ImageView corazonyo1;

    @FXML
    ImageView corazonyo2;

    @FXML
    ImageView corazonyo3;

    @FXML
    ImageView corazonotro1;

    @FXML
    ImageView corazonotro2;

    @FXML
    ImageView corazonotro3;

    @FXML
    Label nombreotro;

    @FXML
    ImageView imagenotro;

    @FXML
    Label fuerzaotro;

    @FXML
    Label destrezaotro;

    @FXML
    Label suerteotro;

    @FXML
    Label labelPelea;

    @FXML
    Label resolucion;

    @FXML
    Label reiniciar;

    @FXML
    ImageView gif1;

    @FXML
    ImageView gif2;

    /**Creo diferentes variables para ir almacenando cosas que me hagan falta
     * Para empezar éstos dos booleanos que se usarán para comprobar si ya se
     * ha creado el personaje 1 para saltar al 2, y el 2 para bloquear la acción
     * de seleccionar*/
    private boolean primerPersonajeSeleccionado = false, segundoPersonajeSeleccionado = false;
    // Una lista que almacena los tipos de peleas
    private final String[] atributoPelea = {"Fuerza", "Destreza", "Suerte"};
    // Los dos primeros se utilizarán para almacenar los numeros random de las peleas
    // Y los otros dos para almacenar las vidas tanto mias como las del enemigo
    private int random1, random2, vidasYo = 3, vidasOtro = 3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Desactivamos los dos labels para que no se pueda hacer nada antes
        labelPelea.setDisable(true);resolucion.setDisable(true);
        // Hacemos invisible los numeros que tiene cada uno porque todavia
        // No los vamos a usar
        numeroyo.setVisible(false);numerootro.setVisible(false);tipopelea.setVisible(false);
        // Los hacemos
        gif1.setVisible(false);gif2.setVisible(false);
        labelpelea();
        labelResolucion();
        xSalir();
        seleccionarPersonajes();
        reiniciar();
    }

    private void seleccionarPersonajes(){
        // Éste método le da un evento a cada imagen de selección que llama al método de crear los personajes
        // Cada evento crea un personaje
        seleccionaragua.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            try {
                seleccionar(1);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        seleccionardragon.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            try {
                seleccionar(2);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        seleccionarfuego.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            try {
                seleccionar(3);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));

        seleccionarveneno.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            try {
                seleccionar(4);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }));
    }

    // Éste método lo que hace es crear los personajes, solamente hay que mandarle un entero
    // Que cada uno de los enteros es un personaje diferente
    private void seleccionar(int personaje) throws IOException {
        // éste condicional comprueba si ya existen ya los 2 personajes
        if (primerPersonajeSeleccionado && segundoPersonajeSeleccionado) {
            return;
        }

        // Aquí lo que hace es darle la imagen del usuario según el número del evento
        URL image = VideojuegoController.class.getResource("/com/example/videojuego/" + personaje + ".jpeg");
        Image imagen = new Image(image.toExternalForm());
        // Creamos la variable seleccionado que será una instancia de la clase personaje
        Personaje seleccionado = null;

        // Aquí según el el personaje que se haya seleccionado y se le ponen unos valores
        switch (personaje) {
            case 1:
                seleccionado = new Personaje("Agua", 50, 30, 10, Color.BLUE, image.toString());
                break;
            case 2:
                seleccionado = new Personaje("Dragón", 35, 25, 20, Color.CHOCOLATE, image.toString());
                break;
            case 3:
                seleccionado = new Personaje("Fuego", 25, 20, 45, Color.ORANGERED, image.toString());
                break;
            case 4:
                seleccionado = new Personaje("Veneno", 20, 40, 25, Color.GREEN, image.toString());
                break;
            default:
                throw new IllegalArgumentException("Personaje no válido: " + personaje);
        }

        /**
         * Aquí hacemos un if para comprobar si existe un primer personaje, en caso de no haber ninguno creado,
         * Al primero le pone primero la imagen, luego los valores de nombre, fuerza, destreza y suerte, además
         * En todos esos textos les ponemos el color que le puse al crear la instancia del personaje
         * Y cambiamor a true el boleano de si está seleccionado el primer personaje, y luego, en caso de que
         * Elijas a otro personaje, hace lo mismo con el otro personaje
         */
        if (!primerPersonajeSeleccionado) {
            imagenyo.setImage(imagen);
            nombreyo.setText(seleccionado.getNombre());
            nombreyo.setTextFill(seleccionado.getColor());
            fuerzayo.setText(String.valueOf(seleccionado.getFuerza()));
            fuerzayo.setTextFill(seleccionado.getColor());
            destrezayo.setText(String.valueOf(seleccionado.getDestreza()));
            destrezayo.setTextFill(seleccionado.getColor());
            suerteyo.setText(String.valueOf(seleccionado.getSuerte()));
            suerteyo.setTextFill(seleccionado.getColor());
            numeroyo.setTextFill(seleccionado.getColor());
            primerPersonajeSeleccionado = true;
        } else if (!segundoPersonajeSeleccionado) {
            imagenotro.setImage(imagen);
            nombreotro.setText(seleccionado.getNombre());
            nombreotro.setTextFill(seleccionado.getColor());
            fuerzaotro.setText(String.valueOf(seleccionado.getFuerza()));
            fuerzaotro.setTextFill(seleccionado.getColor());
            destrezaotro.setText(String.valueOf(seleccionado.getDestreza()));
            destrezaotro.setTextFill(seleccionado.getColor());
            suerteotro.setText(String.valueOf(seleccionado.getSuerte()));
            suerteotro.setTextFill(seleccionado.getColor());
            numerootro.setTextFill(seleccionado.getColor());
            segundoPersonajeSeleccionado = true;
            panelimagenes.setVisible(false);
            labelPelea.setDisable(false);
        }
    }

    // Éste es simplemente un metodo que contiene los 3 eventos del label de la X superior
    // El primero cierra la ventana y el segundo y tercero le da un efecto de hover al entrar
    // Con el ratón en el label
    private void xSalir(){
        xsalir.addEventHandler(MouseEvent.MOUSE_PRESSED, (event) -> {
            xsalir.getScene().getWindow().hide();
        });

        xsalir.addEventHandler(MouseEvent.MOUSE_ENTERED, (event) -> {
            xsalir.setTextFill(Color.WHITE);
            xsalir.setStyle("-fx-background-color: red");
        });

        xsalir.addEventHandler(MouseEvent.MOUSE_EXITED, (event) -> {
            xsalir.setTextFill(Color.RED);
            xsalir.setStyle("-fx-background-color: grey");
        });
    }

    // Éste método es lo mismo que el de arriba, para llamar al método de pelea (creado justo debajo) y
    // Otros dos para darle el efecto hover
    private void labelpelea(){
        labelPelea.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            pelear();
        }));

        labelPelea.addEventHandler(MouseEvent.MOUSE_ENTERED, (event -> {
            labelPelea.setStyle("-fx-opacity: 0.75");

        }));

        labelPelea.addEventHandler(MouseEvent.MOUSE_EXITED, (event -> {
            labelPelea.setStyle("-fx-opacity: 1");
        }));
    }

    /** Éste método hace visible el label de tipo pelea y tira un número random del 0 al 2
     *  Y ese número lo usa para tomar un valor del array que tenga dicha posicion
     *  Además desactiva el label y activa el de la resolucion */
    private void pelear(){
        tipopelea.setVisible(true);
        Random rn = new Random();
        int numRandom = rn.nextInt(atributoPelea.length);
        String seleccionado = atributoPelea[numRandom];
        tipopelea.setText("Pelea por: " + seleccionado);
        labelPelea.setDisable(true);
        labelPelea.setStyle("-fx-opacity: 0.50");
        resolucion.setDisable(false);
    }

    // Éste llama a la función que creamos debajo con el primer evento y los otros dos
    // Son para darle efecto hover
    private void labelResolucion(){
        resolucion.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            solucion();
        }));
        resolucion.addEventHandler(MouseEvent.MOUSE_ENTERED, (event -> {
            resolucion.setStyle("-fx-opacity: 0.75");
        }));
        resolucion.addEventHandler(MouseEvent.MOUSE_EXITED, (event -> {
            resolucion.setStyle("-fx-opacity: 1");
        }));
    }

    // Éste método es el que hace la pelea
    private void solucion(){
        // Comienza por generar números random del 1 al 100  y los almacena en una variable
        Random rn = new Random();
        random1 = rn.nextInt(100) + 1;
        numeroyo.setVisible(true);
        numeroyo.setText(String.valueOf(random1));

        random2 = rn.nextInt(100) + 1;
        numerootro.setVisible(true);
        numerootro.setText(String.valueOf(random2));

        // Éste pequeño apartado, toma el String completo del tipo pelea, deshecha la parte de
        // Pelea por:  y almacena el resto, que es el tipo de pelea y lo almacena en la variable
        String atributoSeleccionado = tipopelea.getText().replace("Pelea por: ", "");

        // Éstas dos variables comienzan por 0, son donde se almacenarán los valores totales de la pelea
        // (numero random + valor del tipo de pelea)
        int valorYo = 0;
        int valorOtro = 0;

        // Éste switch toma el tipo de pelea, busca el numero que tiene cada personaje en esa variable, la
        // Suma al número random y los almacena en las dos variables de arriba

        switch (atributoSeleccionado){
            case "Fuerza":
                valorYo = Integer.parseInt(fuerzayo.getText()) + random1;
                valorOtro = Integer.parseInt(fuerzaotro.getText()) + random2;
                break;
            case "Destreza":
                valorYo = Integer.parseInt(destrezayo.getText()) + random1;
                valorOtro = Integer.parseInt(destrezaotro.getText()) + random2;
                break;
            case "Suerte":
                valorYo = Integer.parseInt(suerteyo.getText()) + random1;
                valorOtro = Integer.parseInt(suerteotro.getText()) + random2;
                break;
            default:
                throw new IllegalArgumentException("Atributo no válido" + atributoSeleccionado);
        }

        // Éste if comprueba los valores y el que sea más pequeño le resta una vida y llama al metodo
        // De actualizar corazones que está abajo
        if (valorYo < valorOtro) {
            vidasOtro--;
            actualizarCorazones(vidasOtro, true);
        } else if (valorOtro < valorYo) {
            vidasYo--;
            actualizarCorazones(vidasYo, false);
        }

        // En caso de que algún personaje llegue a 0 vidas llama al método que acaba el juego
        if (vidasYo == 0 || vidasOtro == 0) {
            finDelJuego();
        }
        resolucion.setDisable(true);
    }

    // Éste metodo actualiza los corazones
    private void actualizarCorazones(int vidas, boolean yo){
        /** El metodo mira si has perdido la vida tu o el otro (por parámetros)
        * Y tiene unos ifs anidados donde el que haya perdido alguna vida se le reinician los corazones
        * Y según la cantidad de vidas que le quede a ese mismo, hace visibles una cantidad de imagenes u otra
        */
        if (yo) {
            corazonyo1.setVisible(false);
            corazonyo2.setVisible(false);
            corazonyo3.setVisible(false);

            if (vidas > 0) corazonyo1.setVisible(true);
            if (vidas > 1) corazonyo2.setVisible(true);
            if (vidas > 2) corazonyo3.setVisible(true);
        } else {
            corazonotro1.setVisible(false);
            corazonotro2.setVisible(false);
            corazonotro3.setVisible(false);

            if (vidas > 0) corazonotro1.setVisible(true);
            if (vidas > 1) corazonotro2.setVisible(true);
            if (vidas > 2) corazonotro3.setVisible(true);
        }
        labelPelea.setDisable(false);
    }

    // Éste método está hecho para dar feedback de quien ha ganado
    public void finDelJuego(){
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        // Empezamos creando una alerta y haciendo un if para saber quien
        // Tiene más vidas que el otro, en caso de que tu tengas mas
        // Vidas te dice que has ganado, y sinó te dice que has perdido
        if (vidasYo < vidasOtro) {
            alerta.setTitle("Victoria");
            alerta.setContentText("¿¿Has ganado!!");
        }
        else {
            alerta.setTitle("Derrota");
            alerta.setContentText("¡Ohhh! \nA la proxima te irá mejor");
        }
        // además desactiva todos los labes excepto el de salir y el de reiniciar
        resolucion.setDisable(true);
        labelPelea.setDisable(true);
        // Hace visibles dos gifs que señalan a los labels de salir y reiniciar
        gif1.setVisible(true);
        gif2.setVisible(true);
        //Muestra la alerta
        alerta.showAndWait();
    }

    // Éste método reinicia la ventana del juego
    private void reiniciar() {
        reiniciar.addEventHandler(MouseEvent.MOUSE_CLICKED, (event -> {
            Stage escena = (Stage) reiniciar.getScene().getWindow();
            escena.close();
            Platform.runLater(() -> {
                try {
                    new VideojuegoApplication().start(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }));
    }
}