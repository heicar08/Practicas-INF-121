import java.util.ArrayList;
import java.util.List;

public class Empresa {
    private String nombre;
    private List<Empleado> empleados;
    
    public Empresa(String nombre) {
        this.nombre = nombre;
        this.empleados = new ArrayList<>();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void agregarEmpleado(Empleado empleado) {
        if (!empleados.contains(empleado)) {
            empleados.add(empleado);
            System.out.println("Empleado " + empleado.getNombre() + " agregado a la empresa " + nombre);
        } else {
            System.out.println("El empleado " + empleado.getNombre() + " ya existe en la empresa");
        }
    }
    
    public void mostrarInformacion() {
        System.out.println("\n=== INFORMACIÓN DE LA EMPRESA: " + nombre + " ===");
        System.out.println("Total de empleados: " + empleados.size());
        System.out.println("\n--- LISTA DE EMPLEADOS ---");
        System.out.printf("%-15s %-20s %s\n", "NOMBRE", "PUESTO", "SALARIO");
        System.out.println("------------------------------------------------");
        
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados en la empresa");
        } else {
            for (int i = 0; i < empleados.size(); i++) {
                System.out.println((i + 1) + ". " + empleados.get(i));
            }
        }
    }
    
    public Empleado buscarEmpleado(String nombre) {
        for (Empleado empleado : empleados) {
            if (empleado.getNombre().equalsIgnoreCase(nombre)) {
                return empleado;
            }
        }
        return null;
    }
    
    public boolean eliminarEmpleado(String nombre) {
        Empleado empleadoAEliminar = null;
        for (Empleado empleado : empleados) {
            if (empleado.getNombre().equalsIgnoreCase(nombre)) {
                empleadoAEliminar = empleado;
                break;
            }
        }
        
        if (empleadoAEliminar != null) {
            empleados.remove(empleadoAEliminar);
            System.out.println("Empleado " + nombre + " eliminado de la empresa");
            return true;
        } else {
            System.out.println("Empleado " + nombre + " no encontrado en la empresa");
            return false;
        }
    }
    
    public double calcularPromedioSalarial() {
        if (empleados.isEmpty()) {
            return 0.0;
        }
        
        double totalSalarios = 0;
        for (Empleado empleado : empleados) {
            totalSalarios += empleado.getSalario();
        }
        
        return totalSalarios / empleados.size();
    }
    
    public void mostrarEmpleadosSalarioMayorA(double salarioMinimo) {
        System.out.println("\n=== EMPLEADOS CON SALARIO MAYOR A bs" + salarioMinimo + " ===");
        System.out.printf("%-15s %-20s %s\n", "NOMBRE", "PUESTO", "SALARIO");
        System.out.println("------------------------------------------------");
        
        boolean encontrados = false;
        for (Empleado empleado : empleados) {
            if (empleado.getSalario() > salarioMinimo) {
                System.out.println(empleado);
                encontrados = true;
            }
        }
        
        if (!encontrados) {
            System.out.println("No se encontraron empleados con salario mayor a bs" + salarioMinimo);
        }
    }
    
    public int getCantidadEmpleados() {
        return empleados.size();
    }
}