module co.edu.uniquindio.poo.pfbanco {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    exports co.edu.uniquindio.poo.pfbanco;
    opens co.edu.uniquindio.poo.pfbanco to javafx.fxml;
    opens co.edu.uniquindio.poo.pfbanco.viewController to javafx.fxml;
    opens co.edu.uniquindio.poo.pfbanco.model to javafx.base;

}