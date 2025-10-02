package ejercicio1.Empleado;

public abstract class Empleado {
    protected String nombre;
    
    public Empleado(String nombre) {
        this.nombre = nombre;
    }
    
    public abstract double calcularSalarioMensual();
    
    @Override
    public String toString() {
        return "Empleado: " + nombre;
    }
    
    public String getNombre() {
        return nombre;
    }
}