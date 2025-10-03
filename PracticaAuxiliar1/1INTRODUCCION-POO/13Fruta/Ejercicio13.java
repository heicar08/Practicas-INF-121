package PracticaAuxiliar1.1INTRODUCCION-POO.13Fruta;

import java.util.*;

public class Ejercicio13 {
    
    public static ArrayList<Fruta> instanciarFrutas() {
        ArrayList<Fruta> frutas = new ArrayList<>();
        
        Fruta fruta1 = new Fruta("Naranja", "Cítrica");
        fruta1.agregarVitamina("C");
        fruta1.agregarVitamina("A");
        fruta1.agregarVitamina("B");
        
        Fruta fruta2 = new Fruta("Manzana", "Templada");
        fruta2.agregarVitamina("C");
        fruta2.agregarVitamina("K");
        
        Fruta fruta3 = new Fruta("Kiwi", "Subtropical");
        fruta3.agregarVitamina("C");
        fruta3.agregarVitamina("E");
        fruta3.agregarVitamina("K");
        
        frutas.add(fruta1);
        frutas.add(fruta2);
        frutas.add(fruta3);
        
        return frutas;
    }
    
    public static Fruta frutaConMasVitaminas(ArrayList<Fruta> frutas) {
        int maxVitaminas = 0;
        Fruta frutaMax = null;
        
        for (Fruta fruta : frutas) {
            if (fruta.getNroVitaminas() > maxVitaminas) {
                maxVitaminas = fruta.getNroVitaminas();
                frutaMax = fruta;
            }
        }
        
        if (frutaMax != null) {
            System.out.println("Fruta con más vitaminas: " + frutaMax.getNombre() + 
                             " (" + frutaMax.getNroVitaminas() + " vitaminas)");
        }
        return frutaMax;
    }
    
    public static void mostrarVitaminasFrutaX(ArrayList<Fruta> frutas, String nombreFruta) {
        for (Fruta fruta : frutas) {
            if (fruta.getNombre().equalsIgnoreCase(nombreFruta)) {
                fruta.mostrarVitaminas();
                return;
            }
        }
        System.out.println("Fruta '" + nombreFruta + "' no encontrada");
    }
    
    public static int contarVitaminasCitricas(ArrayList<Fruta> frutas) {
        int count = 0;
        for (Fruta fruta : frutas) {
            if (fruta.esCitrica()) {
                count += fruta.getNroVitaminas();
            }
        }
        System.out.println("Total de vitaminas en frutas cítricas: " + count);
        return count;
    }
    
    public static void ordenarPorVitaminas(ArrayList<Fruta> frutas) {
        Collections.sort(frutas, new Comparator<Fruta>() {
            @Override
            public int compare(Fruta f1, Fruta f2) {
                String vit1 = f1.getNroVitaminas() > 0 ? f1.getVitaminas()[0] : "";
                String vit2 = f2.getNroVitaminas() > 0 ? f2.getVitaminas()[0] : "";
                return vit1.compareTo(vit2);
            }
        });
        
        System.out.println("\nFrutas ordenadas por primera vitamina:");
        for (Fruta fruta : frutas) {
            if (fruta.getNroVitaminas() > 0) {
                System.out.println(fruta.getNombre() + ": " + fruta.getVitaminas()[0]);
            } else {
                System.out.println(fruta.getNombre() + ": Sin vitaminas");
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE FRUTAS ===\n");
        
        ArrayList<Fruta> frutas = instanciarFrutas();
        
        for (Fruta fruta : frutas) {
            System.out.println("Fruta: " + fruta.getNombre() + " | Tipo: " + 
                             fruta.getTipo() + " | Vitaminas: " + fruta.getNroVitaminas());
        }
        
        System.out.println("\n" + "=".repeat(50));
        frutaConMasVitaminas(frutas);
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Vitaminas de fruta específica:");
        mostrarVitaminasFrutaX(frutas, "Kiwi");
        
        System.out.println("\n" + "=".repeat(50));
        contarVitaminasCitricas(frutas);
        
        System.out.println("\n" + "=".repeat(50));
        ordenarPorVitaminas(frutas);
    }
}