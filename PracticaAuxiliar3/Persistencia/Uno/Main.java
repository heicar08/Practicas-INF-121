package Uno;
package com.charango;

import com.charango.util.ConsoleUI;

public class Main {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   SISTEMA DE GESTIÓN DE CHARANGOS     ");
        System.out.println("========================================");
        
        java.io.File dataDir = new java.io.File("data");
        if (!dataDir.exists()) {
            dataDir.mkdirs();
        }
        
        ConsoleUI ui = new ConsoleUI();
        ui.iniciar();
    }
}