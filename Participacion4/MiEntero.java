package Participacion4;
public class MiEntero {
    private int valor;
    public MiEntero(int valor) {
        this.valor = valor;
    }
    
    public int getValor() {
        return valor;
    }
    
    public boolean esPar() {
        return valor % 2 == 0;
    }
    
    public boolean esImpar() {
        return valor % 2 != 0;
    }
    
    public boolean esPrimo() {
        if (valor <= 1) return false;
        if (valor == 2) return true;
        if (valor % 2 == 0) return false;
        
        for (int i = 3; i <= Math.sqrt(valor); i += 2) {
            if (valor % i == 0) return false;
        }
        return true;
    }
    
    public boolean equals(int otroValor) {
        return this.valor == otroValor;
    }
    
    public boolean equals(MiEntero otro) {
        return this.valor == otro.getValor();
    }
    
    
    public static boolean esPar(int valor) {
        return valor % 2 == 0;
    }
    
    public static boolean esImpar(int valor) {
        return valor % 2 != 0;
    }
    
    public static boolean esPrimo(int valor) {
        if (valor <= 1) return false;
        if (valor == 2) return true;
        if (valor % 2 == 0) return false;
        
        for (int i = 3; i <= Math.sqrt(valor); i += 2) {
            if (valor % i == 0) return false;
        }
        return true;
    }
    
    public static boolean esPar(MiEntero entero) {
        return entero.getValor() % 2 == 0;
    }
    
    public static boolean esImpar(MiEntero entero) {
        return entero.getValor() % 2 != 0;
    }
    
    public static boolean esPrimo(MiEntero entero) {
        return esPrimo(entero.getValor());
    }
    
    public static int parseInt(char[] chars) {
        return parseInt(new String(chars));
    }
    
    public static int parseInt(String str) {
        return Integer.parseInt(str);
    }
}