class Persona:
    def __init__(self, nombre, apellido, ci):
        self.nombre = nombre
        self.apellido = apellido
        self.ci = ci
    
    def get_nombre_completo(self):
        return f"{self.nombre} {self.apellido}"

class Empleado:
    def __init__(self, sueldo, cargo):
        self.sueldo = sueldo
        self.cargo = cargo
    
    def get_sueldo(self):
        return self.sueldo

class Policia:
    def __init__(self, grado, años_servicio):
        self.grado = grado
        self.añosServicio = años_servicio
    
    def get_grado(self):
        return self.grado

class JefePolicia(Persona, Empleado, Policia):
    def __init__(self, nombre, apellido, ci, sueldo, cargo, grado, años_servicio, departamento):
        Persona.__init__(self, nombre, apellido, ci)
        Empleado.__init__(self, sueldo, cargo)
        Policia.__init__(self, grado, años_servicio)
        self.departamento = departamento
    
    def mostrar_datos(self):
        print(f"Nombre: {self.get_nombre_completo()}")
        print(f"CI: {self.ci}")
        print(f"Sueldo: ${self.get_sueldo()}")
        print(f"Cargo: {self.cargo}")
        print(f"Grado: {self.get_grado()}")
        print(f"Años de servicio: {self.añosServicio}")
        print(f"Departamento: {self.departamento}")

jefe1 = JefePolicia("Carlos", "Mendoza", "1234567", 8000, "Jefe Regional", "Coronel", 20, "Narcóticos")
jefe2 = JefePolicia("Ana", "Torres", "7654321", 7500, "Jefe de División", "Teniente Coronel", 18, "Homicidios")

print("=== JEFE DE POLICÍA 1 ===")
jefe1.mostrar_datos()

print("\n=== JEFE DE POLICÍA 2 ===")
jefe2.mostrar_datos()

print("\n=== COMPARACIÓN DE SUELDOS ===")
if jefe1.get_sueldo() > jefe2.get_sueldo():
    print(f"{jefe1.get_nombre_completo()} tiene mayor sueldo")
elif jefe2.get_sueldo() > jefe1.get_sueldo():
    print(f"{jefe2.get_nombre_completo()} tiene mayor sueldo")
else:
    print("Ambos tienen el mismo sueldo")

print("\n=== COMPARACIÓN DE GRADOS ===")
if jefe1.get_grado() == jefe2.get_grado():
    print("Ambos tienen el mismo grado")
else:
    print("Tienen grados diferentes")