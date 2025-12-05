package com.charango.service;

import com.charango.model.Charango;
import com.charango.persistence.GestorCharangos;
import java.util.List;

public class CharangoService {
    private GestorCharangos gestor;
    
    public CharangoService() {
        this.gestor = new GestorCharangos();
    }
    
    public void agregarCharango(Charango charango) {
        gestor.agregarCharango(charango);
    }
    
    public boolean eliminarCharango(int id) {
        return gestor.eliminarCharango(id);
    }
    
    public Charango buscarPorId(int id) {
        return gestor.buscarPorId(id);
    }
    
    public List<Charango> obtenerTodos() {
        return gestor.obtenerTodos();
    }
    
    public int eliminarConMasDe6CuerdasFalsas() {
        return gestor.eliminarConMasDe6CuerdasFalsas();
    }
    
    public List<Charango> listarPorMaterial(String material) {
        return gestor.listarPorMaterial(material);
    }
    
    public List<Charango> buscarCon10Cuerdas() {
        return gestor.buscarCon10Cuerdas();
    }
    
    public void ordenarPorMaterial() {
        gestor.ordenarPorMaterial();
    }
    
    public List<Charango> obtenerOrdenadosPorMaterial() {
        return gestor.obtenerOrdenadosPorMaterial();
    }
    
    public void generarDatosEjemplo() {
        gestor.generarDatosEjemplo();
    }
    
    public void mostrarEstadisticas() {
        gestor.mostrarEstadisticas();
    }
    
    public int getTotalCharangos() {
        return gestor.obtenerTodos().size();
    }
}