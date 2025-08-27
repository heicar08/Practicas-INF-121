package ejercicio2;
import java.util.Scanner;

public class TestEcuacionCuadratica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Ingrese a: ");
        double a = scanner.nextDouble();
        System.out.print("Ingrese b: ");
        double b = scanner.nextDouble();
        System.out.print("Ingrese 1c: ");
        double c = scanner.nextDouble();
        
        EcuacionCuadratica ecuacion = new EcuacionCuadratica(a, b, c);
        double discriminante = ecuacion.getDiscriminante();
        
        if (discriminante > 0) {
            double raiz1 = ecuacion.getRaiz1();
            double raiz2 = ecuacion.getRaiz2();
            System.out.println("La ecuación tiene dos raíces " + raiz1 + " y " + raiz2);
        } else if (discriminante == 0) {
            double raiz = ecuacion.getRaiz1();
            System.out.println("La ecuación tiene una raíz " + raiz);
        } else {
            System.out.println("La ecuación no tiene raíces reales");
        }
        
        scanner.close();
    }
}