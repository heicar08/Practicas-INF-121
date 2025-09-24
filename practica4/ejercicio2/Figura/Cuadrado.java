package ejercicio2.Figura;

public class Cuadrado extends Figura implements Coloreado {
    private double lado;
    
    public Cuadrado(double lado, String color) {
        super(color);
        this.lado = lado;
    }
    
    public Cuadrado(double lado) {
        this(lado, "negro");
    }
    
    @Override
    public double area() {
        return lado * lado;
    }
    
    @Override
    public double perimetro() {
        return 4 * lado;
    }
    
    @Override
    public String comoColorear() {
        return "Colorear los cuatro lados";
    }
    
    @Override
    public String toString() {
        return "Cuadrado - Lado: " + String.format("%.2f", lado) + ", Color: " + color;
    }
}