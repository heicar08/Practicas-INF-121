import pickle
import os

class Persona:
    def __init__(self, nombre="", apellidoPaterno="", apellidoMaterno="", ci=0):
        self.nombre = nombre
        self.apellidoPaterno = apellidoPaterno
        self.apellidoMaterno = apellidoMaterno
        self.ci = ci

class Nino(Persona):
    def __init__(self, nombre="", apellidoPaterno="", apellidoMaterno="", ci=0, edad=0, peso="", talla=""):
        super().__init__(nombre, apellidoPaterno, apellidoMaterno, ci)
        self.edad = edad
        self.peso = peso
        self.talla = talla
    def leer(self):
        self.nombre = input("Nombre: ")
        self.apellidoPaterno = input("Apellido paterno: ")
        self.apellidoMaterno = input("Apellido materno: ")
        self.ci = int(input("CI: "))
        self.edad = int(input("Edad: "))
        self.peso = input("Peso: ")
        self.talla = input("Talla: ")
    def mostrar(self):
        print(f"{self.nombre} {self.apellidoPaterno} {self.apellidoMaterno} {self.ci} {self.edad} {self.peso} {self.talla}")
    def peso_adecuado(self):
        return self.peso == "adecuado"
    def talla_adecuada(self):
        return self.talla == "adecuada"

class ArchNino:
    def __init__(self, na="ninos.dat"):
        self.na = na
        self.ninos = []
        self.cargar()
    def crearArchivo(self):
        n = int(input("Cantidad de ninos: "))
        for _ in range(n):
            nino = Nino()
            nino.leer()
            self.ninos.append(nino)
        self.guardar()
    def adicionar(self):
        nino = Nino()
        nino.leer()
        self.ninos.append(nino)
        self.guardar()
    def listar(self):
        for nino in self.ninos:
            nino.mostrar()
    def b(self):
        contador = 0
        for nino in self.ninos:
            if nino.peso_adecuado():
                contador += 1
        print(f"Ninos con peso adecuado: {contador}")
    def c(self):
        for nino in self.ninos:
            if not nino.peso_adecuado() or not nino.talla_adecuada():
                nino.mostrar()
    def d(self):
        if not self.ninos:
            print("Promedio edad: 0")
            return
        suma_edades = sum(nino.edad for nino in self.ninos)
        promedio = suma_edades / len(self.ninos)
        print(f"Promedio edad: {promedio}")
    def e(self, ci):
        for nino in self.ninos:
            if nino.ci == ci:
                nino.mostrar()
                return
        print("No encontrado")
    def f(self):
        if not self.ninos:
            return
        max_talla = max(nino.talla for nino in self.ninos)
        for nino in self.ninos:
            if nino.talla == max_talla:
                nino.mostrar()
    def guardar(self):
        with open(self.na, 'wb') as f:
            pickle.dump(self.ninos, f)
    def cargar(self):
        if os.path.exists(self.na):
            with open(self.na, 'rb') as f:
                self.ninos = pickle.load(f)

if __name__ == "__main__":
    arch = ArchNino()
    arch.crearArchivo()
    arch.listar()
    arch.b()
    arch.c()
    arch.d()
    arch.e(12345)
    arch.f()