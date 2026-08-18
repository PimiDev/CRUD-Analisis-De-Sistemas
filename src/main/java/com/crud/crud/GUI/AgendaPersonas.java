package com.crud.crud.GUI;

import com.crud.crud.AgendaDB;
import com.crud.crud.Tablas.Persona;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;

public class AgendaPersonas extends BorderPane {

    private TableView<Persona> tablaPersonas;

    public AgendaPersonas(){
        tablaPersonas = new TableView<>();

        TableColumn<Persona, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));


        TableColumn<Persona, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Persona, String> colDireccion = new TableColumn<>("Dirección");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        tablaPersonas.getColumns().addAll(colId, colNombre, colDireccion);
    }

    public void setListaPersonas(ObservableList<Persona> lista) {
        this.tablaPersonas.setItems(lista);
    }

    public Persona getPersonaSeleccionada() {
        return tablaPersonas.getSelectionModel().getSelectedItem();
    }
    public TableView<Persona> getTablaPersonas() {
        return tablaPersonas;
    }
}
