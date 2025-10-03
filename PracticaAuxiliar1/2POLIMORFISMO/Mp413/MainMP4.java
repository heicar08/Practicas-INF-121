package PracticaAuxiliar1.POLIMORFISMO2.Mp413;

public class MainMP4 {
    public static void main(String[] args) {
        MP4 mp4 = new MP4("Sony", 16.0);
        
        System.out.println("=== AGREGANDO CONTENIDO ===");
        mp4.agregarCancion("Back To Black", "Amy Winehouse", 100.0);
        mp4.agregarCancion("Lost On You", "LP", 150.0);
        mp4.agregarVideo("Heathens", "twenty one pilots", 50.0);
        mp4.agregarVideo("Harmonica Andromeda", "KSHMR", 70.0);
        
        System.out.println("\n=== MOSTRAR CONTENIDO ===");
        mp4.mostrarCanciones();
        mp4.mostrarVideos();
        
        System.out.println("\n=== CAPACIDAD ===");
        mp4.mostrarCapacidad();
        
        System.out.println("\n=== BORRAR CANCIONES ===");
        mp4.borrar("Back To Black");
        mp4.borrar("Lost On You", "LP");
        
        System.out.println("\n=== CAPACIDAD FINAL ===");
        mp4.mostrarCapacidad();
    }
}