package com.example.castriwolf.getup2.Clases;

public class Actividad {

    //Clase Actividad
    private int id_Actividad;
    private Alarma alarma;
    private String razon;
    private int tiempo;


    public Actividad(Alarma alarma,String razon, int tiempo) {
        this.alarma=alarma;
        this.razon = razon;
        this.tiempo = tiempo;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }
}
