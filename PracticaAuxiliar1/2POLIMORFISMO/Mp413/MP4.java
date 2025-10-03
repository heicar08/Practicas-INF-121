package PracticaAuxiliar1.POLIMORFISMO2.Mp413;

public class MP4 {
    private String marca;
    private double capacidadGb;
    private int nroCanciones;
    private String[][] canciones;
    private int nroVideos;
    private String[][] videos;
    
    public MP4(String marca, double capacidadGb) {
        this.marca = marca;
        this.capacidadGb = capacidadGb;
        this.nroCanciones = 0;
        this.nroVideos = 0;
        this.canciones = new String[3][100];
        this.videos = new String[100][3];
    }
    
    public void borrar(String nombre) {
        for (int i = 0; i < nroCanciones; i++) {
            if (canciones[0][i] != null && canciones[0][i].equals(nombre)) {
                eliminarCancion(i);
                System.out.println("Canción '" + nombre + "' eliminada");
                return;
            }
        }
        System.out.println("Canción '" + nombre + "' no encontrada");
    }
    
    public void borrar(String nombre, String artista) {
        for (int i = 0; i < nroCanciones; i++) {
            if (canciones[0][i] != null && canciones[0][i].equals(nombre) && 
                canciones[1][i] != null && canciones[1][i].equals(artista)) {
                eliminarCancion(i);
                System.out.println("Canción '" + nombre + "' de " + artista + " eliminada");
                return;
            }
        }
        System.out.println("Canción '" + nombre + "' de " + artista + " no encontrada");
    }
    
    public void borrar(String nombre, String artista, String peso) {
        for (int i = 0; i < nroCanciones; i++) {
            if (canciones[0][i] != null && canciones[0][i].equals(nombre) && 
                canciones[1][i] != null && canciones[1][i].equals(artista) &&
                canciones[2][i] != null && canciones[2][i].equals(peso)) {
                eliminarCancion(i);
                System.out.println("Canción '" + nombre + "' de " + artista + " (" + peso + "KB) eliminada");
                return;
            }
        }
        System.out.println("Canción no encontrada con los criterios especificados");
    }
    
    private void eliminarCancion(int index) {
        for (int j = index; j < nroCanciones - 1; j++) {
            canciones[0][j] = canciones[0][j + 1];
            canciones[1][j] = canciones[1][j + 1];
            canciones[2][j] = canciones[2][j + 1];
        }
        canciones[0][nroCanciones - 1] = null;
        canciones[1][nroCanciones - 1] = null;
        canciones[2][nroCanciones - 1] = null;
        nroCanciones--;
    }
    
    public MP4 agregarCancion(String nombre, String artista, double pesoKb) {
        if (nroCanciones >= 100) {
            System.out.println("No hay espacio para más canciones");
            return this;
        }
        
        for (int i = 0; i < nroCanciones; i++) {
            if (canciones[0][i] != null && canciones[0][i].equals(nombre) && 
                canciones[1][i] != null && canciones[1][i].equals(artista)) {
                System.out.println("La canción ya existe");
                return this;
            }
        }
        
        double espacioNecesario = pesoKb / 1024 / 1024;
        if (calcularEspacioUsado() + espacioNecesario > capacidadGb) {
            System.out.println("No hay suficiente espacio");
            return this;
        }
        
        canciones[0][nroCanciones] = nombre;
        canciones[1][nroCanciones] = artista;
        canciones[2][nroCanciones] = String.valueOf(pesoKb);
        nroCanciones++;
        System.out.println("Canción '" + nombre + "' agregada");
        return this;
    }
    
    public MP4 agregarVideo(String nombre, String artista, double pesoMb) {
        if (nroVideos >= 100) {
            System.out.println("No hay espacio para más videos");
            return this;
        }
        
        for (int i = 0; i < nroVideos; i++) {
            if (videos[i][0] != null && videos[i][0].equals(nombre) && 
                videos[i][1] != null && videos[i][1].equals(artista)) {
                System.out.println("El video ya existe");
                return this;
            }
        }
        
        double espacioNecesario = pesoMb / 1024;
        if (calcularEspacioUsado() + espacioNecesario > capacidadGb) {
            System.out.println("No hay suficiente espacio");
            return this;
        }
        
        videos[nroVideos][0] = nombre;
        videos[nroVideos][1] = artista;
        videos[nroVideos][2] = String.valueOf(pesoMb);
        nroVideos++;
        System.out.println("Video '" + nombre + "' agregado");
        return this;
    }
    
    public double calcularEspacioUsado() {
        double espacioUsado = 0;
        
        for (int i = 0; i < nroCanciones; i++) {
            if (canciones[2][i] != null) {
                espacioUsado += Double.parseDouble(canciones[2][i]) / 1024 / 1024;
            }
        }
        
        for (int i = 0; i < nroVideos; i++) {
            if (videos[i][2] != null) {
                espacioUsado += Double.parseDouble(videos[i][2]) / 1024;
            }
        }
        
        return espacioUsado;
    }
    
    public void mostrarCapacidad() {
        double espacioUsado = calcularEspacioUsado();
        double espacioDisponible = capacidadGb - espacioUsado;
        
        System.out.println("=== CAPACIDAD MP4 ===");
        System.out.println("Marca: " + marca);
        System.out.println("Capacidad total: " + capacidadGb + " GB");
        System.out.println("Espacio usado: " + String.format("%.4f", espacioUsado) + " GB");
        System.out.println("Espacio disponible: " + String.format("%.4f", espacioDisponible) + " GB");
        System.out.println("Número de canciones: " + nroCanciones);
        System.out.println("Número de videos: " + nroVideos);
    }
    
    public void mostrarCanciones() {
        System.out.println("=== CANCIONES ===");
        if (nroCanciones == 0) {
            System.out.println("No hay canciones");
            return;
        }
        for (int i = 0; i < nroCanciones; i++) {
            System.out.println((i + 1) + ". " + canciones[0][i] + " - " + 
                             canciones[1][i] + " (" + canciones[2][i] + " KB)");
        }
    }
    
    public void mostrarVideos() {
        System.out.println("=== VIDEOS ===");
        if (nroVideos == 0) {
            System.out.println("No hay videos");
            return;
        }
        for (int i = 0; i < nroVideos; i++) {
            System.out.println((i + 1) + ". " + videos[i][0] + " - " + 
                             videos[i][1] + " (" + videos[i][2] + " MB)");
        }
    }
}