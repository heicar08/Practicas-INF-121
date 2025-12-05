package Uno;

package com.charango.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Charango implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer id;
    private String material;
    private int numeroCuerdas;
    private boolean[] cuerdas;
    private LocalDateTime fechaCreacion;
    
    public Charango() {
        this.cuerdas = new boolean[10];
        this.fechaCreacion = LocalDateTime.now();
    }
    
    public Charango(String material, int numeroCuerdas) {
        this();
        this.material = material;
        setNumeroCuerdas(numeroCuerdas);
        inicializarCuerdas();
    }
    
    public Charango(Integer id, String material, int numeroCuerdas, boolean[] cuerdas) {
        this.id = id;
        this.material = material;
        setNumeroCuerdas(numeroCuerdas);
        setCuerdas(cuerdas);
        this.fechaCreacion = LocalDateTime.now();
    }
    
    private void inicializarCuerdas() {
        for (int i = 0; i < numeroCuerdas && i < 10; i++) {
            cuerdas[i] = true;
        }
    }
    
    public int contarCuerdasFalsas() {
        int contador = 0;
        for (boolean cuerda : cuerdas) {
            if (!cuerda) contador++;
        }
        return contador;
    }
    
    public boolean tieneMasDe6CuerdasFalsas() {
        return contarCuerdasFalsas() > 6;
    }
    
    public boolean esDeMaterial(String material) {
        return this.material.equalsIgnoreCase(material);
    }
    
    public boolean tiene10Cuerdas() {
        return numeroCuerdas == 10;
    }
    
    public String getRepresentacionCuerdas() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < 10; i++) {
            if (i < numeroCuerdas) {
                sb.append(cuerdas[i] ? "■" : "□");
            } else {
                sb.append(" ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getMaterial() {
        return material;
    }
    
    public void setMaterial(String material) {
        this.material = material;
    }
    
    public int getNumeroCuerdas() {
        return numeroCuerdas;
    }
    
    public void setNumeroCuerdas(int numeroCuerdas) {
        if (numeroCuerdas < 0 || numeroCuerdas > 10) {
            throw new IllegalArgumentException("Número de cuerdas inválido");
        }
        this.numeroCuerdas = numeroCuerdas;
    }
    
    public boolean[] getCuerdas() {
        return Arrays.copyOf(cuerdas, 10);
    }
    
    public void setCuerdas(boolean[] cuerdas) {
        if (cuerdas.length != 10) {
            throw new IllegalArgumentException("Array debe tener 10 elementos");
        }
        this.cuerdas = Arrays.copyOf(cuerdas, 10);
    }
    
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }
    
    @Override
    public String toString() {
        return String.format("Charango[ID=%d, Material=%s, Cuerdas=%d, Falsas=%d] %s",
                id, material, numeroCuerdas, contarCuerdasFalsas(), getRepresentacionCuerdas());
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Charango charango = (Charango) obj;
        return id != null && id.equals(charango.id);
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}