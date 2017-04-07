/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.Calendarizador;
import java.util.ArrayList;
import model.Proceso;

/**
 *
 * @author adrianaldairleyvasanchez
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Proceso> arrayList = new ArrayList<>();
        Calendarizador calend;
        arrayList.add(new Proceso("proceso1",0,5,7,"activo"));
        arrayList.add(new Proceso("proceso2",4,8,4,"activo"));
        arrayList.add(new Proceso("proceso3",8,10,2,"activo"));
        arrayList.add(new Proceso("proceso4",12,7,1,"activo"));
        arrayList.add(new Proceso("proceso5",16,3,6,"activo"));
        
        calend = new Calendarizador(arrayList);
        calend.executeSRTF();
    }
}
