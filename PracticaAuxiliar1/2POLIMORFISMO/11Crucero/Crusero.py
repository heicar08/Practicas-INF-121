class Pasajero:
    def __init__(self, nombre="", edad=0, genero="", nro_habitacion=0, costo_pasaje=0):
        self.nombre = nombre
        self.edad = edad
        self.genero = genero
        self.nro_habitacion = nro_habitacion
        self.costo_pasaje = costo_pasaje

    def __pos__(self):
        print(f"Leyendo pasajero: {self.nombre}")
        return self

    def __neg__(self):
        print(f"Mostrando pasajero: {self.nombre}, Habitación: {self.nro_habitacion}, Costo: ${self.costo_pasaje}")
        return self

class Crucero:
    def __init__(self, nombre="", pais_origen="", pais_destino="", nro_pasajeros=0):
        self.nombre = nombre
        self.pais_origen = pais_origen
        self.pais_destino = pais_destino
        self.nro_pasajeros = nro_pasajeros
        self.pasajeros = []

    def __pos__(self):
        print(f"Leyendo crucero: {self.nombre}")
        return self

    def __neg__(self):
        print(f"Mostrando crucero: {self.nombre}, Origen: {self.pais_origen}, Destino: {self.pais_destino}")
        return self

    def __eq__(self, otro):
        total_self = sum(p.costo_pasaje for p in self.pasajeros)
        total_otro = sum(p.costo_pasaje for p in otro.pasajeros)
        print(f"Total crucero {self.nombre}: ${total_self}")
        print(f"Total crucero {otro.nombre}: ${total_otro}")
        return total_self == total_otro

    def __add__(self, otro):
        mismo_pasajeros = self.nro_pasajeros == otro.nro_pasajeros
        print(f"¿Misma cantidad de pasajeros? {mismo_pasajeros}")
        return mismo_pasajeros

    def __sub__(self, otro):
        hombres_self = sum(1 for p in self.pasajeros if p.genero.lower() == "masculino")
        mujeres_self = sum(1 for p in self.pasajeros if p.genero.lower() == "femenino")
        print(f"Crucero {self.nombre}: Hombres: {hombres_self}, Mujeres: {mujeres_self}")
        return hombres_self, mujeres_self

    def agregar_pasajero(self, pasajero):
        self.pasajeros.append(pasajero)
        self.nro_pasajeros += 1

if __name__ == "__main__":
    pasajero1 = Pasajero("Juan Vargas", 30, "masculino", 502, 500)
    pasajero2 = Pasajero("Martina Vasques", 25, "femenino", 603, 1000)
    pasajero3 = Pasajero("Wilmer Montero", 35, "masculino", 401, 925)
    pasajero4 = Pasajero("Laura Garcia", 28, "femenino", 305, 750)
    pasajero5 = Pasajero("Carlos Lopez", 40, "masculino", 208, 850)

    crucero1 = Crucero("Caribe Paradise", "España", "Caribe", 0)
    crucero2 = Crucero("Mediterranean Dream", "Italia", "Grecia", 0)

    crucero1.agregar_pasajero(pasajero1)
    crucero1.agregar_pasajero(pasajero2)
    crucero1.agregar_pasajero(pasajero3)

    crucero2.agregar_pasajero(pasajero4)
    crucero2.agregar_pasajero(pasajero5)

    +pasajero1
    -pasajero1

    +crucero1
    -crucero1

    print(f"\nComparación de costos totales: {crucero1 == crucero2}")
    
    print(f"\nComparación cantidad pasajeros:")
    crucero1 + crucero2
    
    print(f"\nEstadísticas de género:")
    crucero1 - crucero2