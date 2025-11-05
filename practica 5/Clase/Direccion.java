public class Direccion {
    private String ciudad;
    private String direccion;
    private int numero;
    
    // Constructor
    public Direccion(String c, String d, int n) {
        this.ciudad = c;
        this.direccion = d;
        this.numero = n;
    }
    
    // Getters
    public String getCiudad() {
        return ciudad;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public int getNumero() {
        return numero;
    }
    
    // Setters
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public void setNumero(int numero) {
        this.numero = numero;
    }
    
    // Método toString
    public String toString() {
        return direccion + " #" + numero + ", " + ciudad;
    }
}

