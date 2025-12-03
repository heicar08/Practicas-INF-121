import java.io.Serializable;

public class Autor implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String nacionalidad;
    private String idAutor;

    // Constructor principal para crear nuevos autores
    public Autor(String nombre, String nacionalidad) {
        this.nombre = nombre;
        this.nacionalidad = nacionalidad;
        this.idAutor = "A" + System.currentTimeMillis();
    }

    // Constructor para cargar autores existentes con ID específico
    public Autor(String nombre, String idAutor, String nacionalidad) {
        this.nombre = nombre;
        this.idAutor = idAutor;
        this.nacionalidad = nacionalidad;
    }

    // Muestra información del autor en consola
    public void mostrarInfo() {
        System.out.println("Autor: " + nombre + " | Nacionalidad: " + nacionalidad);
    }


    public void registrarLibro(Libro libro, Biblioteca biblioteca) {
        biblioteca.agregarLibro(libro);
        System.out.println("Libro '" + libro.getTitulo() + "' registrado por " + this.nombre);
    }


    public String obtenerInfoAutor() {
        return "Autor: " + nombre + " | ID: " + idAutor + " | Nacionalidad: " + nacionalidad;
    }


    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdAutor() { return idAutor; }
    public void setIdAutor(String idAutor) { this.idAutor = idAutor; }

    public String getNacionalidad() { return nacionalidad; }
    public void setNacionalidad(String nacionalidad) { this.nacionalidad = nacionalidad; }
}