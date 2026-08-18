package com.crud.crud;

import com.crud.crud.Controlador.Controlador;
import com.crud.crud.GUI.AgendaPersonas;
import com.crud.crud.GUI.PanelControles;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        AgendaPersonas ap = new AgendaPersonas();
        PanelControles pc = new PanelControles();

        BorderPane root = new BorderPane();
        root.setCenter(ap.getTablaPersonas());
        root.setRight(pc);

        Controlador controlador = new Controlador(ap,pc);

        Scene scene = new Scene(root, 900, 500);


        /**
         * el archivo excel-style.css fue prompteado en su totalidad.
         */
        scene.getStylesheets().add(getClass().getResource("/com/crud/crud/excel-style.css").toExternalForm());
        stage.setTitle("CRUD by Gonzalo Pimienta");
        stage.setScene(scene);
        stage.show();
    }
}
