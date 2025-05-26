package co.edu.uniquindio.poo.biblioteca;

import co.edu.uniquindio.poo.biblioteca.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import co.edu.uniquindio.poo.biblioteca.model.Biblioteca;
import co.edu.uniquindio.poo.biblioteca.viewController.BibliotecaViewController;

/**
 * JavaFX App
 */
public class App extends Application {

    private Stage primaryStage;
    private static Libro LibroDigital;
    public static Biblioteca biblioteca = new Biblioteca("UQ", 123, LibroDigital);

    Libro libro = new LibroDigital("el principito", "yo", "n", "12");

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Gestion de Clientes");
        openViewPrincipal();
    }
    /**
     * JavaFX App abrir ventana principal
     */

    private void openViewPrincipal() {
        inicializarData();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/co/edu/uniquindio/poo/biblioteca/crudBiblioteca.fxml"));
            AnchorPane rootLayout = loader.load();
            BibliotecaViewController BibliotecaViewController = loader.getController();
            BibliotecaViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
    /**
     * JavaFX App abrir ventana principal
     */
    public void openCrudCliente() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("/co/edu/uniquindio/poo/biblioteca/crudBiblioteca.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            BibliotecaViewController BibliotecaViewController = loader.getController();
            BibliotecaViewController.setApp(this);

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    //servicios

    public void inicializarData(){
        Biblioteca biblioteca = new Biblioteca("12233", 123, LibroDigital);
        biblioteca.agregarLibro(LibroDigital);
    }
}

