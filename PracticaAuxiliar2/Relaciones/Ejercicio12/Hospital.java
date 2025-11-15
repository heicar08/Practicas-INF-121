import java.util.ArrayList;
import java.util.List;

public class Hospital {
    private String nombre;
    private List<Doctor> doctores;
    
    public Hospital(String nombre) {
        this.nombre = nombre;
        this.doctores = new ArrayList<>();
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void asignarDoctor(Doctor doctor) {
        if (!doctores.contains(doctor)) {
            doctores.add(doctor);
            System.out.println("Doctor " + doctor.getNombre() + " asignado al hospital " + nombre);
        } else {
            System.out.println("El doctor " + doctor.getNombre() + " ya está asignado a este hospital");
        }
    }
    
    public void mostrarDoctores() {
        System.out.println("\n=== Doctores del Hospital " + nombre + " ===");
        if (doctores.isEmpty()) {
            System.out.println("No hay doctores asignados a este hospital");
        } else {
            for (int i = 0; i < doctores.size(); i++) {
                System.out.println((i + 1) + ". " + doctores.get(i));
            }
        }
    }
    
    public List<Doctor> getDoctores() {
        return new ArrayList<>(doctores);
    }
    
    public boolean tieneDoctor(Doctor doctor) {
        return doctores.contains(doctor);
    }
}