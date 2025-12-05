import pickle
import os

class Animal:
    def __init__(self, especie="", nombre="", cantidad=0):
        self.especie = especie
        self.nombre = nombre
        self.cantidad = cantidad
    def leer(self):
        self.especie = input("Especie: ")
        self.nombre = input("Nombre: ")
        self.cantidad = int(input("Cantidad: "))
    def mostrar(self):
        print(f"{self.especie} {self.nombre} {self.cantidad}")

class Zoologico:
    def __init__(self, id=0, nombre="", nroAnimales=0):
        self.id = id
        self.nombre = nombre
        self.nroAnimales = nroAnimales
        self.animales = [Animal() for _ in range(30)]
    def leer(self):
        self.id = int(input("ID: "))
        self.nombre = input("Nombre: ")
        self.nroAnimales = int(input("Nro animales: "))
        for i in range(self.nroAnimales):
            self.animales[i].leer()
    def mostrar(self):
        print(f"{self.id} {self.nombre} {self.nroAnimales}")
        for i in range(self.nroAnimales):
            self.animales[i].mostrar()
    def variedad_animales(self):
        especies = set()
        for i in range(self.nroAnimales):
            especies.add(self.animales[i].especie)
        return len(especies)
    def esta_vacio(self):
        return self.nroAnimales == 0
    def animales_especie_x(self, especie_x):
        for i in range(self.nroAnimales):
            if self.animales[i].especie == especie_x:
                self.animales[i].mostrar()
    def agregar_animal(self, animal):
        if self.nroAnimales < 30:
            self.animales[self.nroAnimales] = animal
            self.nroAnimales += 1
    def quitar_animales_especie(self, especie_x):
        i = 0
        removidos = []
        while i < self.nroAnimales:
            if self.animales[i].especie == especie_x:
                removidos.append(self.animales[i])
                for j in range(i, self.nroAnimales - 1):
                    self.animales[j] = self.animales[j + 1]
                self.nroAnimales -= 1
            else:
                i += 1
        return removidos

class ArchZoo:
    def __init__(self, nombre="zoologicos.dat"):
        self.nombre = nombre
        self.zoos = []
        self.cargar()
    def crearArchivo(self):
        n = int(input("Cant zoologicos: "))
        for _ in range(n):
            z = Zoologico()
            z.leer()
            self.zoos.append(z)
        self.guardar()
    def modificar(self, id_zoo):
        for z in self.zoos:
            if z.id == id_zoo:
                z.leer()
                self.guardar()
                return
        print("No encontrado")
    def eliminar(self, id_zoo):
        self.zoos = [z for z in self.zoos if z.id != id_zoo]
        self.guardar()
    def b(self):
        if not self.zoos:
            return
        max_variedad = max(z.variedad_animales() for z in self.zoos)
        for z in self.zoos:
            if z.variedad_animales() == max_variedad:
                z.mostrar()
    def c(self):
        vacios = [z for z in self.zoos if z.esta_vacio()]
        for z in vacios:
            z.mostrar()
        self.zoos = [z for z in self.zoos if not z.esta_vacio()]
        self.guardar()
    def d(self, especie_x):
        for z in self.zoos:
            z.animales_especie_x(especie_x)
    def e(self, id_x, id_y, especie_x):
        zoo_x = next((z for z in self.zoos if z.id == id_x), None)
        zoo_y = next((z for z in self.zoos if z.id == id_y), None)
        if not zoo_x or not zoo_y:
            return
        animales_mover = zoo_x.quitar_animales_especie(especie_x)
        for animal in animales_mover:
            zoo_y.agregar_animal(animal)
        self.guardar()
    def listar(self):
        for z in self.zoos:
            z.mostrar()
    def guardar(self):
        with open(self.nombre, 'wb') as f:
            pickle.dump(self.zoos, f)
    def cargar(self):
        if os.path.exists(self.nombre):
            with open(self.nombre, 'rb') as f:
                self.zoos = pickle.load(f)

if __name__ == "__main__":
    arch = ArchZoo()
    arch.crearArchivo()
    arch.modificar(1)
    arch.eliminar(2)
    arch.b()
    arch.c()
    arch.d("Leon")
    arch.e(1, 3, "Tigre")