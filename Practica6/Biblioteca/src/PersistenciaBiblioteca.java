import java.io.*;

public class PersistenciaBiblioteca {
    private static final String ARCHIVO_BIBLIOTECA = "biblioteca.dat";

    /**
     * PERSISTENCIA: Guarda toda la biblioteca (con libros, autores, estudiantes y préstamos) en archivo
     */
    public static void guardarBiblioteca(Biblioteca biblioteca) {
        try {
            FileOutputStream fileOut = new FileOutputStream(ARCHIVO_BIBLIOTECA);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(biblioteca);
            out.close();
            fileOut.close();
            System.out.println("Datos guardados exitosamente en " + ARCHIVO_BIBLIOTECA);
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * PERSISTENCIA: Carga la biblioteca completa desde archivo
     */
    public static Biblioteca cargarBiblioteca() {
        Biblioteca biblioteca = null;
        File archivo = new File(ARCHIVO_BIBLIOTECA);

        if (archivo.exists()) {
            try {
                FileInputStream fileIn = new FileInputStream(ARCHIVO_BIBLIOTECA);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                biblioteca = (Biblioteca) in.readObject();
                in.close();
                fileIn.close();
                System.out.println("Datos cargados exitosamente desde " + ARCHIVO_BIBLIOTECA);
            } catch (IOException e) {
                System.err.println("Error al cargar los datos: " + e.getMessage());
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                System.err.println("Clase no encontrada: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("No se encontró archivo de datos. Se creará una nueva biblioteca.");
        }

        return biblioteca;
    }

    /**
     * Verifica si existe un archivo de datos guardado
     */
    public static boolean existeArchivo() {
        return new File(ARCHIVO_BIBLIOTECA).exists();
    }
}