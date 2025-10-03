class Persona:
    def __init__(self, nombre, paterno, materno, edad):
        self.nombre = nombre
        self.paterno = paterno
        self.materno = materno
        self.edad = edad
    
    def mostrar(self):
        print(f"Nombre: {self.nombre} {self.paterno} {self.materno}")
        print(f"Edad: {self.edad}")

class Docente(Persona):
    def __init__(self, nombre, paterno, materno, edad, sueldo, reg_profesional):
        super().__init__(nombre, paterno, materno, edad)
        self.sueldo = sueldo
        self.regProfesional = reg_profesional
    
    def mostrar(self):
        super().mostrar()
        print(f"Sueldo: {self.sueldo}")
        print(f"Registro profesional: {self.regProfesional}")

class Estudiante(Persona):
    def __init__(self, nombre, paterno, materno, edad, ru, matricula):
        super().__init__(nombre, paterno, materno, edad)
        self.ru = ru
        self.matricula = matricula
    
    def mostrar(self):
        super().mostrar()
        print(f"RU: {self.ru}")
        print(f"Matrícula: {self.matricula}")
    
    def modificar_edad(self, nueva_edad):
        self.edad = nueva_edad
        print(f"Edad modificada a: {nueva_edad}")

estudiante1 = Estudiante("Juan", "Pérez", "Gómez", 20, "123456", "2023001")
estudiante2 = Estudiante("María", "López", "Santos", 22, "123457", "2023002")
docente1 = Docente("Carlos", "Rodríguez", "Vargas", 45, 5000, "PROF-001")

print("=== ESTUDIANTE 1 ===")
estudiante1.mostrar()

print("\n=== ESTUDIANTE 2 ===")
estudiante2.mostrar()

print("\n=== DOCENTE ===")
docente1.mostrar()

estudiantes = [estudiante1, estudiante2]
promedio = sum(est.edad for est in estudiantes) / len(estudiantes)
print(f"\nPromedio de edad de estudiantes: {promedio}")

print("\n=== MODIFICAR EDAD ===")
estudiante1.modificar_edad(21)
estudiante1.mostrar()

print("\n=== COMPARACIÓN DE EDADES ===")
for i, estudiante in enumerate(estudiantes, 1):
    if estudiante.edad == docente1.edad:
        print(f"El estudiante {i} tiene la misma edad que el docente")
    else:
        print(f"El estudiante {i} NO tiene la misma edad que el docente")