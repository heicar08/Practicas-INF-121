package Ejercicio2;

public class MITeleferico {
    private Linea[] lineas;
    private float cantidadIngresos;

    public MITeleferico() {
        this.lineas = new Linea[3];
        this.lineas[0] = new Linea("Amarilla");
        this.lineas[1] = new Linea("Rojo");
        this.lineas[2] = new Linea("Verde");
        this.cantidadIngresos = 0;
    }

    public void agregarPersonalFila(Persona p, String linea) {
        for (Linea l : lineas) {
            if (l != null && l.getColor().equalsIgnoreCase(linea)) {
                l.agregarPersonal(p);
                break;
            }
        }
    }

    public void agregarCabinas(String linea) {
        for (Linea l : lineas) {
            if (l != null && l.getColor().equalsIgnoreCase(linea)) {
                int nroCabina = l.getCabinas().length + 1;
                l.agregarCabina(nroCabina);
                break;
            }
        }
    }

    public boolean agregarPrimeraPersonaACabina(int nroCabina, String colorLinea, Persona persona) {
        for (Linea linea : lineas) {
            if (linea != null && linea.getColor().equalsIgnoreCase(colorLinea)) {
                Cabina[] cabinas = linea.getCabinas();
                for (Cabina cabina : cabinas) {
                    if (cabina != null && cabina.getNroCabina() == nroCabina) {
                        return cabina.agregarPersonaConValidacion(persona);
                    }
                }
            }
        }
        return false;
    }

    public boolean verificarReglasAbordoTodasCabinas() {
        for (Linea linea : lineas) {
            if (linea != null) {
                if (!linea.verificarReglasAbordoTodasCabinas()) {
                    return false;
                }
            }
        }
        return true;
    }

    public float calcularIngresoTotal() {
        float total = 0;
        for (Linea linea : lineas) {
            if (linea != null) {
                total += linea.calcularIngresoLinea();
            }
        }
        this.cantidadIngresos = total;
        return total;
    }

    public void mostrarLineaConMasIngresoRegular() {
        String lineaMax = "";
        float maxIngreso = 0;
        
        for (Linea linea : lineas) {
            if (linea != null) {
                float ingresoRegular = linea.calcularIngresoRegular();
                if (ingresoRegular > maxIngreso) {
                    maxIngreso = ingresoRegular;
                    lineaMax = linea.getColor();
                }
            }
        }
        
        System.out.println("Línea con más ingreso regular: " + lineaMax + " - Ingreso: " + maxIngreso + " bs");
    }

    public Linea[] getLineas() {
        return lineas;
    }

    public float getCantidadIngresos() {
        return cantidadIngresos;
    }
}