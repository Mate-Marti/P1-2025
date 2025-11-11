module co.edu.uniquindio.poo.preparcial3 {
    requires javafx.controls;
    requires javafx.fxml;

    opens co.edu.uniquindio.poo.preparcial3.viewController to javafx.fxml;
    exports co.edu.uniquindio.poo.preparcial3;
    opens co.edu.uniquindio.poo.preparcial3 to javafx.fxml;
    opens co.edu.uniquindio.poo.preparcial3.model to javafx.base;

}