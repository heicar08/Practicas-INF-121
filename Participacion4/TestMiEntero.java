package Participacion4;
public class TestMiEntero {
    public static void main(String[] args) {
        MiEntero num1 = new MiEntero(7);
        MiEntero num2 = new MiEntero(12);
        MiEntero num3 = new MiEntero(29);
        
        System.out.println("=== MÉTODOS DE INSTANCIA ===");
        System.out.println("num1 (" + num1.getValor() + "):");
        System.out.println("  Es par: " + num1.esPar());
        System.out.println("  Es impar: " + num1.esImpar());
        System.out.println("  Es primo: " + num1.esPrimo());
        
        System.out.println("\nnum2 (" + num2.getValor() + "):");
        System.out.println("  Es par: " + num2.esPar());
        System.out.println("  Es impar: " + num2.esImpar());
        System.out.println("  Es primo: " + num2.esPrimo());
        
        System.out.println("\nnum3 (" + num3.getValor() + "):");
        System.out.println("  Es par: " + num3.esPar());
        System.out.println("  Es impar: " + num3.esImpar());
        System.out.println("  Es primo: " + num3.esPrimo());
        
        System.out.println("\n=== MÉTODOS EQUALS ===");
        System.out.println("num1 equals 7: " + num1.equals(7));
        System.out.println("num1 equals num2: " + num1.equals(num2));
        System.out.println("num1 equals num3: " + num1.equals(num3));
        
        System.out.println("\n=== MÉTODOS ESTÁTICOS (int) ===");
        System.out.println("esPar(15): " + MiEntero.esPar(15));
        System.out.println("esImpar(15): " + MiEntero.esImpar(15));
        System.out.println("esPrimo(15): " + MiEntero.esPrimo(15));
        
        System.out.println("\n=== MÉTODOS ESTÁTICOS (MiEntero) ===");
        System.out.println("esPar(num2): " + MiEntero.esPar(num2));
        System.out.println("esImpar(num2): " + MiEntero.esImpar(num2));
        System.out.println("esPrimo(num2): " + MiEntero.esPrimo(num2));
        
        System.out.println("\n=== MÉTODOS DE CONVERSIÓN ===");
        char[] chars = {'1', '2', '3'};
        String str = "456";
        
        System.out.println("parseInt(['1','2','3']): " + MiEntero.parseInt(chars));
        System.out.println("parseInt(\"456\"): " + MiEntero.parseInt(str));
    }
}
