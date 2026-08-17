package com.crud.crud;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.lang.classfile.constantpool.PackageEntry;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {


        HBox prueba = new HBox();

        Scene scene = new Scene(prueba, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
