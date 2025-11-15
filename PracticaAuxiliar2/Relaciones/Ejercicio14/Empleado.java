public class Empleado {
    private String nombre;
    private String puesto;
    private double salario;
    
    public Empleado(String nombre, String puesto, double salario) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.salario = salario;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getPuesto() {
        return puesto;
    }
    
    public double getSalario() {
        return salario;
    }
    
    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    @Override
    public String toString() {
        return String.format("%-15s %-20s bs%,.2f", nombre, puesto, salario);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Empleado empleado = (Empleado) obj;
        return nombre.equalsIgnoreCase(empleado.nombre);
    }
}