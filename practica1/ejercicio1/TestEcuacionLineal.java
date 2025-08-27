package ejercicio1;
import java.util.Scanner;

public class TestEcuacionLineal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese a: ");
        double a = scanner.nextDouble();
        System.out.print("Ingrese b: ");
        double b = scanner.nextDouble();
        System.out.print("Ingrese c: ");
        double c = scanner.nextDouble();
        System.out.print("Ingrese d: ");
        double d = scanner.nextDouble();
        System.out.print("Ingrese e: ");
        double e = scanner.nextDouble();
        System.out.print("Ingrese f: ");
        double f = scanner.nextDouble();
        
        EcuacionLineal ecuacion = new EcuacionLineal(a, b, c, d, e, f);
        
        if (ecuacion.tieneSolucion()) {
            System.out.println("x = " + ecuacion.getX() + ", y = " + ecuacion.getY());
        } else {
            System.out.println("La ecuación no tiene solución");
        }
        
        scanner.close();
    }
}