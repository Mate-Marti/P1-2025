package co.edu.uniquindio.poo.preparcial3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.Parent;

import co.edu.uniquindio.poo.preparcial3.model.Repositorio;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Repositorio.getInstancia().cargarDatos();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/poo/preparcial3/View.fxml"));
        Parent root = loader.load();
        stage.setTitle("Tienda");
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}