from abc import ABC, abstractmethod

# Clase abstracta Empleado
class Empleado(ABC):
    def __init__(self, nombre):
        self.nombre = nombre
    
    @abstractmethod
    def calcular_salario_mensual(self):
        pass
    
    def __str__(self):
        return f"Empleado: {self.nombre}"

# Clase EmpleadoTiempoCompleto
class EmpleadoTiempoCompleto(Empleado):
    def __init__(self, nombre, salario_anual):
        super().__init__(nombre)
        self.salario_anual = salario_anual
    
    def calcular_salario_mensual(self):
        return self.salario_anual / 12
    
    def __str__(self):
        return f"{super().__str__()}, Tipo: Tiempo Completo, Salario Anual: ${self.salario_anual:,.2f}"

# Clase EmpleadoTiempoHorario
class EmpleadoTiempoHorario(Empleado):
    def __init__(self, nombre, horas_trabajadas, tarifa_por_hora):
        super().__init__(nombre)
        self.horas_trabajadas = horas_trabajadas
        self.tarifa_por_hora = tarifa_por_hora
    
    def calcular_salario_mensual(self):
        return self.horas_trabajadas * self.tarifa_por_hora
    
    def __str__(self):
        return f"{super().__str__()}, Tipo: Tiempo Horario, Horas: {self.horas_trabajadas}, Tarifa: ${self.tarifa_por_hora:.2f}"

# Programa de prueba
def main_empleados():
    empleados = []
    
    print("=== REGISTRO DE EMPLEADOS ===")
    print("\n--- Empleados de Tiempo Completo (3 empleados) ---")
    for i in range(3):
        print(f"\nEmpleado de Tiempo Completo #{i+1}:")
        nombre = input("Nombre: ")
        salario_anual = float(input("Salario anual: $"))
        empleado = EmpleadoTiempoCompleto(nombre, salario_anual)
        empleados.append(empleado)
    
    print("\n--- Empleados de Tiempo Horario (2 empleados) ---")
    for i in range(2):
        print(f"\nEmpleado de Tiempo Horario #{i+1}:")
        nombre = input("Nombre: ")
        horas = float(input("Horas trabajadas: "))
        tarifa = float(input("Tarifa por hora: $"))
        empleado = EmpleadoTiempoHorario(nombre, horas, tarifa)
        empleados.append(empleado)
    
    print("\n=== REPORTE DE EMPLEADOS ===")
    for i, empleado in enumerate(empleados, 1):
        salario_mensual = empleado.calcular_salario_mensual()
        print(f"\n{i}. {empleado.nombre} - Salario Mensual: ${salario_mensual:,.2f}")

# Ejecutar el programa de empleados
# main_empleados()