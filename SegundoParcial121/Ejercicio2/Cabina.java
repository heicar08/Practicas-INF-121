package Ejercicio2;

import java.util.Arrays;

class Cabina {
    private int nroCabina;
    private Persona[] personasAbordo;

    public Cabina(int nroCabina) {
        this.nroCabina = nroCabina;
        this.personasAbordo = new Persona[0];
    }

    public void agregarPersonal(Persona p) {
        Persona[] nuevoArray = Arrays.copyOf(personasAbordo, personasAbordo.length + 1);
        nuevoArray[personasAbordo.length] = p;
        this.personasAbordo = nuevoArray;
    }

    public boolean agregarPersonaConValidacion(Persona persona) {
        if (personasAbordo.length >= 10) {
            return false;
        }
        
        float pesoTotal = calcularPesoTotal();
        if (pesoTotal + persona.getPesoPersona() > 850) {
            return false;
        }
        
        agregarPersonal(persona);
        return true;
    }

    public boolean cumpleReglasAbordo() {
        return personasAbordo.length <= 10 && calcularPesoTotal() <= 850;
    }

    private float calcularPesoTotal() {
        float pesoTotal = 0;
        for (Persona persona : personasAbordo) {
            if (persona != null) {
                pesoTotal += persona.getPesoPersona();
            }
        }
        return pesoTotal;
    }

    public int getNroCabina() {
        return nroCabina;
    }

    public Persona[] getPersonasAbordo() {
        return personasAbordo;
    }
}