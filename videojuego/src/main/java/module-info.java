module com.example.videojuego {
    requires java.desktop;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.videojuego to javafx.fxml;
    exports com.example.videojuego;
}