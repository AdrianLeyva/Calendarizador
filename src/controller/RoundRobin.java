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
public class RoundRobin {
    
    private Proceso currentProcess;
    private int quantum;

    public RoundRobin(Proceso currentProcess, int quantum) {
        this.currentProcess = currentProcess;
        this.quantum = quantum;
    }

    public Proceso getCurrentProcess() {
        return currentProcess;
    }

    public void setCurrentProcess(Proceso currentProcess) {
        this.currentProcess = currentProcess;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
    
    
    public void roundRobin(int quantum){
        
        Queue<Proceso> cola = new LinkedList();
        
        
        
    }
    
}
