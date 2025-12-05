package Diez;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        RedWifi redWifi = new RedWifi();
        CobrosNFC cobrosNFC = new CobrosNFC();

        while (true) {
            System.out.println("\n=== MENÚ PRINCIPAL ===");
            System.out.println("1. Gestión de Red WiFi");
            System.out.println("2. Gestión de Cobros NFC");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            int opcionPrincipal = scanner.nextInt();
            scanner.nextLine();

            switch (opcionPrincipal) {
                case 1 -> gestionRedWifi(scanner, redWifi);
                case 2 -> gestionCobrosNFC(scanner, cobrosNFC);
                case 3 -> {
                    System.out.println("Saliendo...");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Opción no válida");
            }
        }
    }

    private static void gestionRedWifi(Scanner scanner, RedWifi redWifi) {
        while (true) {
            System.out.println("\n=== GESTIÓN DE RED WiFi ===");
            System.out.println("1. Agregar dispositivo");
            System.out.println("2. Mostrar todos los dispositivos");
            System.out.println("3. Buscar dispositivo por MAC");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese dirección MAC: ");
                    String mac = scanner.nextLine();
                    System.out.print("Ingrese nombre del dispositivo: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese velocidad de conexión (Mbps): ");
                    int velocidad = scanner.nextInt();
                    scanner.nextLine();

                    Dispositivo dispositivo = new Dispositivo(mac, nombre, velocidad);
                    redWifi.agregarDispositivo(dispositivo);
                    System.out.println("Dispositivo agregado correctamente.");
                }
                case 2 -> {
                    System.out.println("\n=== DISPOSITIVOS CONECTADOS ===");
                    redWifi.mostrarDispositivos();
                }
                case 3 -> {
                    System.out.print("Ingrese dirección MAC a buscar: ");
                    String macBuscar = scanner.nextLine();
                    Dispositivo encontrado = redWifi.buscarPorMAC(macBuscar);
                    if (encontrado != null) {
                        System.out.println("Dispositivo encontrado: " + encontrado);
                    } else {
                        System.out.println("No se encontró dispositivo con esa MAC.");
                    }
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opción no válida");
            }
        }
    }

    private static void gestionCobrosNFC(Scanner scanner, CobrosNFC cobrosNFC) {
        while (true) {
            System.out.println("\n=== GESTIÓN DE COBROS NFC ===");
            System.out.println("1. Agregar transacción");
            System.out.println("2. Mostrar todas las transacciones");
            System.out.println("3. Buscar transacción por ID");
            System.out.println("4. Volver al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 -> {
                    System.out.print("Ingrese ID de transacción: ");
                    String id = scanner.nextLine();
                    System.out.print("Ingrese monto: ");
                    double monto = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Ingrese fecha (dd/mm/aaaa): ");
                    String fecha = scanner.nextLine();

                    Transaccion transaccion = new Transaccion(id, monto, fecha);
                    cobrosNFC.agregarTransaccion(transaccion);
                    System.out.println("Transacción agregada correctamente.");
                }
                case 2 -> {
                    System.out.println("\n=== TODAS LAS TRANSACCIONES ===");
                    cobrosNFC.mostrarTransacciones();
                }
                case 3 -> {
                    System.out.print("Ingrese ID a buscar: ");
                    String idBuscar = scanner.nextLine();
                    Transaccion encontrada = cobrosNFC.buscarPorID(idBuscar);
                    if (encontrada != null) {
                        System.out.println("Transacción encontrada: " + encontrada);
                    } else {
                        System.out.println("No se encontró transacción con ese ID.");
                    }
                }
                case 4 -> {
                    return;
                }
                default -> System.out.println("Opción no válida");
            }
        }
    }
}
