package proyect.bankproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import proyect.bankproject.Service.BankService;

import java.io.IOException;
import java.net.URL;

public class BankApp extends Application {

    @Override
    public void start(Stage stage) {

        try {
            BankService.getInstance().initializeData();
        } catch (Exception e) {
            System.err.println("Error fatal al inicializar los datos de la aplicación.");
            e.printStackTrace();
            return;
        }

        try {
            URL fxmlLocation = getClass().getResource("/login-view.fxml");

            if (fxmlLocation == null) {
                System.err.println("No se pudo encontrar el FXML: login-view.fxml");
                return;
            }

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlLocation);

            Scene scene = new Scene(fxmlLoader.load(), 500, 400);

            stage.setTitle("Proyecto Banco");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (IOException e) {
            System.err.println("Error al cargar la escena de login.");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch();
    }
}
