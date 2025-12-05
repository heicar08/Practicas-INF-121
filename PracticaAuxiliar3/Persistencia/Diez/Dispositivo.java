package Diez;

public class Dispositivo {
    private String mac;
    private String nombre;
    private int velocidad;

    public Dispositivo(String mac, String nombre, int velocidad) {
        this.mac = mac;
        this.nombre = nombre;
        this.velocidad = velocidad;
    }

    public String getMac() {
        return mac;
    }

    public String getNombre() {
        return nombre;
    }

    public int getVelocidad() {
        return velocidad;
    }

    @Override
    public String toString() {
        return mac + " - " + nombre + " - " + velocidad + " Mbps";
    }
}