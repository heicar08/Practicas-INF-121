package ejercicio1.Empleado;

public class EmpleadoTiempoHorario extends Empleado {
    private double horasTrabajadas;
    private double tarifaPorHora;
    
    public EmpleadoTiempoHorario(String nombre, double horasTrabajadas, double tarifaPorHora) {
        super(nombre);
        this.horasTrabajadas = horasTrabajadas;
        this.tarifaPorHora = tarifaPorHora;
    }
    
    @Override
    public double calcularSalarioMensual() {
        return horasTrabajadas * tarifaPorHora;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Tipo: Tiempo Horario, Horas: " + horasTrabajadas + 
               ", Tarifa: $" + String.format("%.2f", tarifaPorHora);
    }
}