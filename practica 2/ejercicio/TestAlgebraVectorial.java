package ejercicio;

public class TestAlgebraVectorial {

    public static void main(String[] args) {
        AlgebraVectorial a = new AlgebraVectorial(2, 3);
        AlgebraVectorial b = new AlgebraVectorial(4, 6);

        System.out.println("Vector a: " + a);
        System.out.println("Vector b: " + b);
        System.out.println("a + b: " + a.add(b));
        System.out.println("a - b: " + a.subtract(b));
        System.out.println("a · b: " + a.dot(b));
        System.out.println("a x b: " + a.cross(b));
        System.out.println("¿a y b son perpendiculares?: " + a.esPerpendicular(b));
        System.out.println("¿a y b son paralelos?: " + a.esParalelo(b));
        System.out.println("Proyección de a sobre b: " + a.proyeccionSobre(b));
        System.out.println("Componente de a en b: " + a.componenteEn(b));
    }
}