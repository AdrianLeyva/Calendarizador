/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.ArrayList;
import java.util.Stack;
import model.Proceso;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class FCFS {

    public FCFS() {
    }
    
    //Algoritmo First-come First-served
    public static Stack execute(ArrayList<Proceso> lista){
       boolean isCPURunning = false;
       Stack pilaListos = new Stack();
       Stack pilaFinalizados = new Stack();
       Proceso procesoActual = null;
       
       BubbleSort.sort(lista);
       
       for(int i=0;pilaFinalizados.size() != lista.size();i++){
           
            Proceso proceso = getProcessComing(lista, i);
            
            if(isCPURunning){
                
                if(proceso!=null)
                    pilaListos.push(proceso);
                
                procesoActual.setTiempoTotal(i - procesoActual.getTiempoLlegada());
                if(procesoActual.getRafaga() == (procesoActual.getTiempoTotal() - procesoActual.getTiempoEspera())){
                    pilaFinalizados.push(procesoActual);
                    isCPURunning = false;
                }
                     
                
            }else{
                
                if(pilaListos.isEmpty()){
                    
                    if(proceso!=null){
                        procesoActual = proceso;
                        procesoActual.setTiempoEspera(i - procesoActual.getTiempoLlegada());
                        isCPURunning = true;
                        //i--;
                    }
                    
                }else{
                    
                    if(proceso!=null)
                        pilaListos.push(proceso);
                    
                
                    System.out.println(procesoActual.toString());
                    procesoActual = (Proceso)pilaListos.pop();
                    isCPURunning = true;
                    //i--;
                }
                
            }
           
        }
       
        printLog(pilaFinalizados);
       
       return pilaFinalizados;      
    }
    
    //Verifica si ha llegado un nuevo proceso
    private static Proceso getProcessComing(ArrayList<Proceso> lista, int time){
        Proceso proceso = null;
        for(int i=0;i<lista.size();i++){
            if(lista.get(i).getTiempoLlegada() == time){
                proceso = lista.get(i);
                break;
            }
        }
        return proceso;
    }
    
    private static void printLog(Stack stack){
        int limit = stack.size();
        for(int i=0;i<limit;i++){
            Proceso proceso = (Proceso)stack.pop();
            System.out.println(proceso.toString());
        }
    }
}
