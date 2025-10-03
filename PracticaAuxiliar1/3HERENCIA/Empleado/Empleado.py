class Empleado:
    def __init__(self, nombre, salario):
        self.nombre = nombre
        self.salario = salario
    
    def mostrar_info(self):
        print(f"Nombre: {self.nombre}")
        print(f"Salario: ${self.salario}")

class Administrativo(Empleado):
    def __init__(self, nombre, salario, departamento, experiencia):
        super().__init__(nombre, salario)
        self.departamento = departamento
        self.experiencia = experiencia
    
    def mostrar_info(self):
        super().mostrar_info()
        print(f"Departamento: {self.departamento}")
        print(f"Experiencia: {self.experiencia} años")

class Chef(Empleado):
    def __init__(self, nombre, salario, especialidad, estrellas):
        super().__init__(nombre, salario)
        self.especialidad = especialidad
        self.estrellas = estrellas
    
    def mostrar_info(self):
        super().mostrar_info()
        print(f"Especialidad: {self.especialidad}")
        print(f"Estrellas: {self.estrellas}")

class Mesero(Empleado):
    def __init__(self, nombre, salario, turno, mesas):
        super().__init__(nombre, salario)
        self.turno = turno
        self.mesas = mesas
    
    def mostrar_info(self):
        super().mostrar_info()
        print(f"Turno: {self.turno}")
        print(f"Mesas asignadas: {self.mesas}")

administrativo1 = Administrativo("María González", 2500, "Recursos Humanos", 5)
chef1 = Chef("Carlos Rodríguez", 4000, "Cocina Internacional", 3)
mesero1 = Mesero("Ana López", 1500, "Noche", 8)

empleados = [administrativo1, chef1, mesero1]

for empleado in empleados:
    empleado.mostrar_info()
    print()