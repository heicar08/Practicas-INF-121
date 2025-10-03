class Estudiante:
    def __init__(self, nombre="", apellidos="", parcial1=0, parcial2=0, exfinal=0):
        self.nombre = nombre
        self.apellidos = apellidos
        self.parcial1 = parcial1
        self.parcial2 = parcial2
        self.exfinal = exfinal

    def notaFinal(self, sistema=1):
        if sistema == 1:
            return self.parcial1 + self.parcial2 + self.exfinal
        else:
            return (self.parcial1 * 0.3) + (self.parcial2 * 0.3) + (self.exfinal * 0.4)

    def aprobo(self, nota_minima=51, nota_extra=0):
        nota_total = self.notaFinal(2) + nota_extra
        return nota_total > nota_minima

    def mostrar(self, con_nota_final=False):
        print(f"Nombre: {self.nombre}")
        print(f"Apellidos: {self.apellidos}")
        print(f"Parcial 1: {self.parcial1}")
        print(f"Parcial 2: {self.parcial2}")
        print(f"Examen Final: {self.exfinal}")
        if con_nota_final:
            print(f"Nota Final (sistema 1): {self.notaFinal(1)}")
            print(f"Nota Final (sistema 2): {self.notaFinal(2)}")

if __name__ == "__main__":
    est1 = Estudiante("Ana", "Garcia", 60, 70, 80)
    est2 = Estudiante("Luis", "Martinez", 40, 50, 60)
    est3 = Estudiante("Maria", "Lopez", 80, 85, 90)
    
    print("=== Mostrar sin nota final ===")
    est1.mostrar()
    
    print("\n=== Mostrar con nota final ===")
    est1.mostrar(True)
    
    print(f"\nAprobó (default 51): {est1.aprobo()}")
    print(f"Aprobó con 60: {est1.aprobo(60)}")
    print(f"Aprobó con 70 y 5 extra: {est1.aprobo(70, 5)}")