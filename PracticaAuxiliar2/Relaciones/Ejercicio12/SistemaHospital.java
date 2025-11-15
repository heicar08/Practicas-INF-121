public class SistemaHospital{
    
    public static int contarHospitales(Doctor doctor, Hospital... hospitales) {
        int count = 0;
        for (Hospital hospital : hospitales) {
            if (hospital.tieneDoctor(doctor)) {
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA HOSPITAL-DOCTORES ===\n");
        
      
        Doctor doctor1 = new Doctor("Dr. Carlos Ruiz", "Cardiología");
        Doctor doctor2 = new Doctor("Dra. Ana García", "Pediatría");
        Doctor doctor3 = new Doctor("Dr. Luis Martínez", "Cirugía");
        Doctor doctor4 = new Doctor("Dra. María López", "Neurología");
        Doctor doctor5 = new Doctor("Dr. Pedro Sánchez", "Traumatología");
    
        Hospital hospital1 = new Hospital("Hospital Central");
        Hospital hospital2 = new Hospital("Hospital del Norte");
        Hospital hospital3 = new Hospital("Clínica Sur");
        
        System.out.println("=== ASIGNANDO DOCTORES A HOSPITALES ===\n");
        
        hospital1.asignarDoctor(doctor1);
        hospital1.asignarDoctor(doctor2);
        hospital1.asignarDoctor(doctor3);
     
        hospital2.asignarDoctor(doctor2);
        hospital2.asignarDoctor(doctor4);
        hospital2.asignarDoctor(doctor5);
        
        
        hospital3.asignarDoctor(doctor1);
        hospital3.asignarDoctor(doctor3);
        hospital3.asignarDoctor(doctor5);
        
        
        System.out.println("\n=== INTENTO DE ASIGNACIÓN DUPLICADA ===");
        hospital1.asignarDoctor(doctor1);
        
        System.out.println("\n=== MOSTRANDO DOCTORES POR HOSPITAL ===");
        
        hospital1.mostrarDoctores();
        hospital2.mostrarDoctores();
        hospital3.mostrarDoctores();
        
        System.out.println("\n=== RESUMEN DE ASIGNACIONES ===");
        System.out.println("Doctor " + doctor1.getNombre() + " trabaja en " + 
                          contarHospitales(doctor1, hospital1, hospital2, hospital3) + " hospital(es)");
        System.out.println("Doctor " + doctor2.getNombre() + " trabaja en " + 
                          contarHospitales(doctor2, hospital1, hospital2, hospital3) + " hospital(es)");
        System.out.println("Doctor " + doctor5.getNombre() + " trabaja en " + 
                          contarHospitales(doctor5, hospital1, hospital2, hospital3) + " hospital(es)");
    }
}