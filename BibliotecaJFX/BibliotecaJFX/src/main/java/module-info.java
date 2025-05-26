module co.edu.uniquindio.poo.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.mail;

    opens co.edu.uniquindio.poo.biblioteca to javafx.fxml;
    exports co.edu.uniquindio.poo.biblioteca;
    exports co.edu.uniquindio.poo.biblioteca.viewController;
    opens co.edu.uniquindio.poo.biblioteca.viewController to javafx.fxml;
    opens co.edu.uniquindio.poo.biblioteca.model to javafx.base;
    exports co.edu.uniquindio.poo.biblioteca.model to javafx.base;
}