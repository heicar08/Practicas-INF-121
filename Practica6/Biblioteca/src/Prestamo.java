import java.io.Serializable;
import java.util.Date;

public class Prestamo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String idPrestamo;
    private Date fechaPrestamo;
    private Date fechaDevolucion;
    private String horaPrestamo;
    private boolean activo;
    private Estudiante estudiante;
    private Libro libro;

    // crear nuevos préstamos
    public Prestamo(Estudiante estudiante, Libro libro) {
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = new Date();
        this.fechaDevolucion = null;
        this.horaPrestamo = new Date().toString();
        this.activo = true;
        this.idPrestamo = "P" + System.currentTimeMillis();
    }

    // Constructor para crear préstamos con ID específico
    public Prestamo(String idPrestamo, Estudiante estudiante, Libro libro) {
        this.idPrestamo = idPrestamo;
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaPrestamo = new Date();
        this.fechaDevolucion = null;
        this.horaPrestamo = new Date().toString();
        this.activo = true;
    }


    public void mostrarInfo() {
        System.out.println("Préstamo:");
        System.out.println("  Estudiante: " + estudiante.getNombre());
        System.out.println("  Libro: " + libro.getTitulo());
        System.out.println("  Fecha de préstamo: " + fechaPrestamo);
        System.out.println("  Fecha de devolución: " + (fechaDevolucion != null ? fechaDevolucion : "Pendiente"));
    }

    // ELIMINAR: Registra la devolución de un libro prestado
    public void registrarDevolucion() {
        this.fechaDevolucion = new Date();
        this.activo = false;
        if (libro != null) {
            libro.marcarDisponible();
        }
        System.out.println("Devolución registrada para el préstamo: " + idPrestamo);
    }

    public int calcularDiasPrestamo() {
        if (fechaDevolucion != null) {
            long diff = fechaDevolucion.getTime() - fechaPrestamo.getTime();
            return (int) (diff / (1000 * 60 * 60 * 24));
        }
        return 0;
    }

    public String getIdPrestamo() { return idPrestamo; }
    public void setIdPrestamo(String idPrestamo) { this.idPrestamo = idPrestamo; }

    public Date getFechaPrestamo() { return fechaPrestamo; }
    public void setFechaPrestamo(Date fechaPrestamo) { this.fechaPrestamo = fechaPrestamo; }

    public Date getFechaDevolucion() { return fechaDevolucion; }
    public void setFechaDevolucion(Date fechaDevolucion) { this.fechaDevolucion = fechaDevolucion; }

    public String getHoraPrestamo() { return horaPrestamo; }
    public void setHoraPrestamo(String horaPrestamo) { this.horaPrestamo = horaPrestamo; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public Estudiante getEstudiante() { return estudiante; }
    public void setEstudiante(Estudiante estudiante) { this.estudiante = estudiante; }

    public Libro getLibro() { return libro; }
    public void setLibro(Libro libro) { this.libro = libro; }
}