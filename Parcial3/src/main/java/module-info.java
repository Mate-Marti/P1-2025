module proyect.parcial3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens proyect.parcial3.ViewController to javafx.fxml;
    opens proyect.parcial3 to javafx.fxml;
    exports proyect.parcial3;
    exports proyect.parcial3.ViewController;
    opens proyect.parcial3.Model to javafx.base;
    
}