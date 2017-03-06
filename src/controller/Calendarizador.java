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
    
    public void execute(){
        FCFS fcfs = new FCFS(lista);
        fcfs.execute();
    }
    
}
