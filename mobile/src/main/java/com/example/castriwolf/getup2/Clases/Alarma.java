package com.example.castriwolf.getup2.Clases;

import java.time.LocalTime;
import java.util.ArrayList;

public class Alarma {
//Clase alarma
    private int id_alarma;
    private String lugarSalida;
    private String lugarLlegada;
    private int horaSalida;
    private int minutoSalida;
    private int minutoLlegada;
    private int horaLlegada;
    private int lunes;
    private int martes;
    private int miercoles;
    private int jueves;
    private int viernes;
    private int sabado;
    private int domingo;
    private ArrayList<Actividad> actividades;


    public Alarma(int id_alarma, String lugarSalida, String lugarLlegada, int horaSalida, int minutoSalida, int horaLlegada, int minutoLlegada,int l,int m,int x,int j,int v,int s,int d) {
        this.id_alarma = id_alarma;
        this.lugarSalida = lugarSalida;
        this.lugarLlegada = lugarLlegada;
        this.horaSalida = horaSalida;
        this.minutoSalida = minutoSalida;
        this.horaLlegada = horaLlegada;
        this.minutoLlegada = minutoLlegada;
        this.lunes = l;
        this.martes = m;
        this.miercoles = x;
        this.jueves = j;
        this.viernes = v;
        this.sabado = s;
        this.domingo = d;
        actividades = new ArrayList<Actividad>();
    }

    public int getLunes() {
        return lunes;
    }

    public void setLunes(int lunes) {
        this.lunes = lunes;
    }

    public int getMartes() {
        return martes;
    }

    public void setMartes(int martes) {
        this.martes = martes;
    }

    public int getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(int miercoles) {
        this.miercoles = miercoles;
    }

    public int getJueves() {
        return jueves;
    }

    public void setJueves(int jueves) {
        this.jueves = jueves;
    }

    public int getViernes() {
        return viernes;
    }

    public void setViernes(int viernes) {
        this.viernes = viernes;
    }

    public int getSabado() {
        return sabado;
    }

    public void setSabado(int sabado) {
        this.sabado = sabado;
    }

    public int getDomingo() {
        return domingo;
    }

    public void setDomingo(int domingo) {
        this.domingo = domingo;
    }

    public int getMinutoLlegada() {
        return minutoLlegada;
    }

    public void setMinutoLlegada(int minutoLlegada) {
        this.minutoLlegada = minutoLlegada;
    }

    public void setHoraLlegada(int horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    public int getId_alarma() {
        return id_alarma;
    }

    public void setId_alarma(int id_alarma) {
        this.id_alarma = id_alarma;
    }

    public void setHoraSalida(int horaSalida) {
        this.horaSalida = horaSalida;
    }

    public int getMinutoSalida() {
        return minutoSalida;
    }

    public void setMinutoSalida(int minutoSalida) {
        this.minutoSalida = minutoSalida;
    }

    public String getLugarSalida() {
        return lugarSalida;
    }

    public String getLugarLlegada() {
        return lugarLlegada;
    }

    public int getHoraSalida() {
        return horaSalida;
    }

    public int getHoraLlegada() {
        return horaLlegada;
    }

    public ArrayList<Actividad> getActividades() {
        return actividades;
    }

    public void setLugarSalida(String lugarSalida) {
        this.lugarSalida = lugarSalida;
    }

    public void setLugarLlegada(String lugarLlegada) {
        this.lugarLlegada = lugarLlegada;
    }

    public void setActividades(ArrayList<Actividad> actividades) {
        this.actividades = actividades;
    }

    public void crearAlarma(int id_alarma, String lugarSalida, String lugarLlegada, LocalTime horaLlegada) {


    }
}
