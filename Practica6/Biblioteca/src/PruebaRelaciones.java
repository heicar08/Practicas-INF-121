/**
 * Clase de prueba para demostrar las tres relaciones:
 * - COMPOSICIÓN: Libro-Pagina, Biblioteca-Horario
 * - AGREGACIÓN: Biblioteca-Libro, Biblioteca-Autor
 * - ASOCIACIÓN: Prestamo-Estudiante, Prestamo-Libro
 */
public class PruebaRelaciones {
    public static void main(String[] args) {
        System.out.println("=== DEMOSTRACIÓN DE RELACIONES ===\n");

        // 1. COMPOSICIÓN: Biblioteca - Horario
        System.out.println("1. COMPOSICIÓN: Biblioteca - Horario");
        System.out.println("   (El horario no puede existir sin la biblioteca)");
        Biblioteca biblioteca = new Biblioteca("Biblioteca Central");
        biblioteca.getHorario().mostrarHorario();
        System.out.println();

        // 2. COMPOSICIÓN: Libro - Pagina
        System.out.println("2. COMPOSICIÓN: Libro - Pagina");
        System.out.println("   (Las páginas no pueden existir sin el libro)");
        java.util.List<String> contenido = new java.util.ArrayList<>();
        contenido.add("Esta es la primera página del libro.");
        contenido.add("Esta es la segunda página del libro.");
        contenido.add("Esta es la tercera página del libro.");
        Libro libro1 = new Libro("El Quijote", "ISBN-001", contenido);
        libro1.leer();
        System.out.println();

        // 3. AGREGACIÓN: Biblioteca - Libro
        System.out.println("3. AGREGACIÓN: Biblioteca - Libro");
        System.out.println("   (Los libros pueden existir fuera de la biblioteca)");
        Libro libro2 = new Libro("Cien Años de Soledad", "ISBN-002", contenido);
        System.out.println("Libro creado independientemente: " + libro2.getTitulo());
        // AGREGAR: Añade libro a la biblioteca
        biblioteca.agregarLibro(libro2);
        System.out.println("Libro agregado a la biblioteca.");
        System.out.println();

        // 4. AGREGACIÓN: Biblioteca - Autor
        System.out.println("4. AGREGACIÓN: Biblioteca - Autor");
        System.out.println("   (Los autores existen independientemente de la biblioteca)");
        Autor autor1 = new Autor("Gabriel García Márquez", "Colombiana");
        autor1.mostrarInfo();
        System.out.println("Autor creado independientemente.");
        // AGREGAR: Registra autor en la biblioteca
        biblioteca.agregarAutor(autor1);
        System.out.println("Autor registrado en la biblioteca.");
        System.out.println();

        // 5. ASOCIACIÓN: Prestamo - Estudiante y Prestamo - Libro
        System.out.println("5. ASOCIACIÓN: Prestamo - Estudiante y Prestamo - Libro");
        System.out.println("   (El préstamo asocia un estudiante con un libro)");
        Estudiante estudiante1 = new Estudiante("E001", "Juan Pérez");
        estudiante1.mostrarInfo();
        // AGREGAR: Crea un préstamo asociando estudiante y libro
        Prestamo prestamo = biblioteca.prestarLibro(estudiante1, libro2);
        if (prestamo != null) {
            prestamo.mostrarInfo();
        }
        System.out.println();


        System.out.println(" ESTADO FINAL DE LA BIBLIOTECA ");
        biblioteca.mostrarEstado();

        // ELIMINAR: Cierra la biblioteca y limpia todos los préstamos
        System.out.println(" CIERRE DE BIBLIOTECA ");
        biblioteca.cerrarBiblioteca();
    }
}