public class Empleado {
    private String nombre;
    private String puesto;
    private Direccion direccion;
    
    // Constructor
    public Empleado(String n, String p, String cDir, String dDir, int nDir) {
        this.nombre = n;
        this.puesto = p;
        this.direccion = new Direccion(cDir, dDir, nDir);
    }
    
    // Constructor alternativo con objeto Direccion
    public Empleado(String n, String p, Direccion dir) {
        this.nombre = n;
        this.puesto = p;
        this.direccion = dir;
    }
    
    // Getters
    public String getNombre() {
        return nombre;
    }
    
    public String getPuesto() {
        return puesto;
    }
    
    public Direccion getDireccion() {
        return direccion;
    }
    
    // Setters
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }
    
    // Método toString
    public String toString() {
        return "Nombre: " + nombre + "\nPuesto: " + puesto + "\nDirección: " + direccion.toString();
    }
}

