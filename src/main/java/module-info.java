module com.crud.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.crud.crud to javafx.fxml;
    exports com.crud.crud;
}