package Diez;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CobrosNFC {
    private List<Transaccion> transacciones;
    private static final String ARCHIVO = "cobros_nfc.txt";

    public CobrosNFC() {
        transacciones = new ArrayList<>();
        cargarTransacciones();
    }

    public void agregarTransaccion(Transaccion transaccion) {
        transacciones.add(transaccion);
        guardarTransacciones();
    }

    public void mostrarTransacciones() {
        for (Transaccion t : transacciones) {
            System.out.println(t);
        }
    }

    public Transaccion buscarPorID(String id) {
        for (Transaccion t : transacciones) {
            if (t.getId().equalsIgnoreCase(id)) {
                return t;
            }
        }
        return null;
    }

    private void guardarTransacciones() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Transaccion t : transacciones) {
                writer.write(t.getId() + "," + t.getMonto() + "," + t.getFecha());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar transacciones: " + e.getMessage());
        }
    }

    private void cargarTransacciones() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    String id = datos[0];
                    double monto = Double.parseDouble(datos[1]);
                    String fecha = datos[2];
                    transacciones.add(new Transaccion(id, monto, fecha));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar transacciones: " + e.getMessage());
        }
    }
}