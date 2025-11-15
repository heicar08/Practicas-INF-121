

public class Doctor {
    private String nombre;
    private String especialidad;
    
    public Doctor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getEspecialidad() {
        return especialidad;
    }
    
    @Override
    public String toString() {
        return nombre + " - " + especialidad;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Doctor doctor = (Doctor) obj;
        return nombre.equals(doctor.nombre) && especialidad.equals(doctor.especialidad);
    }
}