module co.edu.uniquindio.poo.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.demo to javafx.fxml;
    exports co.edu.uniquindio.poo.demo;
}