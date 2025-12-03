import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private List<Libro> catalogo;
    private List<Autor> autores;
    private List<Prestamo> prestamos;
    private Horario horario;
    private String direccion;
    private List<Estudiante> estudiantes;
    private int contadorPrestamos;

    // PERSISTENCIA: Clase interna Horario que también implementa Serializable para persistencia
    public static class Horario implements Serializable {
        private static final long serialVersionUID = 1L;
        private String diasApertura;
        private String horaApertura;
        private String horaCierre;

        public Horario(String diasApertura, String horaApertura, String horaCierre) {
            this.diasApertura = diasApertura;
            this.horaApertura = horaApertura;
            this.horaCierre = horaCierre;
        }

        public void mostrarHorario() {
            System.out.println("Horario de atención:");
            System.out.println("  Días: " + diasApertura);
            System.out.println("  Hora de apertura: " + horaApertura);
            System.out.println("  Hora de cierre: " + horaCierre);
        }

        public String getDiasApertura() { return diasApertura; }
        public String getHoraApertura() { return horaApertura; }
        public String getHoraCierre() { return horaCierre; }
    }

    public Biblioteca(String nombre) {
        this.nombre = nombre;
        this.catalogo = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
        this.contadorPrestamos = 1;
        this.direccion = "";
        this.horario = new Horario("Lunes a Viernes", "08:00", "18:00");
    }

    public Biblioteca(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.catalogo = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.prestamos = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
        this.contadorPrestamos = 1;
        this.horario = new Horario("Lunes a Viernes", "08:00", "18:00");
    }

    // AGREGAR: Agrega un nuevo libro al catálogo de la biblioteca
    public void agregarLibro(Libro libro) {
        catalogo.add(libro);
        System.out.println("Libro agregado: " + libro.getTitulo());
    }

    // AGREGAR: Registra un nuevo autor en la biblioteca si no existe
    public void agregarAutor(Autor autor) {
        if (!autores.contains(autor)) {
            autores.add(autor);
            System.out.println(" registrado: " + autor.getNombre());
        }
    }

    // AGREGAR: Crea un nuevo préstamo de libro a estudiante
    public Prestamo prestarLibro(Estudiante estudiante, Libro libro) {
        if (libro != null && libro.isDisponible()) {
            Prestamo prestamo = new Prestamo(estudiante, libro);
            prestamos.add(prestamo);
            libro.marcarPrestado();
            System.out.println("Préstamo realizado: " + libro.getTitulo() + " a " + estudiante.getNombre());
            return prestamo;
        }
        System.out.println("No se pudo realizar el préstamo - Libro no disponible");
        return null;
    }

    public void mostrarEstado() {
        System.out.println("\n ESTADO DE LA BIBLIOTECA: " + nombre + "");
        System.out.println("Total de libros: " + catalogo.size());
        System.out.println("Total de autores registrados: " + autores.size());
        System.out.println("Total de préstamos activos: " + prestamos.size());
        if (horario != null) {
            horario.mostrarHorario();
        }
        System.out.println("==========================================\n");
    }

    // ELIMINAR: Cierra la biblioteca y limpia todos los préstamos activos
    public void cerrarBiblioteca() {
        System.out.println("La biblioteca " + nombre + " está cerrando...");
        prestamos.clear();
        System.out.println("Todos los préstamos han sido cancelados.");
        System.out.println("Biblioteca cerrada.");
    }

    public Libro buscarLibro(String titulo) {
        for (Libro libro : catalogo) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        System.out.println("Libro no encontrado: " + titulo);
        return null;
    }

    public boolean verificarDisponibilidad(Libro libro) {
        return libro.isDisponible();
    }

    public Prestamo crearPrestamo(Estudiante estudiante, Libro libro) {
        Prestamo prestamo = prestarLibro(estudiante, libro);
        if (prestamo != null) {
            prestamo.setIdPrestamo("P" + contadorPrestamos++);
        }
        return prestamo;
    }

    // ELIMINAR: Procesa la devolución de un libro prestado
    public void procesarDevolucion(Prestamo prestamo) {
        prestamo.registrarDevolucion();
        System.out.println("Devolución procesada para el préstamo: " + prestamo.getIdPrestamo());
    }

    public void generarReportePrestamos() {
        System.out.println("\n=== REPORTE DE PRÉSTAMOS ===");
        System.out.println("Total de préstamos: " + prestamos.size());
        int activos = 0;
        for (Prestamo p : prestamos) {
            if (p.isActivo()) {
                activos++;
                System.out.println("Préstamo activo: " + p.getIdPrestamo() + " - " +
                        p.getLibro().getTitulo() + " - " + p.getEstudiante().getNombre());
            }
        }
        System.out.println("Préstamos activos: " + activos);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public List<Libro> getCatalogo() { return catalogo; }
    public List<Prestamo> getPrestamos() { return prestamos; }
    public List<Estudiante> getEstudiantes() { return estudiantes; }
    public List<Autor> getAutores() { return autores; }
    public Horario getHorario() { return horario; }
    public void setHorario(Horario horario) { this.horario = horario; }

    // AGREGAR: Agrega un nuevo estudiante a la biblioteca
    public void agregarEstudiante(Estudiante estudiante) {
        estudiantes.add(estudiante);
    }

    public int getContadorPrestamos() { return contadorPrestamos; }
    public void setContadorPrestamos(int contador) { this.contadorPrestamos = contador; }
}