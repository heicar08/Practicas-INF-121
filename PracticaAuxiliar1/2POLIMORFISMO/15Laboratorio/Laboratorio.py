class Ordenador:
    def __init__(self, codigo_serial, numero, ram, procesador, estado=True):
        self.codigo_serial = codigo_serial
        self.numero = numero
        self.ram = ram
        self.procesador = procesador
        self.estado = estado
    
    def __str__(self):
        estado = "Activo" if self.estado else "Inactivo"
        return f"Serial: {self.codigo_serial}, Número: {self.numero}, RAM: {self.ram}GB, Procesador: {self.procesador}, Estado: {estado}"

class Laboratorio:
    def __init__(self, nombre, capacidad):
        self.nombre = nombre
        self.capacidad = capacidad
        self.ordenadores = []
        self.codigos_seriales = []
    
    def agregar_ordenador(self, ordenador):
        if len(self.ordenadores) < self.capacidad:
            self.ordenadores.append(ordenador)
            self.codigos_seriales.append(ordenador.codigo_serial)
            return True
        return False
    
    def informacion(self, tipo="todos", laboratorio=None, ram_minima=8):
        if tipo == "estado":
            activos = [o for o in self.ordenadores if o.estado]
            inactivos = [o for o in self.ordenadores if not o.estado]
            
            print(f"\nOrdenadores ACTIVOS en {self.nombre}:")
            for ordenador in activos:
                print(f"  {ordenador}")
            
            print(f"\nOrdenadores INACTIVOS en {self.nombre}:")
            for ordenador in inactivos:
                print(f"  {ordenador}")
        
        elif tipo == "laboratorio" and laboratorio:
            print(f"\nOrdenadores que pertenecen a {laboratorio.nombre}:")
            for ordenador in laboratorio.ordenadores:
                print(f"  {ordenador}")
        
        elif tipo == "ram":
            print(f"\nOrdenadores con más de {ram_minima}GB RAM en {self.nombre}:")
            for ordenador in self.ordenadores:
                if ordenador.ram > ram_minima:
                    print(f"  {ordenador}")
        
        else:
            print(f"\nTodos los ordenadores de {self.nombre}:")
            for ordenador in self.ordenadores:
                print(f"  {ordenador}")
    
    def mostrar_estadisticas(self):
        activos = sum(1 for o in self.ordenadores if o.estado)
        inactivos = sum(1 for o in self.ordenadores if not o.estado)
        alta_ram = sum(1 for o in self.ordenadores if o.ram > 8)
        
        print(f"\n=== ESTADÍSTICAS {self.nombre} ===")
        print(f"Total ordenadores: {len(self.ordenadores)}")
        print(f"Activos: {activos}")
        print(f"Inactivos: {inactivos}")
        print(f"Con +8GB RAM: {alta_ram}")

def trasladar_ordenadores(lab_origen, lab_destino, cantidad=2):
    print("\n" + "="*60)
    print("ANTES DEL TRASLADO:")
    print(f"{lab_origen.nombre}: {len(lab_origen.ordenadores)} ordenadores")
    print(f"{lab_destino.nombre}: {len(lab_destino.ordenadores)} ordenadores")
    
    trasladados = 0
    ordenadores_a_trasladar = lab_origen.ordenadores[:cantidad]
    
    print(f"\nOrdenadores a trasladar de {lab_origen.nombre} a {lab_destino.nombre}:")
    for ordenador in ordenadores_a_trasladar:
        print(f"  - {ordenador.codigo_serial} (RAM: {ordenador.ram}GB)")
    
    for ordenador in ordenadores_a_trasladar:
        if lab_destino.agregar_ordenador(ordenador):
            lab_origen.ordenadores.remove(ordenador)
            lab_origen.codigos_seriales.remove(ordenador.codigo_serial)
            trasladados += 1
    
    print(f"\nDESPUÉS DEL TRASLADO:")
    print(f"Se trasladaron {trasladados} ordenadores")
    print(f"{lab_origen.nombre}: {len(lab_origen.ordenadores)} ordenadores")
    print(f"{lab_destino.nombre}: {len(lab_destino.ordenadores)} ordenadores")

if __name__ == "__main__":
    print("=== EJERCICIO 15 - LABORATORIO LASIN ===\n")
    
    lab1 = Laboratorio("Lasin 1", 10)
    lab2 = Laboratorio("Lasin 2", 10)
    
    ordenadores_lasin1 = [
        Ordenador("SER001", 1, 8, "Intel i5", True),
        Ordenador("SER002", 2, 16, "Intel i7", True),
        Ordenador("SER003", 3, 4, "AMD Ryzen 3", False)
    ]
    
    ordenadores_lasin2 = [
        Ordenador("SER004", 4, 32, "Intel i9", True),
        Ordenador("SER005", 5, 8, "AMD Ryzen 5", True),
        Ordenador("SER006", 6, 16, "Intel i7", False)
    ]
    
    for ordenador in ordenadores_lasin1:
        lab1.agregar_ordenador(ordenador)
    
    for ordenador in ordenadores_lasin2:
        lab2.agregar_ordenador(ordenador)
    
    print("a) LABORATORIOS CREADOS:")
    print(f"- {lab1.nombre} con {len(lab1.ordenadores)} ordenadores")
    print(f"- {lab2.nombre} con {len(lab2.ordenadores)} ordenadores")
    
    print("\nb) SOBRECARGA DEL MÉTODO INFORMACION:")
    
    print("\n1. Mostrar por estado:")
    lab1.informacion("estado")
    
    print("\n2. Mostrar ordenadores de laboratorio específico:")
    lab1.informacion("laboratorio", lab1)
    
    print("\n3. Mostrar ordenadores con +8GB RAM:")
    lab1.informacion("ram")
    
    print("\n4. Mostrar todos los ordenadores:")
    lab1.informacion()
    
    print("\nc) TRASLADO DE ORDENADORES:")
    trasladar_ordenadores(lab1, lab2, 2)
    
    print("\nd) ESTADÍSTICAS FINALES:")
    lab1.mostrar_estadisticas()
    lab2.mostrar_estadisticas()