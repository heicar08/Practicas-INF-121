import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private List<Empleado> empleados;
    
    // Constructor
    public Empresa(String n) {
        this.nombre = n;
        this.empleados = new ArrayList<Empleado>();
    }
    
    // Getter para nombre
    public String getNombre() {
        return nombre;
    }
    
    // Getter para lista de empleados
    public List<Empleado> getEmpleados() {
        return empleados;
    }
    
    // Método para contratar empleado
    public void contratar(Empleado empl) {
        empleados.add(empl);
    }
    
    // Método para listar empleados
    public void listar_empleados() {
        System.out.println("=== EMPLEADOS DE " + nombre.toUpperCase() + " ===");
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados contratados.");
        } else {
            for (int i = 0; i < empleados.size(); i++) {
                System.out.println("\nEmpleado #" + (i + 1) + ":");
                System.out.println(empleados.get(i).toString());
                System.out.println("---");
            }
        }
    }
    
    // Método para obtener cantidad de empleados
    public int getCantidadEmpleados() {
        return empleados.size();
    }
}

