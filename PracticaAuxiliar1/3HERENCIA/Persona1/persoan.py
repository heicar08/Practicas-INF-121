class Persona:
    def __init__(self, nombre, apellido, ci):
        self.nombre = nombre
        self.apellido = apellido
        self.ci = ci
    
    def mostrar(self):
        print(f"Nombre: {self.nombre} {self.apellido}")
        print(f"CI: {self.ci}")

class Cliente(Persona):
    def __init__(self, nombre, apellido, ci, numCuenta):
        super().__init__(nombre, apellido, ci)
        self.numCuenta = numCuenta
    
    def mostrar(self):
        super().mostrar()
        print(f"Número de cuenta: {self.numCuenta}")

class Jefe(Persona):
    def __init__(self, nombre, apellido, ci, sucursal, tipo):
        super().__init__(nombre, apellido, ci)
        self.sucursal = sucursal
        self.tipo = tipo
    
    def mostrar(self):
        super().mostrar()
        print(f"Sucursal: {self.sucursal}")
        print(f"Tipo: {self.tipo}")

# Prueba
cliente1 = Cliente("Juan", "Pérez", "1234567", "CTE-001")
jefe1 = Jefe("María", "Gómez", "7654321", "Central", "Gerente")

print("=== Cliente ===")
cliente1.mostrar()
print("\n=== Jefe ===")
jefe1.mostrar()