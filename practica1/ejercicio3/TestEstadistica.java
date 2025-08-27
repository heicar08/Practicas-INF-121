package ejercicio3;

import java.util.Scanner;

public class TestEstadistica {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double[] numeros = new double[10];
        
        System.out.print("Ingrese 10 números separados por espacios: ");
        for (int i = 0; i < 10; i++) {
            numeros[i] = scanner.nextDouble();
        }
        
        Estadistica stats = new Estadistica(numeros);
        double prom = stats.promedio();
        double dev = stats.desviacion();
        
        System.out.println("El promedio es " + String.format("%.2f", prom));
        System.out.println("La desviación estándar es " + String.format("%.5f", dev));
        
        scanner.close();
    }
}
