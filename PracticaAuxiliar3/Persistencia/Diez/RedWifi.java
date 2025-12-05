package Diez;
// Archivo: RedWifi.java
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RedWifi {
    private List<Dispositivo> dispositivos;
    private static final String ARCHIVO = "dispositivos_wifi.txt";

    public RedWifi() {
        dispositivos = new ArrayList<>();
        cargarDispositivos();
    }

    public void agregarDispositivo(Dispositivo dispositivo) {
        dispositivos.add(dispositivo);
        guardarDispositivos();
    }

    public void mostrarDispositivos() {
        for (Dispositivo d : dispositivos) {
            System.out.println(d);
        }
    }

    public Dispositivo buscarPorMAC(String mac) {
        for (Dispositivo d : dispositivos) {
            if (d.getMac().equalsIgnoreCase(mac)) {
                return d;
            }
        }
        return null;
    }

    private void guardarDispositivos() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Dispositivo d : dispositivos) {
                writer.write(d.getMac() + "," + d.getNombre() + "," + d.getVelocidad());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar dispositivos: " + e.getMessage());
        }
    }

    private void cargarDispositivos() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    String mac = datos[0];
                    String nombre = datos[1];
                    int velocidad = Integer.parseInt(datos[2]);
                    dispositivos.add(new Dispositivo(mac, nombre, velocidad));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar dispositivos: " + e.getMessage());
        }
    }
}