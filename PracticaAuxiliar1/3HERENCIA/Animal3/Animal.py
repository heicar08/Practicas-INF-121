class Animal:
    def __init__(self, nombre, edad):
        self.nombre = nombre
        self.edad = edad
    
    def desplazarse(self):
        print(f"{self.nombre} se está desplazando de manera genérica")

class Leon(Animal):
    def desplazarse(self):
        print(f"{self.nombre} está caminando poderosamente")

class Pinguino(Animal):
    def desplazarse(self):
        print(f"{self.nombre} está nadando elegantemente")

class Canguro(Animal):
    def desplazarse(self):
        print(f"{self.nombre} está saltando ágilmente")

animales = [
    Leon("Simba", 5),
    Pinguino("Pingu", 2),
    Canguro("Saltarín", 3),
    Leon("Nala", 4)
]

for animal in animales:
    animal.desplazarse()