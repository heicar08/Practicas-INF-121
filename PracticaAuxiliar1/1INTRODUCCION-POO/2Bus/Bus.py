class Bus:
    def __init__(self, capacidad_total, costo_pasaje=1.50):
        self.capacidad_total = capacidad_total
        self.pasajeros_actuales = 0
        self.costo_pasaje = costo_pasaje
        self.recaudacion_total = 0.0
    
    def subir_pasajeros(self, cantidad):
        if self.pasajeros_actuales + cantidad <= self.capacidad_total:
            self.pasajeros_actuales += cantidad
            print(f"Subieron {cantidad} pasajeros. Total a bordo: {self.pasajeros_actuales}")
            return True
        else:
            asientos_disponibles = self.capacidad_total - self.pasajeros_actuales
            print(f"No hay espacio para {cantidad} pasajeros. Solo hay {asientos_disponibles} asientos disponibles.")
            return False
    
    def cobrar_pasaje(self, cantidad_pasajeros=None):
        if cantidad_pasajeros is None:
            cantidad_pasajeros = self.pasajeros_actuales
        
        total_cobrado = cantidad_pasajeros * self.costo_pasaje
        self.recaudacion_total += total_cobrado
        print(f"Se cobró Bs. {total_cobrado:.2f} por {cantidad_pasajeros} pasajeros")
        return total_cobrado
    
    def asientos_disponibles(self):
        disponibles = self.capacidad_total - self.pasajeros_actuales
        print(f"Asientos disponibles: {disponibles}")
        return disponibles
    
    def mostrar_estado(self):
        print(f"\n--- ESTADO DEL BUS ---")
        print(f"Capacidad total: {self.capacidad_total}")
        print(f"Pasajeros a bordo: {self.pasajeros_actuales}")
        print(f"Asientos disponibles: {self.asientos_disponibles()}")
        print(f"Recaudación total: Bs. {self.recaudacion_total:.2f}")


if __name__ == "__main__":
   
    mi_bus = Bus(40)
    
   
    mi_bus.mostrar_estado()
    
    
    mi_bus.subir_pasajeros(15)
    mi_bus.subir_pasajeros(20)
    mi_bus.subir_pasajeros(10) 
    
    
    mi_bus.cobrar_pasaje()
    
    mi_bus.asientos_disponibles()
    
    mi_bus.mostrar_estado()