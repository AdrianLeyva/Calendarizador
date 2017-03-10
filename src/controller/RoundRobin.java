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
    private Stack readyStack;
    private Stack finishedStack;
    private Proceso currentProcess;
    private int listSize;
    private int timer;
    private int quantum;
    
    
    public RoundRobin(ArrayList<Proceso> processesList, int quantum) {
        this.processesList = processesList;
        this.isCPURunning = false;
        this.readyStack = new Stack();
        this.finishedStack = new Stack();
        this.currentProcess = null;
        this.listSize = this.processesList.size();
        this.timer = 0;
        this.quantum = 0;
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
            this.readyStack.push(process);
        }
    }
    
    private void runCPU(){
        
        Queue queueProcess = new LinkedList();
        
        if(!this.isCPURunning){
            queueProcess.add(this.currentProcess);
            if(this.quantum == 8){
                this.currentProcess.setTiempoEspera(this.timer - this.currentProcess.getTiempoLlegada());
                queueProcess.remove();
                queueProcess.add(this.currentProcess);
                this.isCPURunning = true;
                this.quantum = 0;
            }
            
        }else{
            this.currentProcess.setTiempoTotal(this.timer - this.currentProcess.getTiempoLlegada());
            
            double dinamicRafaja = (this.currentProcess.getTiempoTotal() - this.currentProcess.getTiempoEspera());
        
            if(this.currentProcess.getRafaga() == dinamicRafaja){
                this.finishedStack.push(this.currentProcess);
                this.isCPURunning = false;
                this.timer--;
            }
            
            this.quantum++;
            this.timer++;
            
            
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
            Proceso proceso = (Proceso)this.finishedStack.pop();
            System.out.println(proceso.toString());
        }
    }
    
    private void printTimer(){
        System.out.println("El tiempo total de ejecución es: " + this.timer);
    }
    
}
