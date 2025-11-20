package proyect.bankproject.Controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import proyect.bankproject.Model.Users.User;
import proyect.bankproject.Model.Users.Client;
import proyect.bankproject.Model.Users.Cashier;
import proyect.bankproject.Model.Users.Administrator;


import java.io.IOException;
import java.net.URL;

public class ViewManager {

    public void showDashboard(Stage currentStage, User user) {
        String role = user.getRole();
        String fxmlFile = "";

        switch (role) {
            case "Client":
                fxmlFile = "client-view.fxml";
                break;
            case "Cashier":
                fxmlFile = "cashier-view.fxml";
                break;
            case "Administrator":
                fxmlFile = "admin-view.fxml";
                break;
            default:
                System.err.println("Rol desconocido, no se puede cargar dashboard: " + role);
                return;
        }

        try {

            URL fxmlLocation = getClass().getResource("/" + fxmlFile);

            if (fxmlLocation == null) {

                fxmlLocation = getClass().getResource("/proyect/bankproject/View/" + fxmlFile);
                if (fxmlLocation == null) {
                    System.err.println("No se pudo encontrar el FXML en la raíz (/) ni en /proyect/bankproject/View/: " + fxmlFile);
                    return;
                }
            }

            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();

            Object controller = loader.getController();

            if (controller instanceof ClientViewController) {
                ((ClientViewController) controller).initData((Client) user);

            } else if (controller instanceof CashierViewController) {
                ((CashierViewController) controller).initData((Cashier) user);

            } else if (controller instanceof AdminViewController) {
                ((AdminViewController) controller).initData((Administrator) user);
            }

            Scene newScene = new Scene(root);
            currentStage.setScene(newScene);
            currentStage.setTitle(role + " Dashboard");
            currentStage.setWidth(1000);
            currentStage.setHeight(700);
            currentStage.setResizable(true);
            currentStage.centerOnScreen();

        } catch (IOException e) {
            System.err.println("Error al cargar la nueva escena: " + fxmlFile);
            e.printStackTrace();
        }
    }
}