package ejercicio2.Figura;

import java.util.Random;

public class SistemaFiguras {
    public static void main(String[] args) {
        Random random = new Random();
        Figura[] figuras = new Figura[5];
        String[] colores = {"rojo", "azul", "verde", "amarillo", "naranja", "morado"};
        
        System.out.println("=== GENERACIÓN ALEATORIA DE FIGURAS ===");
        
        for (int i = 0; i < figuras.length; i++) {
            int tipoFigura = random.nextInt(2) + 1;
            String color = colores[random.nextInt(colores.length)];
            
            if (tipoFigura == 1) {
                double lado = 1 + random.nextDouble() * 9;
                figuras[i] = new Cuadrado(lado, color);
                System.out.printf("Figura %d: Cuadrado - Lado: %.2f, Color: %s%n", 
                                 i + 1, lado, color);
            } else {
                double radio = 1 + random.nextDouble() * 9;
                figuras[i] = new Circulo(radio, color);
                System.out.printf("Figura %d: Círculo - Radio: %.2f, Color: %s%n", 
                                 i + 1, radio, color);
            }
        }
        
        System.out.println("\n=== INFORMACIÓN DE FIGURAS ===");
        for (int i = 0; i < figuras.length; i++) {
            System.out.printf("%n%d. %s%n", i + 1, figuras[i]);
            System.out.printf("   Área: %.2f%n", figuras[i].area());
            System.out.printf("   Perímetro: %.2f%n", figuras[i].perimetro());
            
            if (figuras[i] instanceof Coloreado) {
                Coloreado figuraColoreable = (Coloreado) figuras[i];
                System.out.printf("   Método colorear: %s%n", figuraColoreable.comoColorear());
            }
        }
    }
}