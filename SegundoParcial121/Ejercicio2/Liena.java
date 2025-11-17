package Ejercicio2;

import java.util.Arrays;

class Linea {
    private String color;
    private Persona[] filaPersonas;
    private Cabina[] cabinas;
    private int cantidadCabinas;

    public Linea(String color) {
        this.color = color;
        this.filaPersonas = new Persona[0];
        this.cabinas = new Cabina[0];
        this.cantidadCabinas = 0;
    }

    public void agregarPersonal(Persona p) {
        Persona[] nuevaFila = Arrays.copyOf(filaPersonas, filaPersonas.length + 1);
        nuevaFila[filaPersonas.length] = p;
        this.filaPersonas = nuevaFila;
    }

    public void agregarCabina(int nroCabina) {
        Cabina[] nuevasCabinas = Arrays.copyOf(cabinas, cabinas.length + 1);
        nuevasCabinas[cabinas.length] = new Cabina(nroCabina);
        this.cabinas = nuevasCabinas;
        this.cantidadCabinas++;
    }

    public boolean verificarReglasAbordoTodasCabinas() {
        for (Cabina cabina : cabinas) {
            if (cabina != null) {
                if (!cabina.cumpleReglasAbordo()) {
                    return false;
                }
            }
        }
        return true;
    }

    public float calcularIngresoLinea() {
        float total = 0;
        for (Persona persona : filaPersonas) {
            if (persona != null) {
                if (persona.getEdad() < 25 || persona.getEdad() > 60) {
                    total += 1.50f;
                } else {
                    total += 3.00f;
                }
            }
        }
        return total;
    }

    public float calcularIngresoRegular() {
        float total = 0;
        for (Persona persona : filaPersonas) {
            if (persona != null && persona.getEdad() >= 25 && persona.getEdad() <= 60) {
                total += 3.00f;
            }
        }
        return total;
    }

    public String getColor() {
        return color;
    }

    public Persona[] getFilaPersonas() {
        return filaPersonas;
    }

    public Cabina[] getCabinas() {
        return cabinas;
    }

    public int getCantidadCabinas() {
        return cantidadCabinas;
    }
}