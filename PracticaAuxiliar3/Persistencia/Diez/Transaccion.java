package Diez;

public class Transaccion {
    private String id;
    private double monto;
    private String fecha;

    public Transaccion(String id, double monto, String fecha) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
    }

    public String getId() {
        return id;
    }

    public double getMonto() {
        return monto;
    }

    public String getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        return "ID: " + id + " - Monto: $" + monto + " - Fecha: " + fecha;
    }
}