public class SistemaEmpresa {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE EMPRESAS Y EMPLEADOS ===\n");
        
        
        Empresa empresa = new Empresa("Tech S.A.");
        
        
        Empleado emp1 = new Empleado("Ana García", "Gerente de Proyectos", 75000);
        Empleado emp2 = new Empleado("Carlos López", "Desarrollador Senior", 60000);
        Empleado emp3 = new Empleado("María Rodríguez", "Analista de Sistemas", 55000);
        Empleado emp4 = new Empleado("Pedro Martínez", "Desarrollador Junior", 45000);
        Empleado emp5 = new Empleado("Laura Fernández", "Diseñadora UX", 52000);
        Empleado emp6 = new Empleado("Juan Pérez", "Arquitecto de Software", 80000);
        
        
        empresa.agregarEmpleado(emp1);
        empresa.agregarEmpleado(emp2);
        empresa.agregarEmpleado(emp3);
        empresa.agregarEmpleado(emp4);
        empresa.agregarEmpleado(emp5);
        empresa.agregarEmpleado(emp6);
        
        
        System.out.println("\n--- Intento de agregar empleado duplicado ---");
        empresa.agregarEmpleado(new Empleado("Ana García", "Gerente", 75000));
        
        
        System.out.println("\n" + "=".repeat(50));
        empresa.mostrarInformacion();
        
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== BÚSQUEDA DE EMPLEADOS ===");
        
        String nombreBuscar = "Carlos López";
        Empleado empleadoEncontrado = empresa.buscarEmpleado(nombreBuscar);
        if (empleadoEncontrado != null) {
            System.out.println("Empleado encontrado: " + empleadoEncontrado);
        } else {
            System.out.println("Empleado '" + nombreBuscar + "' no encontrado");
        }
        
        
        nombreBuscar = "Roberto Silva";
        empleadoEncontrado = empresa.buscarEmpleado(nombreBuscar);
        if (empleadoEncontrado != null) {
            System.out.println("Empleado encontrado: " + empleadoEncontrado);
        } else {
            System.out.println("Empleado '" + nombreBuscar + "' no encontrado");
        }
        
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== ESTADÍSTICAS SALARIALES ===");
        double promedio = empresa.calcularPromedioSalarial();
        System.out.printf("Promedio salarial de la empresa: bs%,.2f\n", promedio);
        System.out.println("Total de empleados: " + empresa.getCantidadEmpleados());
        
        
        System.out.println("\n--- Empleados con salario mayor a bs60,000 ---");
        empresa.mostrarEmpleadosSalarioMayorA(60000);
        
        System.out.println("\n--- Empleados con salario mayor a bs70,000 ---");
        empresa.mostrarEmpleadosSalarioMayorA(70000);
        
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== ELIMINACIÓN DE EMPLEADO ===");
        empresa.eliminarEmpleado("Pedro Martínez");
        
        
        empresa.eliminarEmpleado("Roberto Silva");
        
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("=== INFORMACIÓN ACTUALIZADA ===");
        empresa.mostrarInformacion();
        
        
        promedio = empresa.calcularPromedioSalarial();
        System.out.printf("\nNuevo promedio salarial: bs%,.2f\n", promedio);
    }
}