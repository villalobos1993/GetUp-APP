package com.example.castriwolf.getup2.Clases;

public class Pending {

    //clase pending intent
    private int idPending;
    private int idAlarma;

    public Pending(int idPending, int idAlarma) {
        this.idPending = idPending;
        this.idAlarma = idAlarma;
    }

    public int getIdPending() {
        return idPending;
    }

    public void setIdPending(int idPending) {
        this.idPending = idPending;
    }

    public int getIdAlarma() {
        return idAlarma;
    }

    public void setIdAlarma(int idAlarma) {
        this.idAlarma = idAlarma;
    }
}
