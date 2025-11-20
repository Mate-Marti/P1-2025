module proyect.bankproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens proyect.bankproject.Controller to javafx.fxml;

    opens proyect.bankproject.Model.Users to javafx.base;
    opens proyect.bankproject.Model.Accounts to javafx.base;
    opens proyect.bankproject.Model.Transactions to javafx.base;

    exports proyect.bankproject;
    exports proyect.bankproject.Controller;
}