/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class Proceso {
    public static final String STATE_READY = "STATE_READY";
    public static final String STATE_FINALIZED = "STATE_FINALIZED";
    
    private String nombre;
    private double tiempoLlegada;
    private double rafaga;
    private int prioridad;
    private double tiempoEspera;
    private double tiempoTotal;
    private String estado;

    public Proceso(String nombre, double tiempoLlegada, double rafaga, int prioridad, String estado) {
        this.nombre = nombre;
        this.tiempoLlegada = tiempoLlegada;
        this.rafaga = rafaga;
        this.prioridad = prioridad;
        this.estado = estado;
        this.tiempoEspera = 0;
        this.tiempoTotal = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(double tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    public double getRafaga() {
        return rafaga;
    }

    public void setRafaga(double rafaga) {
        this.rafaga = rafaga;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public double getTiempoEspera() {
        return tiempoEspera;
    }

    public void setTiempoEspera(double tiempoEspera) {
        this.tiempoEspera = tiempoEspera;
    }

    public double getTiempoTotal() {
        return tiempoTotal;
    }

    public void setTiempoTotal(double tiempoTotal) {
        this.tiempoTotal = tiempoTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Proceso{" + "nombre=" + nombre + ", tiempoLlegada=" + tiempoLlegada + ", rafaga=" + rafaga + ", prioridad=" + prioridad + ", tiempoEspera=" + tiempoEspera + ", tiempoTotal=" + tiempoTotal + ", estado=" + estado + '}';
    }
    
    
    
    
}
