package com.example.videojuego;
import javafx.scene.paint.Color;

public class Personaje {
    // Creo el constructor del objeto personaje para poder darle unos atributos diferentes
    // a cada uno de los que se cree
    private String nombre;
    private int fuerza;
    private int destreza;
    private int suerte;
    private Color color;
    private String imagen;

    public Personaje(String nombre, int fuerza, int destreza, int suerte, Color color, String imagen) {
        this.nombre = nombre;
        this.fuerza = fuerza;
        this.destreza = destreza;
        this.suerte = suerte;
        this.color = color;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public int getFuerza() {
        return fuerza;
    }

    public int getDestreza() {
        return destreza;
    }

    public int getSuerte() {
        return suerte;
    }

    public Color getColor() {
        return color;
    }
}