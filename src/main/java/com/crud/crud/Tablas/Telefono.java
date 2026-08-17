package com.crud.crud.Tablas;

public class Telefono {

    private int id;
    private int personaID;
    private String numero;

    public Telefono(int id, int personaID, String numero){
        this.id = id;
        this.personaID = personaID;
        this.numero = numero;
    }

    public int getId(){
        return id;
    }

    public int getPersonaID(){
        return personaID;
    }

    public String getNumero(){
        return numero;
    }

}
