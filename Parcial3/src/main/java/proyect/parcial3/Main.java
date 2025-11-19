package proyect.parcial3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import proyect.parcial3.Model.ClinicData;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/proyect/parcial3/View.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(true);
            primaryStage.show();
            System.out.println("Iniciando sistema...");
            ClinicData.getInstance();

        } catch (Exception exception) {
            System.out.println("Error fatal al iniciar la aplicación:");
            exception.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
