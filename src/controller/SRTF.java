/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import model.Proceso;

/**
 *
 * @author b1796
 */
public class SRTF {
    private ArrayList<Proceso> processesList;
    private boolean isCPURunning;
    private boolean band;
    private Proceso currentProcess;
    private int listSize;
    private int timer;
    private Queue readyQueue;
    private Queue finishedQueue;
    
    public SRTF(ArrayList<Proceso> processesList) {
        this.processesList = processesList;
        this.isCPURunning = false;
        this.band = false;
        this.readyQueue = new LinkedList();
        this.finishedQueue = new LinkedList();
        this.currentProcess = null;
        this.listSize = this.processesList.size();
        this.timer = 0;
    }
    
    public void execute(){
        while(this.finishedQueue.size() != this.listSize){
            addNewProcess();
            runCPU();
        }
        
        printFinishedQueue();
        printTimer();
    }
    
    
    //Valida procesos entrantes y los agrega a la pila de listos si no son nulos.
    private void addNewProcess(){
        Proceso process = getProcessComing();
        
        if(process!=null){
            process = verificarDP(process);
            this.readyQueue.add(process);
            System.out.println(" > Cola de listos: " + process.toString());
        }
    }
    
    private void runCPU(){
        if(!this.isCPURunning){
            this.currentProcess = (Proceso)this.readyQueue.poll();
            this.currentProcess.setTiempoEspera(this.timer - this.currentProcess.getTiempoLlegada() 
                    - this.currentProcess.getTiempoUtilizado());
            this.isCPURunning = true;
            if (band) {
                System.out.println("\n--------------Finalizo un proceso e inicio otro-------------\n");
            } else {
                System.out.println("------------------------------Inicio-----------------------------");
                band = true;
            }
        }else{
            double dinamicRafaga = modificarCurrentProcess();
            if(this.currentProcess.getRafaga() == dinamicRafaga){
                this.finishedQueue.add(this.currentProcess);
                this.isCPURunning = false;
                this.timer--;
            }
            this.timer++;
            
        }
        
    }
    private double modificarCurrentProcess(){
        this.currentProcess.setTiempoTotal(this.timer - this.currentProcess.getTiempoLlegada());
        double dinamicRafaga = (this.currentProcess.getTiempoTotal() - this.currentProcess.getTiempoEspera());
        this.currentProcess.setTiempoRestante(this.currentProcess.getRafaga() - dinamicRafaga);
        this.currentProcess.setTiempoUtilizado(dinamicRafaga);
        System.out.println("Proceso en ejecucion: " + this.currentProcess.toString() + " | Timer = " + this.timer);
        return dinamicRafaga;
    }
    
    //Verifica si un proceso nuevo encontrado tiene preferencia al que se esté ejecutando en ese momento
    private Proceso verificarDP(Proceso p){
        if(this.currentProcess != null){
            Proceso temp = this.currentProcess;
            if(p.getRafaga() < this.currentProcess.getTiempoRestante()){
                modificarCurrentProcess();
                System.out.println("\n--------------------Interrupcion------------------\n");
                //this.timer --;
                this.currentProcess = p;
                return temp;
            }else
                return p;
        }else
            return p;
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
    
    private void printFinishedQueue(){
        System.out.println("\n\n\nProcesos Finalizados");
        int limit = this.finishedQueue.size();
        for(int i=0;i<limit;i++){
            Proceso proceso = (Proceso)this.finishedQueue.poll();
            System.out.println(proceso.toString());
        }
    }
    
    private void printTimer(){
        System.out.println("El tiempo total de ejecución es: " + this.timer);
    }
}

/**
    //Imprime tiempo espera promedio
    private void printTEP(){
        double time = 0;
        double average = 0;
        for(int i=0;i<this.finishedStack.size();i++){
            time = time + ((Proceso)this.finishedStack.get(i)).getTiempoEspera();
        }
        average = time/this.finishedStack.size();
        
        System.out.println("El tiempo de espera promedio es: " + average);
        
    }
    
    //Imprime tiempo total promedio
    private void printTTP(){
        double time = 0;
        double average = 0;
        for(int i=0;i<this.finishedStack.size();i++){
            time = time + ((Proceso)this.finishedStack.get(i)).getTiempoTotal();
        }
        average = time/this.finishedStack.size();
        
        System.out.println("El tiempo total promedio es: " + average);
    }
*/
