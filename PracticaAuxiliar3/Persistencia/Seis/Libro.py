import pickle
import os
from datetime import datetime

class Libro:
    def __init__(self, codLibro=0, titulo="", precio=0.0):
        self.codLibro = codLibro
        self.titulo = titulo
        self.precio = precio
    def leer(self):
        self.codLibro = int(input("Cod libro: "))
        self.titulo = input("Titulo: ")
        self.precio = float(input("Precio: "))
    def mostrar(self):
        print(f"{self.codLibro} {self.titulo} {self.precio}")

class Prestamo:
    def __init__(self, codCliente=0, codLibro=0, fechaPrestamo="", cantidad=0):
        self.codCliente = codCliente
        self.codLibro = codLibro
        self.fechaPrestamo = fechaPrestamo
        self.cantidad = cantidad
    def leer(self):
        self.codCliente = int(input("Cod cliente: "))
        self.codLibro = int(input("Cod libro: "))
        self.fechaPrestamo = input("Fecha: ")
        self.cantidad = int(input("Cantidad: "))
    def mostrar(self):
        print(f"{self.codCliente} {self.codLibro} {self.fechaPrestamo} {self.cantidad}")

class Cliente:
    def __init__(self, codCliente=0, ci="", nombre="", apellido=""):
        self.codCliente = codCliente
        self.ci = ci
        self.nombre = nombre
        self.apellido = apellido
    def leer(self):
        self.codCliente = int(input("Cod cliente: "))
        self.ci = input("CI: ")
        self.nombre = input("Nombre: ")
        self.apellido = input("Apellido: ")
    def mostrar(self):
        print(f"{self.codCliente} {self.ci} {self.nombre} {self.apellido}")

class ArchLibro:
    def __init__(self, nomArch="libros.dat"):
        self.nomArch = nomArch
        self.libros = []
        self.cargar()
    def crearArchivo(self):
        n = int(input("Cant libros: "))
        for _ in range(n):
            l = Libro()
            l.leer()
            self.libros.append(l)
        self.guardar()
    def adicionar(self):
        l = Libro()
        l.leer()
        self.libros.append(l)
        self.guardar()
    def listar(self):
        for l in self.libros:
            l.mostrar()
    def a(self, x, y):
        for l in self.libros:
            if x <= l.precio <= y:
                l.mostrar()
    def c(self, archPrestamo):
        prestamos_cod = set(p.codLibro for p in archPrestamo.prestamos)
        for l in self.libros:
            if l.codLibro not in prestamos_cod:
                l.mostrar()
    def guardar(self):
        with open(self.nomArch, 'wb') as f:
            pickle.dump(self.libros, f)
    def cargar(self):
        if os.path.exists(self.nomArch):
            with open(self.nomArch, 'rb') as f:
                self.libros = pickle.load(f)

class ArchPrestamo:
    def __init__(self, nomArch="prestamos.dat"):
        self.nomArch = nomArch
        self.prestamos = []
        self.cargar()
    def crearArchivo(self):
        n = int(input("Cant prestamos: "))
        for _ in range(n):
            p = Prestamo()
            p.leer()
            self.prestamos.append(p)
        self.guardar()
    def adicionar(self):
        p = Prestamo()
        p.leer()
        self.prestamos.append(p)
        self.guardar()
    def listar(self):
        for p in self.prestamos:
            p.mostrar()
    def b(self, codLibro, archLibro):
        libro = next((l for l in archLibro.libros if l.codLibro == codLibro), None)
        if libro:
            total = sum(p.cantidad * libro.precio for p in self.prestamos if p.codLibro == codLibro)
            print(f"Ingreso total: {total}")
    def d(self, codLibro, archCliente):
        clientes_cod = set(p.codCliente for p in self.prestamos if p.codLibro == codLibro)
        for c in archCliente.clientes:
            if c.codCliente in clientes_cod:
                c.mostrar()
    def e(self):
        conteo = {}
        for p in self.prestamos:
            conteo[p.codLibro] = conteo.get(p.codLibro, 0) + p.cantidad
        if conteo:
            max_libro = max(conteo, key=conteo.get)
            print(f"Libro mas prestado: {max_libro}")
    def f(self):
        conteo = {}
        for p in self.prestamos:
            conteo[p.codCliente] = conteo.get(p.codCliente, 0) + 1
        if conteo:
            max_cliente = max(conteo, key=conteo.get)
            print(f"Cliente con mas prestamos: {max_cliente}")
    def guardar(self):
        with open(self.nomArch, 'wb') as f:
            pickle.dump(self.prestamos, f)
    def cargar(self):
        if os.path.exists(self.nomArch):
            with open(self.nomArch, 'rb') as f:
                self.prestamos = pickle.load(f)

class ArchCliente:
    def __init__(self, nomArch="clientes.dat"):
        self.nomArch = nomArch
        self.clientes = []
        self.cargar()
    def crearArchivo(self):
        n = int(input("Cant clientes: "))
        for _ in range(n):
            c = Cliente()
            c.leer()
            self.clientes.append(c)
        self.guardar()
    def adicionar(self):
        c = Cliente()
        c.leer()
        self.clientes.append(c)
        self.guardar()
    def listar(self):
        for c in self.clientes:
            c.mostrar()
    def guardar(self):
        with open(self.nomArch, 'wb') as f:
            pickle.dump(self.clientes, f)
    def cargar(self):
        if os.path.exists(self.nomArch):
            with open(self.nomArch, 'rb') as f:
                self.clientes = pickle.load(f)

if __name__ == "__main__":
    archLibro = ArchLibro()
    archPrestamo = ArchPrestamo()
    archCliente = ArchCliente()
    archLibro.a(10, 50)
    archPrestamo.b(101, archLibro)
    archLibro.c(archPrestamo)
    archPrestamo.d(101, archCliente)
    archPrestamo.e()
    archPrestamo.f()