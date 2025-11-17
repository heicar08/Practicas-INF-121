package Ejercicio2;

public class main {
    public static void main(String[] args) {
        MITeleferico teleferico = new MITeleferico();
        
        teleferico.agregarCabinas("Amarilla");
        teleferico.agregarCabinas("Amarilla");
        teleferico.agregarCabinas("Rojo");
        teleferico.agregarCabinas("Verde");
        
        Persona joven = new Persona("Juan", 20, 70.5f);
        Persona adulto = new Persona("Maria", 30, 65.0f);
        Persona adulto2 = new Persona("Carlos", 35, 80.0f);
        Persona adulto3 = new Persona("Pedro", 40, 75.0f);
        Persona mayor = new Persona("Ana", 65, 60.0f);
        
        teleferico.agregarPersonalFila(joven, "Amarilla");
        teleferico.agregarPersonalFila(adulto, "Amarilla");
        teleferico.agregarPersonalFila(adulto2, "Rojo");
        teleferico.agregarPersonalFila(adulto3, "Rojo");
        teleferico.agregarPersonalFila(mayor, "Verde");
        
        System.out.println("=== SISTEMA DE TELEFÉRICO ===");
        
        boolean exito1 = teleferico.agregarPrimeraPersonaACabina(1, "Amarilla", joven);
        System.out.println("Persona agregada a cabina 1 línea Amarilla: " + exito1);
        
        boolean exito2 = teleferico.agregarPrimeraPersonaACabina(1, "Rojo", adulto2);
        System.out.println("Persona agregada a cabina 1 línea Rojo: " + exito2);
        
        boolean cumplenReglas = teleferico.verificarReglasAbordoTodasCabinas();
        System.out.println("Todas las cabinas cumplen las reglas: " + cumplenReglas);
        
        float ingresoTotal = teleferico.calcularIngresoTotal();
        System.out.println("Ingreso total de todas las líneas: " + ingresoTotal + " bs");
        
        teleferico.mostrarLineaConMasIngresoRegular();
        
        System.out.println("Personas en fila:");
        for (Linea linea : teleferico.getLineas()) {
            if (linea != null) {
                System.out.println("Línea " + linea.getColor() + ": " + 
                    linea.getFilaPersonas().length + " personas en fila");
            }
        }
    }
}
