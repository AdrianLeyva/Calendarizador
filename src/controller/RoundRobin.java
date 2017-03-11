/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;
import java.util.*;
import model.*;


/**
 *
 * @author Armando Carvajal
 */
class RoundRobin {

    ArrayList<Proceso> cola;
    int i = 0;
    int quantum;
    Proceso process;

    public RoundRobin(ArrayList cola, int quantum) {
        this.cola = cola;
        this.quantum = quantum;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
    
    

    public String generar() {
        //Suma de los tiempos de rafaga de cada Proceso 
        for (int i = 0; i < cola.size(); i++) {
            process = (Proceso)cola.get(i);
            i += process.getRafaga();
        }
        getQuantum();
        //---------------------------------------------
        
        
        //--Declaraciones---
        String cadena = "";
        int contador = 0;
        double aux;
        int suma_tiempo = 0;
        boolean flag = true;
        //------------------

        //----------Programa-----------
        while (flag) {

            aux = process.getRafaga();

            if (aux > 0) {

                if (aux >= getQuantum()) {
                    process.setRafaga(aux - getQuantum());
                    suma_tiempo += getQuantum();
                    cadena += "|" + process.getNombre() + "| t" + suma_tiempo + " n";
                } else {
                    process.setRafaga(0);
                    suma_tiempo += aux;
                    cadena += "|" + process.getNombre() + "| t" + suma_tiempo + " n";
                }
            }
            
            //identifica si hay mas procesos por terminar 
            contador++;
            if (contador < cola.size()) {
                process = (Proceso) cola.get(contador);
                flag = find();
            } else {
                contador = 0;
                process = (Proceso) cola.get(contador);
                flag = find();
            }

        }
        return cadena + "El tiempo fue: " + suma_tiempo;
    }

    
    
    private boolean find()//encuentra procesos restantes 
    {
        Proceso p;
        boolean f = false;
        for (int i = 0; i < cola.size(); i++) {
            p = (Proceso) cola.get(i);
            if (p.getRafaga() > 0) {
                f = true;
                break;
            }

        }
        return f;
    }

}
