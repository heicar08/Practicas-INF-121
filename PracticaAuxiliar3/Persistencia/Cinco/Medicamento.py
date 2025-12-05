import pickle
import os

class Medicamento:
    def __init__(self, nombre="", codMedicamento=0, tipo="", precio=0.0):
        self.nombre = nombre
        self.codMedicamento = codMedicamento
        self.tipo = tipo
        self.precio = precio
    def leer(self):
        self.nombre = input("Nombre: ")
        self.codMedicamento = int(input("Codigo: "))
        self.tipo = input("Tipo: ")
        self.precio = float(input("Precio: "))
    def mostrar(self):
        print(f"{self.nombre} {self.codMedicamento} {self.tipo} {self.precio}")
    def getTipo(self):
        return self.tipo
    def getPrecio(self):
        return self.precio

class Farmacia:
    def __init__(self):
        self.nombreFarmacia = ""
        self.sucursal = 0
        self.direction = ""
        self.moMedicamentos = 0
        self.m = [Medicamento() for _ in range(100)]
    def leer(self):
        self.nombreFarmacia = input("Nombre farmacia: ")
        self.sucursal = int(input("Sucursal: "))
        self.direction = input("Direccion: ")
        self.moMedicamentos = int(input("Cant medicamentos: "))
        for i in range(self.moMedicamentos):
            self.m[i].leer()
    def mostrar(self):
        print(f"{self.nombreFarmacia} {self.sucursal} {self.direction}")
        for i in range(self.moMedicamentos):
            self.m[i].mostrar()
    def getDireccion(self):
        return self.direction
    def getSucursal(self):
        return self.sucursal
    def mostrarMedicamentos(self, x):
        for i in range(self.moMedicamentos):
            if self.m[i].getTipo() == x:
                self.m[i].mostrar()
    def buscaMedicamento(self, x):
        for i in range(self.moMedicamentos):
            if self.m[i].nombre == x:
                return self.m[i]
        return None

class ArchFarmacia:
    def __init__(self, na="farmacias.dat"):
        self.na = na
        self.farmacias = []
        self.cargar()
    def crearArchivo(self):
        n = int(input("Cant farmacias: "))
        for _ in range(n):
            f = Farmacia()
            f.leer()
            self.farmacias.append(f)
        self.guardar()
    def adicionar(self):
        f = Farmacia()
        f.leer()
        self.farmacias.append(f)
        self.guardar()
    def listar(self):
        for f in self.farmacias:
            f.mostrar()
    def mostrarMedicamentosResfrios(self):
        for f in self.farmacias:
            f.mostrarMedicamentos("Resfrio")
    def predoMedicamentoTos(self):
        precio_total = 0
        cantidad = 0
        for f in self.farmacias:
            for i in range(f.moMedicamentos):
                if f.m[i].getTipo() == "Tos":
                    precio_total += f.m[i].getPrecio()
                    cantidad += 1
        return precio_total / cantidad if cantidad > 0 else 0
    def mostrarMedicamentosMenorTos(self):
        promedio = self.predoMedicamentoTos()
        for f in self.farmacias:
            for i in range(f.moMedicamentos):
                if f.m[i].getTipo() == "Tos" and f.m[i].getPrecio() < promedio:
                    f.m[i].mostrar()
    def a(self, sucursal_x):
        for f in self.farmacias:
            if f.getSucursal() == sucursal_x:
                f.mostrarMedicamentos("Tos")
    def b(self):
        for f in self.farmacias:
            for i in range(f.moMedicamentos):
                if f.m[i].nombre == "Tapsin":
                    print(f"{f.getSucursal()} {f.getDireccion()}")
    def c(self, tipo):
        for f in self.farmacias:
            f.mostrarMedicamentos(tipo)
    def d(self):
        self.farmacias.sort(key=lambda x: x.getDireccion())
        self.guardar()
        self.listar()
    def e(self, tipo, sucursal_y, sucursal_z):
        farmacia_y = next((f for f in self.farmacias if f.getSucursal() == sucursal_y), None)
        farmacia_z = next((f for f in self.farmacias if f.getSucursal() == sucursal_z), None)
        if not farmacia_y or not farmacia_z:
            return
        movidos = []
        i = 0
        while i < farmacia_y.moMedicamentos:
            if farmacia_y.m[i].getTipo() == tipo:
                movidos.append(farmacia_y.m[i])
                for j in range(i, farmacia_y.moMedicamentos - 1):
                    farmacia_y.m[j] = farmacia_y.m[j + 1]
                farmacia_y.moMedicamentos -= 1
            else:
                i += 1
        for med in movidos:
            if farmacia_z.moMedicamentos < 100:
                farmacia_z.m[farmacia_z.moMedicamentos] = med
                farmacia_z.moMedicamentos += 1
        self.guardar()
    def guardar(self):
        with open(self.na, 'wb') as f:
            pickle.dump(self.farmacias, f)
    def cargar(self):
        if os.path.exists(self.na):
            with open(self.na, 'rb') as f:
                self.farmacias = pickle.load(f)

if __name__ == "__main__":
    arch = ArchFarmacia()
    arch.crearArchivo()
    arch.a(1)
    arch.b()
    arch.c("Resfrio")
    arch.d()
    arch.e("Tos", 1, 2)