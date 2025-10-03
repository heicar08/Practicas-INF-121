class Parada:
    def __init__(self):
        self.admins = [""] * 10
        self.autos = [[["" for _ in range(3)] for _ in range(10)]]
        self.nombreP = ""
        self.nroAdmins = 0
        self.nroAutos = 0

    def __init__(self, nombre):
        self.admins = [""] * 10
        self.autos = [[["" for _ in range(3)] for _ in range(10)]]
        self.nombreP = nombre
        self.nroAdmins = 0
        self.nroAutos = 0

    def mostrar(self):
        print(f"Parada: {self.nombreP}")
        print("Administradores:")
        for i in range(self.nroAdmins):
            print(f"  {i+1}. {self.admins[i]}")
        print("Autos:")
        for i in range(self.nroAutos):
            print(f"  {i+1}. Modelo: {self.autos[0][i][0]}, Conductor: {self.autos[0][i][1]}, Placa: {self.autos[0][i][2]}")

    def adicionar_admin(self, admin):
        if self.nroAdmins < 10:
            self.admins[self.nroAdmins] = admin
            self.nroAdmins += 1
            print(f"Admin {admin} añadido")
        else:
            print("No hay espacio para más admins")

    def adicionar_auto(self, modelo, conductor, placa):
        if self.nroAutos < 10:
            self.autos[0][self.nroAutos][0] = modelo
            self.autos[0][self.nroAutos][1] = conductor
            self.autos[0][self.nroAutos][2] = placa
            self.nroAutos += 1
            print(f"Auto {modelo} añadido")
        else:
            print("No hay espacio para más autos")

if __name__ == "__main__":
    parada1 = Parada("Parada Central")
    
    parada1.adicionar_admin("Carlos")
    parada1.adicionar_admin("Ana")
    
    parada1.adicionar_auto("Toyota", "Juan", "ABC123")
    parada1.adicionar_auto("Nissan", "Maria", "XYZ789")
    
    parada1.mostrar()