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
public class BubbleSort {
    
    
    public static void sort(ArrayList<Proceso> list){
        
        Proceso buffer;
        int i,j;
        for(i = 0; i < list.size(); i++){
            for(j = 0; j < i; j++){
                if(list.get(i).getTiempoLlegada() < list.get(j).getTiempoLlegada())
                {
                    buffer = list.get(j);
                    list.set(j, list.get(i));
                    list.set(i, buffer);
                }
            }
        }        
   }
    
}
