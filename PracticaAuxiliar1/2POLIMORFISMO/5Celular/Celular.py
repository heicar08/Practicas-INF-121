class Celular:
    def __init__(self, nro_tel="", dueño="", espacio=0, ram=0, nro_app=0):
        self.nro_tel = nro_tel
        self.dueño = dueño
        self.espacio = espacio
        self.ram = ram
        self.nro_app = nro_app

    def __pos__(self):
        self.nro_app += 10
        return self

    def __neg__(self):
        self.espacio -= 5
        if self.espacio < 0:
            self.espacio = 0
        return self

    def mostrar(self):
        print(f"Número: {self.nro_tel}")
        print(f"Dueño: {self.dueño}")
        print(f"Espacio: {self.espacio} GB")
        print(f"RAM: {self.ram} GB")
        print(f"Número de Apps: {self.nro_app}")
        print("-" * 30)

if __name__ == "__main__":
    print("ANTES:")
    celular1 = Celular("12345678", "Juan", 64, 4, 15)
    celular1.mostrar()

    +celular1
    -celular1

    print("DESPUÉS:")
    celular1.mostrar()