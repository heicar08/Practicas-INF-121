package ejercicio1.Empleado;

import java.util.Scanner;

public class SistemaEmpleados {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Empleado[] empleados = new Empleado[5];
        
        System.out.println("=== REGISTRO DE EMPLEADOS ===");
        
        for (int i = 0; i < 3; i++) {
            System.out.println("\nEmpleado de Tiempo Completo #" + (i + 1) + ":");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Salario anual: Bs.");
            double salarioAnual = scanner.nextDouble();
            scanner.nextLine();
            
            empleados[i] = new EmpleadoTiempoCompleto(nombre, salarioAnual);
        }
        
        for (int i = 3; i < 5; i++) {
            System.out.println("\nEmpleado de Tiempo Horario #" + (i - 2) + ":");
            System.out.print("Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Horas trabajadas: ");
            double horas = scanner.nextDouble();
            System.out.print("Tarifa por hora: Bs.");
            double tarifa = scanner.nextDouble();
            scanner.nextLine();
            
            empleados[i] = new EmpleadoTiempoHorario(nombre, horas, tarifa);
        }
        
        System.out.println("\n=== REPORTE DE EMPLEADOS ===");
        for (int i = 0; i < empleados.length; i++) {
            double salarioMensual = empleados[i].calcularSalarioMensual();
            System.out.printf("%d. %s - Salario Mensual: Bs%,.2f%n", 
                             i + 1, empleados[i].getNombre(), salarioMensual);
        }
        
        scanner.close();
    }
}