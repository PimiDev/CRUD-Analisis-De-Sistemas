package com.crud.crud.GUI;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PanelControles extends VBox {

    private TextField txtNombre;
    private TextField txtDireccion;
    private TextField txtTelefono;

    private Button btnAlta;
    private Button btnBaja;
    private Button btnModificarNombre;
    private Button btnModificarDireccion;

    private Button btnAgregarTelefono;
    private Button btnEliminarTelefono;

    private ListView<String> listaTelefonosView;

    public PanelControles() {
        setSpacing(10);
        setPadding(new Insets(10));

        // Campos de texto para Persona
        txtNombre = new TextField();
        txtNombre.setPromptText("Nombre");

        txtDireccion = new TextField();
        txtDireccion.setPromptText("Dirección");

        // Botones de Persona
        btnAlta = new Button("Dar Alta");
        btnBaja = new Button("Dar Baja");
        btnModificarNombre = new Button("Modificar Nombre");
        btnModificarDireccion = new Button("Modificar Dirección");

        HBox filaBotonesPersona = new HBox(10, btnAlta, btnBaja, btnModificarNombre, btnModificarDireccion);

        // Sección de Teléfonos
        txtTelefono = new TextField();
        txtTelefono.setPromptText("Número de Teléfono");

        btnAgregarTelefono = new Button("Agregar Teléfono");
        btnEliminarTelefono = new Button("Eliminar Teléfono");

        HBox filaBotonesTelefono = new HBox(10, btnAgregarTelefono, btnEliminarTelefono);

        listaTelefonosView = new ListView<>();
        listaTelefonosView.setPrefHeight(100);

        // Agregar todos los controles al VBox principal
        getChildren().addAll(
                new Label("Datos de Persona:"),
                txtNombre,
                txtDireccion,
                filaBotonesPersona,
                new Label("Gestión de Teléfonos:"),
                txtTelefono,
                filaBotonesTelefono,
                new Label("Teléfonos de la persona seleccionada:"),
                listaTelefonosView
        );
    }


    public String getNombre() { return txtNombre.getText(); }
    public String getDireccion() { return txtDireccion.getText(); }
    public String getTelefono() { return txtTelefono.getText(); }

    public void setNombre(String nombre) { txtNombre.setText(nombre); }
    public void setDireccion(String direccion) { txtDireccion.setText(direccion); }
    public void setTelefono(String telefono) { txtTelefono.setText(telefono); }

    public Button getBtnAlta() { return btnAlta; }
    public Button getBtnBaja() { return btnBaja; }
    public Button getBtnModificarNombre() { return btnModificarNombre; }
    public Button getBtnModificarDireccion() { return btnModificarDireccion; }
    public Button getBtnAgregarTelefono() { return btnAgregarTelefono; }
    public Button getBtnEliminarTelefono() { return btnEliminarTelefono; }

    public ListView<String> getListaTelefonosView() { return listaTelefonosView; }
    public String getTelefonoSeleccionado() { return listaTelefonosView.getSelectionModel().getSelectedItem(); }
}