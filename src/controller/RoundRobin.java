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
    private ArrayList<Proceso> processesList;
    private boolean isCPURunning;
    private Queue readyStack;
    private Queue finishedStack;
    private Proceso currentProcess;
    private int listSize;
    private int timer;
    private int quantum;
    private int counterQuantum;
    
    public RoundRobin(ArrayList<Proceso> processesList, int quantum) {
        this.processesList = processesList;
        this.isCPURunning = false;
        this.readyStack = new LinkedList();
        this.finishedStack = new LinkedList();
        this.currentProcess = null;
        this.listSize = this.processesList.size();
        this.timer = 0;
        this.quantum = quantum;
        this.counterQuantum = 0;
    }

    public int getQuantum() {
        return quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
    
    
    
    public void execute(){
        sortProcessesList();
        
        while(this.finishedStack.size() != this.listSize){
            addNewProcess();
            runCPU();
        }
        
        printFinishedStack();
        printTimer();
    }
    
    
    //Valida procesos entrantes y los agrega a la pila de listos si no son nulos.
    private void addNewProcess(){
        Proceso process = getProcessComing();
        
        if(process!=null){
            this.readyStack.add(process);
        }
    }
    
    private void runCPU(){
        if(!this.isCPURunning){
            this.currentProcess = (Proceso)this.readyStack.element();
            this.currentProcess.setTiempoEspera(this.timer - this.currentProcess.getTiempoLlegada());
            this.readyStack.poll();
            this.isCPURunning = true;
        }else{
            this.currentProcess.setTiempoTotal(this.timer - this.currentProcess.getTiempoLlegada());
            
            double dinamicRafaja = (this.currentProcess.getTiempoTotal() - this.currentProcess.getTiempoEspera());
        
            if(this.currentProcess.getRafaga() == dinamicRafaja){
                this.finishedStack.add(this.currentProcess);
                this.isCPURunning = false;
                this.timer--;
            }            
            
            this.timer++;
            counterQuantum++;
                    
        }
        
    }
    
    
    //Verifica si ha llegado un nuevo proceso.
    private Proceso getProcessComing(){
        Proceso process = null;
        for(int i=0;i<this.processesList.size();i++){
            if(this.processesList.get(i).getTiempoLlegada() == this.timer){
                process = this.processesList.get(i);
                this.processesList.remove(i);
                break;
            }
        }
        return process;
    }
    
    private void sortProcessesList(){
        Proceso buffer;
        int i,j;
        for(i = 0; i < processesList.size(); i++){
            for(j = 0; j < i; j++){
                if(processesList.get(i).getTiempoLlegada() < processesList.get(j).getTiempoLlegada())
                {
                    buffer = processesList.get(j);
                    processesList.set(j, processesList.get(i));
                    processesList.set(i, buffer);
                }
            }
        }      
    }
    
    private void printFinishedStack(){
        int limit = this.finishedStack.size();
        for(int i=0;i<limit;i++){
            Proceso proceso = (Proceso)this.finishedStack.poll();
            System.out.println(proceso.toString());
        }
    }
    
    private void printTimer(){
        System.out.println("El tiempo total de ejecución es: " + this.timer);
    }
    
}
