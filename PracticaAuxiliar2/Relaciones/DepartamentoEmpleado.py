class Departamento:
    def __init__(self, nombre, area):
        self.nombre = nombre
        self.area = area
        self.empleados = []
    
    def agregar_empleado(self, empleado):
        self.empleados.append(empleado)
    
    def mostrar_empleados(self):
        print(f"\n--- Empleados del Departamento {self.nombre} ({self.area}) ---")
        if not self.empleados:
            print("No hay empleados en este departamento")
        else:
            for i, empleado in enumerate(self.empleados, 1):
                print(f"{i}. {empleado.nombre} - {empleado.cargo} - ${empleado.sueldo}")
    
    def cambiar_salario(self, porcentaje):
        for empleado in self.empleados:
            empleado.sueldo *= (1 + porcentaje / 100)
        print(f"Salarios actualizados en {porcentaje}% para el departamento {self.nombre}")
    
    def mover_empleados_a(self, otro_departamento):
        if not self.empleados:
            print(f"No hay empleados para mover del departamento {self.nombre}")
            return
        
        print(f"\nMoviendo {len(self.empleados)} empleados de {self.nombre} a {otro_departamento.nombre}")
        otro_departamento.empleados.extend(self.empleados)
        self.empleados.clear()
        print("Movimiento completado exitosamente")


class Empleado:
    def __init__(self, nombre, cargo, sueldo):
        self.nombre = nombre
        self.cargo = cargo
        self.sueldo = sueldo
    
    def __str__(self):
        return f"{self.nombre} - {self.cargo} - ${self.sueldo}"


departamento1 = Departamento("Ventas", "Comercial")
departamento2 = Departamento("Recursos Humanos", "Administración")

empleados_dep1 = [
    Empleado("Ana García", "Gerente de Ventas", 5000),
    Empleado("Carlos López", "Ejecutivo de Ventas", 3000),
    Empleado("María Rodríguez", "Asistente de Ventas", 2500),
    Empleado("Pedro Martínez", "Analista Comercial", 3500),
    Empleado("Laura Fernández", "Coordinadora", 2800)
]

for empleado in empleados_dep1:
    departamento1.agregar_empleado(empleado)

print("=== MOSTRAR EMPLEADOS INICIALES ===")
departamento1.mostrar_empleados()
departamento2.mostrar_empleados()

print("\n=== CAMBIO DE SALARIOS ===")
departamento1.cambiar_salario(10)
departamento1.mostrar_empleados()

print("\n=== VERIFICACIÓN DE EMPLEADOS ENTRE DEPARTAMENTOS ===")
empleados_comunes = []
for empleado in departamento1.empleados:
    if empleado in departamento2.empleados:
        empleados_comunes.append(empleado)

if empleados_comunes:
    print(f"Se encontraron {len(empleados_comunes)} empleados en común:")
    for empleado in empleados_comunes:
        print(f"  - {empleado}")
else:
    print("No hay empleados en común entre los departamentos")

print("\n=== MOVIMIENTO DE EMPLEADOS ===")
print("ANTES del movimiento:")
departamento1.mostrar_empleados()
departamento2.mostrar_empleados()

departamento1.mover_empleados_a(departamento2)

print("\nDESPUÉS del movimiento:")
departamento1.mostrar_empleados()
departamento2.mostrar_empleados()