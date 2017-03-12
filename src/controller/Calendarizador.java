/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import model.Proceso;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class Calendarizador {
    private ArrayList<Proceso> lista;
    
    public Calendarizador(ArrayList<Proceso> lista) {
        this.lista = lista;
    }
    
    public void executeFCFS(){
        FCFS fcfs = new FCFS(lista);
        fcfs.execute();
    }
    
    public void executeSJF(){
        SJF sjf = new SJF(lista);
        sjf.execute();
    }
    
    public void executeSRTF(){
        SRTF srtf = new SRTF(lista);
        srtf.execute();
    }
    public void executePrioridad(){
        Prioridad prio = new Prioridad(lista);
        prio.execute();
        
    }
}
