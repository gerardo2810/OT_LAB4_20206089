package com.example.iot_lab4_20206089;

public class Posicion {
    private String equipo;
    private int puntos;

    public Posicion(String equipo, int puntos) {
        this.equipo = equipo;
        this.puntos = puntos;
    }

    public String getEquipo() {
        return equipo;
    }

    public int getPuntos() {
        return puntos;
    }
}
