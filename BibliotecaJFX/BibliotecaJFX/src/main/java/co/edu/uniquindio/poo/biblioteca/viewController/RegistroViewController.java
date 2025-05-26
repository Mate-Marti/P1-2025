package co.edu.uniquindio.poo.biblioteca.viewController;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class RegistroViewController {

    @FXML private Button BtbVolver;
    @FXML private Button BtbEnviar;
    @FXML private TextField TxfAsunto;
    @FXML private TextArea TxfMensaje;
    @FXML private TextField TxfCorreo;


    @FXML void OnEnviar(ActionEvent event) {
        String correo = TxfCorreo.getText().trim();
        String asunto = TxfAsunto.getText().trim();
        String mensaje = TxfMensaje.getText().trim();

        if (correo.isEmpty() || asunto.isEmpty() || mensaje.isEmpty()) {
            mostrarAlerta(Alert.AlertType.WARNING, "Campos Vacíos", "Por favor complete todos los campos.");
            return;
        }

        try {
            enviarCorreoSMTP(correo, asunto, mensaje);
            mostrarAlerta(Alert.AlertType.INFORMATION, "Correo Enviado", "El mensaje fue enviado correctamente.");
        } catch (Exception e) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error", "No se pudo enviar el correo: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void enviarCorreoSMTP(String destinatario, String asunto, String contenido) throws MessagingException {
        final String remitente = "santiago.giraldog1@uqvirtual.edu.co"; // Cambia esto
        final String clave = "gsps yuer ljer qztc";        // Contraseña de aplicación (no la normal)

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session sesion = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);
            }
        });

        Message mensaje = new MimeMessage(sesion);
        mensaje.setFrom(new InternetAddress(remitente));
        mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
        mensaje.setSubject(asunto);
        mensaje.setText(contenido);

        Transport.send(mensaje);
    }
    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String contenido) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
    @FXML private void OnVolver(ActionEvent event) {
        cambiarVista("/co/edu/uniquindio/poo/biblioteca/crudBiblioteca.fxml", event);
    };

    @FXML
    private void cambiarVista(String rutaFXML, ActionEvent event) {
        try {
            URL fxmlLocation = getClass().getResource(rutaFXML);
            if (fxmlLocation == null) {
                System.err.println("No se encontró el archivo FXML en la ruta: " + rutaFXML);
                return;
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Parent root = loader.load();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
