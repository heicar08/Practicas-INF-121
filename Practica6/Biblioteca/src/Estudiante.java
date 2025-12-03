import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Estudiante implements Serializable {
    private static final long serialVersionUID = 1L;
    private String codigoEstudiante;
    private String nombre;
    private String idEstudiante;
    private String email;
    private String carrera;
    private boolean activo;
    private List<Prestamo> prestamos;

    // Constructor principal para crear estudiantes según especificaciones
    public Estudiante(String codigoEstudiante, String nombre) {
        this.codigoEstudiante = codigoEstudiante;
        this.nombre = nombre;
        this.idEstudiante = codigoEstudiante;
        this.email = "";
        this.carrera = "";
        this.activo = true;
        this.prestamos = new ArrayList<>();
    }

    // Constructor para crear estudiantes con información completa
    public Estudiante(String nombre, String idEstudiante, String email, String carrera) {
        this.nombre = nombre;
        this.codigoEstudiante = idEstudiante;
        this.idEstudiante = idEstudiante;
        this.email = email;
        this.carrera = carrera;
        this.activo = true;
        this.prestamos = new ArrayList<>();
    }

    // Muestra información básica del estudiante en consola
    public void mostrarInfo() {
        System.out.println("Estudiante: " + nombre + " | Código: " + codigoEstudiante);
    }

    // AGREGAR: Registra al estudiante en el sistema
    public void registrarse() {
        System.out.println("Estudiante " + nombre + " registrado exitosamente en el sistema");
    }

    // AGREGAR: Solicita un préstamo de libro en la biblioteca
    public Prestamo solicitarPrestamo(Libro libro, Biblioteca biblioteca) {
        if (!libro.isDisponible()) {
            System.out.println("El libro '" + libro.getTitulo() + "' no está disponible");
            return null;
        }

        Prestamo prestamo = biblioteca.crearPrestamo(this, libro);
        if (prestamo != null) {
            prestamos.add(prestamo);
            libro.marcarPrestado();
        }
        return prestamo;
    }

    // ELIMINAR: Devuelve un libro prestado y elimina el préstamo de la lista
    public void devolverLibro(Prestamo prestamo, Biblioteca biblioteca) {
        if (prestamos.contains(prestamo)) {
            biblioteca.procesarDevolucion(prestamo);
            prestamos.remove(prestamo);
            System.out.println("Libro devuelto exitosamente");
        } else {
            System.out.println("Este préstamo no pertenece al estudiante");
        }
    }

    // Obtiene información completa del estudiante como String
    public String obtenerInfoEstudiante() {
        return "Estudiante: " + nombre + " | ID: " + idEstudiante + " | Carrera: " + carrera +
                " | Préstamos activos: " + prestamos.size();
    }

    // Getters y Setters
    public String getCodigoEstudiante() { return codigoEstudiante; }
    public void setCodigoEstudiante(String codigoEstudiante) {
        this.codigoEstudiante = codigoEstudiante;
        this.idEstudiante = codigoEstudiante;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdEstudiante() { return idEstudiante; }
    public void setIdEstudiante(String idEstudiante) {
        this.idEstudiante = idEstudiante;
        this.codigoEstudiante = idEstudiante;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public List<Prestamo> getPrestamos() { return prestamos; }
}