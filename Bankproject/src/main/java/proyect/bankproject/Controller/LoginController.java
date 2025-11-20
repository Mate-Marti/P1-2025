package proyect.bankproject.Controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import proyect.bankproject.Model.Users.User;
import proyect.bankproject.Service.LoginService;
import proyect.bankproject.Exceptions.InvalidPasswordException;
import proyect.bankproject.Exceptions.UserNotFoundException;

public class LoginController {
    @FXML
    private VBox rootPane;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;
    private final LoginService loginService;
    private final ViewManager viewManager;


    public LoginController() {
        this.loginService = new LoginService();
        this.viewManager = new ViewManager();
    }

    @FXML
    private void initialize() {
        errorLabel.setText("");
    }

    @FXML
    private void onLoginButtonClick() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isBlank() || password.isBlank()) {
            errorLabel.setText("Por favor, ingrese usuario y contraseña.");
            return;
        }

        try {
            User loggedInUser = loginService.validateUser(username, password);
            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            viewManager.showDashboard(currentStage, loggedInUser);

        } catch (UserNotFoundException | InvalidPasswordException e) {
            errorLabel.setText(e.getMessage());
        } catch (Exception e) {
            errorLabel.setText("Error inesperado del sistema: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
