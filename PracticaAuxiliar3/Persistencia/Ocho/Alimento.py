import pickle
import os
from datetime import datetime

class Alimento:
    def __init__(self, nombre="", fecha_vencimiento="", cantidad=0):
        self.nombre = nombre
        self.fecha_vencimiento = fecha_vencimiento
        self.cantidad = cantidad
    def leer(self):
        self.nombre = input("Nombre: ")
        self.fecha_vencimiento = input("Fecha vencimiento (dd/mm/yyyy): ")
        self.cantidad = int(input("Cantidad: "))
    def mostrar(self):
        print(f"{self.nombre} {self.fecha_vencimiento} {self.cantidad}")
    def esta_vencido(self):
        try:
            fecha_venc = datetime.strptime(self.fecha_vencimiento, "%d/%m/%Y")
            return fecha_venc < datetime.now()
        except:
            return False
    def caduca_antes_de(self, fecha_x):
        try:
            fecha_venc = datetime.strptime(self.fecha_vencimiento, "%d/%m/%Y")
            fecha_comp = datetime.strptime(fecha_x, "%d/%m/%Y")
            return fecha_venc < fecha_comp
        except:
            return False

class ArchRefri:
    def __init__(self, nombre="refri.dat"):
        self.nombre = nombre
        self.alimentos = []
        self.cargar()
    def crearArchivo(self):
        n = int(input("Cant alimentos: "))
        for _ in range(n):
            a = Alimento()
            a.leer()
            self.alimentos.append(a)
        self.guardar()
    def modificar_por_nombre(self, nombre):
        for a in self.alimentos:
            if a.nombre == nombre:
                print("Nuevos datos:")
                a.leer()
                self.guardar()
                return
        print("No encontrado")
    def eliminar_por_nombre(self, nombre):
        self.alimentos = [a for a in self.alimentos if a.nombre != nombre]
        self.guardar()
    def b(self, fecha_x):
        for a in self.alimentos:
            if a.caduca_antes_de(fecha_x):
                a.mostrar()
    def c(self):
        self.alimentos = [a for a in self.alimentos if a.cantidad > 0]
        self.guardar()
    def d(self):
        for a in self.alimentos:
            if a.esta_vencido():
                a.mostrar()
    def e(self):
        if not self.alimentos:
            return
        max_alimento = max(self.alimentos, key=lambda x: x.cantidad)
        max_alimento.mostrar()
    def listar(self):
        for a in self.alimentos:
            a.mostrar()
    def guardar(self):
        with open(self.nombre, 'wb') as f:
            pickle.dump(self.alimentos, f)
    def cargar(self):
        if os.path.exists(self.nombre):
            with open(self.nombre, 'rb') as f:
                self.alimentos = pickle.load(f)

if __name__ == "__main__":
    arch = ArchRefri()
    arch.crearArchivo()
    arch.modificar_por_nombre("Leche")
    arch.eliminar_por_nombre("Pan")
    arch.b("01/01/2024")
    arch.c()
    arch.d()
    arch.e()