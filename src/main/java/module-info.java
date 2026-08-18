module com.crud.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.base;
    requires org.mariadb.jdbc;

    opens com.crud.crud to javafx.fxml;
    opens com.crud.crud.Tablas to javafx.base;
    exports com.crud.crud;
}