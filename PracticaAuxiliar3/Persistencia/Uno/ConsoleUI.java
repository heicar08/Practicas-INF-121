package Uno;

package com.charango.util;

import com.charango.model.Charango;
import com.charango.service.CharangoService;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private CharangoService servicio;
    private Scanner scanner;
    
    public ConsoleUI() {
        this.servicio = new CharangoService();
        this.scanner = new Scanner(System.in);
    }
    
    public void iniciar() {
        boolean salir = false;
        
        while (!salir) {
            mostrarMenuPrincipal();
            String opcion = scanner.nextLine().trim();
            
            switch (opcion) {
                case "1": mostrarTodosCharangos(); break;
                case "2": agregarCharango(); break;
                case "3": eliminarConMasDe6Falsas(); break;
                case "4": listarPorMaterial(); break;
                case "5": buscarCon10Cuerdas(); break;
                case "6": ordenarPorMaterial(); break;
                case "7": servicio.mostrarEstadisticas(); break;
                case "8": generarDatosEjemplo(); break;
                case "9": mostrarCharangosAEliminar(); break;
                case "0": salir = true; break;
                default: System.out.println("Opción no válida");
            }
            
            if (!salir) {
                System.out.println("\nPresione Enter para continuar...");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
    
    private void mostrarMenuPrincipal() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("        SISTEMA DE CHARANGOS         ");
        System.out.println("=".repeat(50));
        System.out.println("1. Mostrar todos los charangos");
        System.out.println("2. Agregar nuevo charango");
        System.out.println("3. Eliminar con >6 cuerdas falsas");
        System.out.println("4. Listar por material");
        System.out.println("5. Buscar con 10 cuerdas");
        System.out.println("6. Ordenar por material");
        System.out.println("7. Ver estadísticas");
        System.out.println("8. Generar datos de ejemplo");
        System.out.println("9. Ver charangos a eliminar (>6 falsas)");
        System.out.println("0. Salir");
        System.out.println("=".repeat(50));
        System.out.print("Seleccione: ");
    }
    
    private void mostrarTodosCharangos() {
        List<Charango> charangos = servicio.obtenerTodos();
        
        if (charangos.isEmpty()) {
            System.out.println("\nNo hay charangos");
            return;
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("               CHARANGOS");
        System.out.println("=".repeat(70));
        System.out.printf("%-4s %-15s %-10s %-8s %-20s\n", "ID", "Material", "Cuerdas", "Falsas", "Estado");
        System.out.println("-".repeat(70));
        
        for (Charango c : charangos) {
            System.out.printf("%-4d %-15s %-10d %-8d %-20s\n",
                    c.getId(), c.getMaterial(), c.getNumeroCuerdas(),
                    c.contarCuerdasFalsas(), c.getRepresentacionCuerdas());
        }
        
        System.out.println("=".repeat(70));
        System.out.println("Total: " + charangos.size());
    }
    
    private void agregarCharango() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("     NUEVO CHARANGO");
        System.out.println("=".repeat(50));
        
        System.out.print("Material: ");
        String material = scanner.nextLine().trim();
        if (material.isEmpty()) {
            System.out.println("Material vacío");
            return;
        }
        
        int numeroCuerdas;
        while (true) {
            System.out.print("Cuerdas (0-10): ");
            try {
                numeroCuerdas = Integer.parseInt(scanner.nextLine());
                if (numeroCuerdas >= 0 && numeroCuerdas <= 10) break;
                System.out.println("0-10");
            } catch (NumberFormatException e) {
                System.out.println("Número inválido");
            }
        }
        
        Charango nuevo = new Charango(material, numeroCuerdas);
        
        if (numeroCuerdas > 0) {
            System.out.println("Estado cuerdas (S/N):");
            boolean[] cuerdas = new boolean[10];
            
            for (int i = 0; i < numeroCuerdas; i++) {
                while (true) {
                    System.out.printf("Cuerda %d: ", i + 1);
                    String respuesta = scanner.nextLine().trim().toUpperCase();
                    if (respuesta.equals("S") || respuesta.equals("N")) {
                        cuerdas[i] = respuesta.equals("S");
                        break;
                    }
                    System.out.println("S o N");
                }
            }
            nuevo.setCuerdas(cuerdas);
        }
        
        servicio.agregarCharango(nuevo);
        System.out.println("\n✓ Charango agregado");
        System.out.println("ID: " + nuevo.getId());
    }
    
    private void eliminarConMasDe6Falsas() {
        int eliminados = servicio.eliminarConMasDe6CuerdasFalsas();
        
        if (eliminados > 0) {
            System.out.println("\n✓ Eliminados: " + eliminados);
        } else {
            System.out.println("\nNo hay con >6 falsas");
        }
    }
    
    private void mostrarCharangosAEliminar() {
        List<Charango> aEliminar = servicio.obtenerTodos().stream()
                .filter(Charango::tieneMasDe6CuerdasFalsas)
                .toList();
        
        if (aEliminar.isEmpty()) {
            System.out.println("\nNo hay con >6 falsas");
            return;
        }
        
        System.out.println("\n" + "=".repeat(70));
        System.out.println("     CHARANGOS CON >6 FALSAS");
        System.out.println("=".repeat(70));
        System.out.printf("%-4s %-15s %-10s %-8s %-20s\n", "ID", "Material", "Cuerdas", "Falsas", "Estado");
        System.out.println("-".repeat(70));
        
        for (Charango c : aEliminar) {
            System.out.printf("%-4d %-15s %-10d %-8d %-20s\n",
                    c.getId(), c.getMaterial(), c.getNumeroCuerdas(),
                    c.contarCuerdasFalsas(), c.getRepresentacionCuerdas());
        }
        
        System.out.println("=".repeat(70));
        System.out.println("A eliminar: " + aEliminar.size());
    }
    
    private void listarPorMaterial() {
        System.out.print("Material: ");
        String material = scanner.nextLine().trim();
        
        if (material.isEmpty()) {
            System.out.println("Material vacío");
            return;
        }
        
        List<Charango> resultado = servicio.listarPorMaterial(material);
        
        if (resultado.isEmpty()) {
            System.out.println("\nNo hay de " + material);
            return;
        }
        
        System.out.println("\nCharangos de " + material + ":");
        resultado.forEach(c -> System.out.println("  " + c));
        System.out.println("Total: " + resultado.size());
    }
    
    private void buscarCon10Cuerdas() {
        List<Charango> resultado = servicio.buscarCon10Cuerdas();
        
        System.out.println("\nCharangos con 10 cuerdas:");
        
        if (resultado.isEmpty()) {
            System.out.println("No hay");
            return;
        }
        
        resultado.forEach(c -> System.out.println("  " + c));
        System.out.println("\nTotal: " + resultado.size());
    }
    
    private void ordenarPorMaterial() {
        servicio.ordenarPorMaterial();
        System.out.println("✓ Ordenados por material");
    }
    
    private void generarDatosEjemplo() {
        System.out.print("¿Eliminar datos existentes? (S/N): ");
        String confirmacion = scanner.nextLine().trim().toUpperCase();
        
        if (confirmacion.equals("S")) {
            servicio = new CharangoService();
            servicio.generarDatosEjemplo();
            System.out.println("\n✓ Ejemplos creados");
            System.out.println("Total: " + servicio.getTotalCharangos());
        } else {
            System.out.println("\nCancelado");
        }
    }
}