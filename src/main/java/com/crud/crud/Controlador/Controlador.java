package com.crud.crud.Controlador;

import com.crud.crud.GUI.AgendaPersonas;
import com.crud.crud.GUI.PanelControles;
import com.crud.crud.Tablas.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

public class Controlador {

    private ObservableList<Persona> listaPersonas;
    private ObservableList<String> listaTelefonos;

    private ControladorDB controladorDB;

    private AgendaPersonas agendaPersonas;
    private PanelControles panelControles;

    public Controlador(AgendaPersonas ap, PanelControles pc){
       controladorDB = new ControladorDB();
       agendaPersonas = ap;
       panelControles = pc;

       //listas logicas
        listaPersonas = FXCollections.observableArrayList(controladorDB.getPersonas());
        listaTelefonos = FXCollections.observableArrayList();

        //listas visuales
        agendaPersonas.setListaPersonas(listaPersonas);
        panelControles.getListaTelefonosView().setItems(listaTelefonos);

        configurarEventos();
    }

    /**
     * Dar de alta y baja
     */
    public void agregarPersona(Persona persona){
        controladorDB.darAlta(persona);
        listaPersonas.add(persona);
    }

    public void eliminarPersona(){
        Persona personaSeleccionada = agendaPersonas.getPersonaSeleccionada();

        if (personaSeleccionada != null) {
            controladorDB.darBaja(personaSeleccionada);
            listaPersonas.remove(personaSeleccionada);
        } else {
            System.out.println("No ha seleccionado a nadie");
        }
    }

    /**
     * Configuración acciones
     */
    private void configurarEventos() {

        // seleccionar una fila en la TableView
        agendaPersonas.getTablaPersonas().getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, personaSeleccionada) -> {
            if (personaSeleccionada != null) {
                panelControles.setNombre(personaSeleccionada.getNombre());
                panelControles.setDireccion(personaSeleccionada.getDireccion());
                cargarTelefonos(personaSeleccionada);
            }
        });

        //DAR ALTA
        panelControles.getBtnAlta().setOnAction(e -> {
            String nombre = panelControles.getNombre();
            String direccion = panelControles.getDireccion();

            if (!nombre.isEmpty()) {
                Persona nuevaPersona = new Persona(0, nombre, direccion);
                controladorDB.darAlta(nuevaPersona);

                // Recargar la lista para obtener el ID asignado
                refrescarTabla();
            } else {
                System.out.println("El nombre esta vacío");
            }
        });

        // DAR BAJA
        panelControles.getBtnBaja().setOnAction(e -> {
            Persona seleccionada = agendaPersonas.getPersonaSeleccionada();
            if (seleccionada != null) {
                controladorDB.darBaja(seleccionada);
                listaPersonas.remove(seleccionada);
                listaTelefonos.clear();
            } else {
                System.out.println("Nadie seleccionado");
            }
        });

        //MODIFICAR NOMBRE
        panelControles.getBtnModificarNombre().setOnAction(e -> {
            Persona seleccionada = agendaPersonas.getPersonaSeleccionada();
            String nuevoNombre = panelControles.getNombre();

            if (seleccionada != null && !nuevoNombre.isEmpty()) {
                controladorDB.modificarNombre(seleccionada, nuevoNombre);
                seleccionada.setNombre(nuevoNombre);
                agendaPersonas.getTablaPersonas().refresh();
            } else {
                System.out.println("Seleccione persona e ingrese nombre");
            }
        });

        // MODIFICAR DIRECCIÓN
        panelControles.getBtnModificarDireccion().setOnAction(e -> {
            Persona seleccionada = agendaPersonas.getPersonaSeleccionada();
            String nuevaDireccion = panelControles.getDireccion();

            if (seleccionada != null && !nuevaDireccion.isEmpty()) {
                controladorDB.modificarDireccion(seleccionada, nuevaDireccion);
                seleccionada.setDireccion(nuevaDireccion);
                agendaPersonas.getTablaPersonas().refresh();
            } else {
                System.out.println("Seleccione persona e ingrese dirección");
            }
        });

        //  AGREGAR TELÉFONO
        panelControles.getBtnAgregarTelefono().setOnAction(e -> {
            Persona seleccionada = agendaPersonas.getPersonaSeleccionada();
            String telefono = panelControles.getTelefono();

            if (seleccionada != null && !telefono.isEmpty()) {
                controladorDB.agregarTelefono(seleccionada, telefono);
                cargarTelefonos(seleccionada);
                panelControles.setTelefono("");
            } else {
                System.out.println("Seleccione persona e ingrese teléfono");
            }
        });

        // ELIMINAR TELÉFONO
        panelControles.getBtnEliminarTelefono().setOnAction(e -> {
            Persona seleccionada = agendaPersonas.getPersonaSeleccionada();
            String telefonoSeleccionado = panelControles.getTelefonoSeleccionado();

            if (seleccionada != null && telefonoSeleccionado != null) {
                controladorDB.eliminarTelefono(seleccionada, telefonoSeleccionado);
                cargarTelefonos(seleccionada);
            } else {
                System.out.println("Seleccione persona y teléfono de la lista");
            }
        });
    }

    private void cargarTelefonos(Persona persona) {
        listaTelefonos.setAll(controladorDB.getTelefonosPorPersona(persona));
    }
    private void refrescarTabla() {
        listaPersonas.setAll(controladorDB.getPersonas());
    }


}
