package Uno;

package com.charango.persistence;

import com.charango.model.Charango;
import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GestorCharangos implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<Charango> listaCharangos;
    private String archivo;
    private int siguienteId;
    
    public GestorCharangos() {
        this("data/charangos.dat");
    }
    
    public GestorCharangos(String archivo) {
        this.archivo = archivo;
        this.listaCharangos = new ArrayList<>();
        this.siguienteId = 1;
        cargarDesdeArchivo();
    }
    
    public void agregarCharango(Charango charango) {
        if (charango.getId() == null) {
            charango.setId(siguienteId++);
        }
        listaCharangos.add(charango);
        guardarEnArchivo();
    }
    
    public boolean eliminarCharango(int id) {
        boolean eliminado = listaCharangos.removeIf(c -> c.getId() != null && c.getId() == id);
        if (eliminado) guardarEnArchivo();
        return eliminado;
    }
    
    public Charango buscarPorId(int id) {
        return listaCharangos.stream()
                .filter(c -> c.getId() != null && c.getId() == id)
                .findFirst()
                .orElse(null);
    }
    
    public List<Charango> obtenerTodos() {
        return new ArrayList<>(listaCharangos);
    }
    
    public int eliminarConMasDe6CuerdasFalsas() {
        List<Charango> paraEliminar = listaCharangos.stream()
                .filter(Charango::tieneMasDe6CuerdasFalsas)
                .collect(Collectors.toList());
        
        listaCharangos.removeAll(paraEliminar);
        
        if (!paraEliminar.isEmpty()) guardarEnArchivo();
        return paraEliminar.size();
    }
    
    public List<Charango> listarPorMaterial(String material) {
        return listaCharangos.stream()
                .filter(c -> c.esDeMaterial(material))
                .collect(Collectors.toList());
    }
    
    public List<Charango> buscarCon10Cuerdas() {
        return listaCharangos.stream()
                .filter(Charango::tiene10Cuerdas)
                .collect(Collectors.toList());
    }
    
    public void ordenarPorMaterial() {
        listaCharangos.sort(Comparator.comparing(c -> c.getMaterial().toLowerCase()));
        guardarEnArchivo();
    }
    
    public List<Charango> obtenerOrdenadosPorMaterial() {
        return listaCharangos.stream()
                .sorted(Comparator.comparing(c -> c.getMaterial().toLowerCase()))
                .collect(Collectors.toList());
    }
    
    public void guardarEnArchivo() {
        try {
            File file = new File(archivo);
            file.getParentFile().mkdirs();
            
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(this);
            }
            crearBackup();
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }
    
    @SuppressWarnings("unchecked")
    public void cargarDesdeArchivo() {
        try {
            File file = new File(archivo);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    GestorCharangos gestorCargado = (GestorCharangos) ois.readObject();
                    this.listaCharangos = gestorCargado.listaCharangos;
                    this.siguienteId = gestorCargado.siguienteId;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            listaCharangos = new ArrayList<>();
            siguienteId = 1;
        }
    }
    
    private void crearBackup() {
        String backupFile = archivo.replace(".dat", "_backup_" + System.currentTimeMillis() + ".dat");
        try {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(backupFile))) {
                oos.writeObject(this);
            }
        } catch (IOException e) {
            System.err.println("Error al crear backup");
        }
    }
    
    public void generarDatosEjemplo() {
        if (listaCharangos.isEmpty()) {
            boolean[] cuerdas1 = new boolean[10];
            cuerdas1[2] = false; cuerdas1[5] = false;
            agregarCharango(new Charango(null, "Madera", 8, cuerdas1));
            
            agregarCharango(new Charango(null, "Madera", 10, new boolean[10]));
            
            boolean[] cuerdas3 = new boolean[10];
            cuerdas3[3] = false;
            agregarCharango(new Charango(null, "Plástico", 6, cuerdas3));
            
            boolean[] cuerdas4 = new boolean[10];
            cuerdas4[8] = false; cuerdas4[9] = false;
            agregarCharango(new Charango(null, "Metal", 10, cuerdas4));
            
            boolean[] cuerdas5 = new boolean[10];
            for (int i = 0; i < 7; i++) cuerdas5[i] = false;
            for (int i = 7; i < 10; i++) cuerdas5[i] = true;
            agregarCharango(new Charango(null, "Madera", 5, cuerdas5));
            
            agregarCharango(new Charango(null, "Metal", 7, new boolean[10]));
            
            boolean[] cuerdas7 = new boolean[10];
            cuerdas7[0] = false; cuerdas7[4] = false;
            cuerdas7[7] = false; cuerdas7[9] = false;
            agregarCharango(new Charango(null, "Plástico", 10, cuerdas7));
            
            boolean[] cuerdas8 = new boolean[10];
            cuerdas8[1] = false; cuerdas8[5] = false; cuerdas8[8] = false;
            agregarCharango(new Charango(null, "Madera", 9, cuerdas8));
        }
    }
    
    public void mostrarEstadisticas() {
        System.out.println("\n=== ESTADÍSTICAS ===");
        System.out.println("Total: " + listaCharangos.size());
        System.out.println("Con 10 cuerdas: " + buscarCon10Cuerdas().size());
        
        listaCharangos.stream()
                .collect(Collectors.groupingBy(Charango::getMaterial, Collectors.counting()))
                .forEach((material, cantidad) -> 
                    System.out.printf("%s: %d\n", material, cantidad));
    }
}
