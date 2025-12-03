import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Libro implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String isbn;
    private String descripcion;
    private String genero;
    private int añoPublicacion;
    private boolean disponible;
    private Autor autor;
    private List<Pagina> paginas;

    // PERSISTENCIA: Clase interna Pagina que también implementa Serializable para persistencia
    public static class Pagina implements Serializable {
        private static final long serialVersionUID = 1L;
        private int numeroPagina;
        private String contenido;

        public Pagina(int numeroPagina, String contenido) {
            this.numeroPagina = numeroPagina;
            this.contenido = contenido;
        }

        public void mostrarContenido() {
            System.out.println("--- Página " + numeroPagina + " ---");
            System.out.println(contenido);
        }

        public int getNumeroPagina() { return numeroPagina; }
        public String getContenido() { return contenido; }
    }

    public Libro(String titulo, String isbn, List<String> contenidoPaginas) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.paginas = new ArrayList<>();
        this.disponible = true;
        for (int i = 0; i < contenidoPaginas.size(); i++) {
            this.paginas.add(new Pagina(i + 1, contenidoPaginas.get(i)));
        }
        this.descripcion = "";
        this.genero = "";
        this.añoPublicacion = 0;
        this.autor = null;
    }

    public Libro(String titulo, String isbn, String descripcion, String genero, int añoPublicacion, Autor autor) {
        this.titulo = titulo;
        this.isbn = isbn;
        this.descripcion = descripcion;
        this.genero = genero;
        this.añoPublicacion = añoPublicacion;
        this.disponible = true;
        this.autor = autor;
        this.paginas = new ArrayList<>();
        if (descripcion != null && !descripcion.isEmpty()) {
            this.paginas.add(new Pagina(1, descripcion));
        }
    }

    public void leer() {
        System.out.println("=== Leyendo libro: " + titulo + " ===");
        if (paginas != null && !paginas.isEmpty()) {
            for (Pagina pagina : paginas) {
                pagina.mostrarContenido();
            }
        } else {
            System.out.println("El libro no tiene páginas.");
        }
    }

    // Marcar el libro como prestado (no disponible)
    public void marcarPrestado() {
        this.disponible = false;
        System.out.println("Libro '" + titulo + "' marcado como prestado");
    }

    // Marcar el libro como disponible para préstamo
    public void marcarDisponible() {
        this.disponible = true;
        System.out.println("Libro '" + titulo + "' marcado como disponible");
    }

    public String obtenerInfoLibro() {
        String info = "Título: " + titulo + " | ISBN: " + isbn;
        if (autor != null) {
            info += " | Autor: " + autor.getNombre();
        }
        info += " | Disponible: " + (disponible ? "Sí" : "No");
        return info;
    }

    // Getters y Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public int getAñoPublicacion() { return añoPublicacion; }
    public void setAñoPublicacion(int añoPublicacion) { this.añoPublicacion = añoPublicacion; }

    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    public List<Pagina> getPaginas() { return paginas; }
    public void setPaginas(List<Pagina> paginas) { this.paginas = paginas; }

    // AGREGAR: Añade una nueva página al libro
    public void agregarPagina(int numero, String contenido) {
        if (paginas == null) {
            paginas = new ArrayList<>();
        }
        paginas.add(new Pagina(numero, contenido));
    }
}