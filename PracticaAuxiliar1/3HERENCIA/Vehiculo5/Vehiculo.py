class Vehiculo:
    def __init__(self, conductor, placa, id_vehiculo):
        self.conductor = conductor
        self.placa = placa
        self.id = id_vehiculo
    
    def mostrar_datos(self):
        print(f"Conductor: {self.conductor}")
        print(f"Placa: {self.placa}")
        print(f"ID: {self.id}")
    
    def cambiar_conductor(self, nuevo_conductor):
        self.conductor = nuevo_conductor
        print(f"Conductor cambiado a: {nuevo_conductor}")

class Bus(Vehiculo):
    def __init__(self, conductor, placa, id_vehiculo, capacidad, sindicato):
        super().__init__(conductor, placa, id_vehiculo)
        self.capacidad = capacidad
        self.sindicato = sindicato

class Auto(Vehiculo):
    def __init__(self, conductor, placa, id_vehiculo, caballos_fuerza, descapotable):
        super().__init__(conductor, placa, id_vehiculo)
        self.caballosFuerza = caballos_fuerza
        self.descapotable = descapotable

class Moto(Vehiculo):
    def __init__(self, conductor, placa, id_vehiculo, cilindrada, casco):
        super().__init__(conductor, placa, id_vehiculo)
        self.cilindrada = cilindrada
        self.casco = casco


bus = Bus("Carlos López", "ABC-123", 1, 50, "SUT")
auto = Auto("Ana García", "DEF-456", 2, 150, True)
moto = Moto("Pedro Martínez", "GHI-789", 3, 250, True)

print("=== BUS ===")
bus.mostrar_datos()
print(f"Capacidad: {bus.capacidad}")

print("\n=== AUTO ===")
auto.mostrar_datos()
print(f"Caballos de fuerza: {auto.caballosFuerza}")

print("\n=== MOTO ===")
moto.mostrar_datos()
print(f"Cilindrada: {moto.cilindrada}")


print("\n=== CAMBIO DE CONDUCTOR ===")
auto.cambiar_conductor("Luis Ramírez")
auto.mostrar_datos()