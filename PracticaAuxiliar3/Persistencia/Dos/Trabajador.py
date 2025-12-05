import pickle
import os
from typing import List

class Trabajador:
    def __init__(self, nombre: str, carnet: int, salario: float):
        self.nombre = nombre
        self.carnet = carnet
        self.salario = salario
    
    def aumentar_salario(self, porcentaje: int):
        self.salario += self.salario * (porcentaje / 100)
    
    def __str__(self):
        return f"{self.carnet} | {self.nombre:20} | {self.salario:10.2f}"
    
    def to_dict(self):
        return {'nombre': self.nombre, 'carnet': self.carnet, 'salario': self.salario}
    
    @classmethod
    def from_dict(cls, data):
        return cls(data['nombre'], data['carnet'], data['salario'])

class ArchivoTrabajador:
    def __init__(self, archivo="trabajadores.dat"):
        self.nombre_arch = archivo
        self.trabajadores = []
        self.cargar()
    
    def crear_archivo(self):
        try:
            with open(self.nombre_arch, 'wb') as f:
                pickle.dump([], f)
            return True
        except:
            return False
    
    def guardar_trabajador(self, t):
        self.trabajadores.append(t)
        self.guardar()
    
    def aumenta_salario(self, a, t):
        if t in self.trabajadores:
            t.aumentar_salario(a)
            self.guardar()
            return True
        return False
    
    def buscar_mayor_salario(self):
        if not self.trabajadores:
            return None
        mayor = self.trabajadores[0]
        for t in self.trabajadores[1:]:
            if t.salario > mayor.salario:
                mayor = t
        return mayor
    
    def ordenar_por_salario(self):
        return sorted(self.trabajadores, key=lambda x: x.salario)
    
    def guardar(self):
        try:
            datos = [t.to_dict() for t in self.trabajadores]
            with open(self.nombre_arch, 'wb') as f:
                pickle.dump(datos, f)
        except:
            pass
    
    def cargar(self):
        try:
            if os.path.exists(self.nombre_arch):
                with open(self.nombre_arch, 'rb') as f:
                    datos = pickle.load(f)
                    self.trabajadores = [Trabajador.from_dict(d) for d in datos]
        except:
            self.trabajadores = []
    
    def buscar_por_carnet(self, carnet):
        for t in self.trabajadores:
            if t.carnet == carnet:
                return t
        return None
    
    def eliminar_trabajador(self, carnet):
        for i, t in enumerate(self.trabajadores):
            if t.carnet == carnet:
                del self.trabajadores[i]
                self.guardar()
                return True
        return False
    
    def obtener_todos(self):
        return self.trabajadores.copy()
    
    def generar_ejemplos(self):
        if not self.trabajadores:
            ejemplos = [
                Trabajador("Juan Pérez", 1001, 2500),
                Trabajador("María López", 1002, 3200),
                Trabajador("Carlos Ruiz", 1003, 1800),
                Trabajador("Ana García", 1004, 4100),
                Trabajador("Pedro Martínez", 1005, 2900)
            ]
            for t in ejemplos:
                self.guardar_trabajador(t)

class SistemaTrabajadores:
    def __init__(self):
        self.archivo = ArchivoTrabajador()
    
    def iniciar(self):
        while True:
            print("\n" + "="*50)
            print("SISTEMA DE TRABAJADORES")
            print("="*50)
            print("1. Crear archivo")
            print("2. Mostrar todos")
            print("3. Agregar trabajador")
            print("4. Aumentar salario")
            print("5. Mayor salario")
            print("6. Ordenar por salario")
            print("7. Eliminar trabajador")
            print("8. Buscar por carnet")
            print("9. Generar ejemplos")
            print("0. Salir")
            print("="*50)
            
            op = input("Opción: ").strip()
            
            if op == "0":
                print("¡Adiós!")
                break
            elif op == "1":
                self.crear_archivo()
            elif op == "2":
                self.mostrar_todos()
            elif op == "3":
                self.agregar_trabajador()
            elif op == "4":
                self.aumentar_salario()
            elif op == "5":
                self.mayor_salario()
            elif op == "6":
                self.ordenar_salario()
            elif op == "7":
                self.eliminar_trabajador()
            elif op == "8":
                self.buscar_carnet()
            elif op == "9":
                self.generar_ejemplos()
    
    def crear_archivo(self):
        if self.archivo.crear_archivo():
            print("✓ Archivo creado")
        else:
            print("✗ Error al crear archivo")
    
    def mostrar_todos(self):
        trabajadores = self.archivo.obtener_todos()
        if not trabajadores:
            print("No hay trabajadores")
            return
        
        print("\n" + "-"*50)
        print(f"{'Carnet':<8} {'Nombre':<20} {'Salario':<10}")
        print("-"*50)
        for t in trabajadores:
            print(t)
        print("-"*50)
        print(f"Total: {len(trabajadores)}")
    
    def agregar_trabajador(self):
        try:
            nombre = input("Nombre: ").strip()
            if not nombre:
                print("Nombre requerido")
                return
            
            carnet = int(input("Carnet: "))
            if carnet <= 0:
                print("Carnet debe ser positivo")
                return
            
            salario = float(input("Salario: "))
            if salario <= 0:
                print("Salario debe ser positivo")
                return
            
            t = Trabajador(nombre, carnet, salario)
            self.archivo.guardar_trabajador(t)
            print("✓ Trabajador agregado")
            
        except ValueError:
            print("Entrada inválida")
    
    def aumentar_salario(self):
        try:
            carnet = int(input("Carnet del trabajador: "))
            t = self.archivo.buscar_por_carnet(carnet)
            
            if not t:
                print("Trabajador no encontrado")
                return
            
            porcentaje = int(input("Porcentaje de aumento: "))
            if porcentaje <= 0:
                print("Porcentaje debe ser positivo")
                return
            
            if self.archivo.aumenta_salario(porcentaje, t):
                print(f"✓ Salario aumentado a {t.salario:.2f}")
            else:
                print("✗ Error al aumentar salario")
                
        except ValueError:
            print("Entrada inválida")
    
    def mayor_salario(self):
        t = self.archivo.buscar_mayor_salario()
        if t:
            print(f"\nMayor salario:")
            print(f"Carnet: {t.carnet}")
            print(f"Nombre: {t.nombre}")
            print(f"Salario: {t.salario:.2f}")
        else:
            print("No hay trabajadores")
    
    def ordenar_salario(self):
        ordenados = self.archivo.ordenar_por_salario()
        if not ordenados:
            print("No hay trabajadores")
            return
        
        print("\nOrdenados por salario (ascendente):")
        print("-"*50)
        for i, t in enumerate(ordenados, 1):
            print(f"{i:2}. {t}")
        
        print("\n¿Guardar este orden? (s/n): ", end="")
        if input().strip().lower() == 's':
            self.archivo.trabajadores = ordenados
            self.archivo.guardar()
            print("✓ Orden guardado")
    
    def eliminar_trabajador(self):
        try:
            carnet = int(input("Carnet a eliminar: "))
            if self.archivo.eliminar_trabajador(carnet):
                print("✓ Trabajador eliminado")
            else:
                print("Trabajador no encontrado")
        except ValueError:
            print("Carnet inválido")
    
    def buscar_carnet(self):
        try:
            carnet = int(input("Carnet a buscar: "))
            t = self.archivo.buscar_por_carnet(carnet)
            if t:
                print(f"\nEncontrado:")
                print(f"Carnet: {t.carnet}")
                print(f"Nombre: {t.nombre}")
                print(f"Salario: {t.salario:.2f}")
            else:
                print("No encontrado")
        except ValueError:
            print("Carnet inválido")
    
    def generar_ejemplos(self):
        self.archivo.generar_ejemplos()
        print("✓ Ejemplos generados")

if __name__ == "__main__":
    app = SistemaTrabajadores()
    app.iniciar()