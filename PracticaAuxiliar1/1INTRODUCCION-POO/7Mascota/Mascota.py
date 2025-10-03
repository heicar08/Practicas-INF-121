class Mascota:
    def __init__(self, nombre, tipo):
        self.nombre = nombre
        self.tipo = tipo
        self.energia = 50  
    
    
    def alimentar(self):
        energia_antes = self.energia
        self.energia = min(100, self.energia + 20)
        aumento = self.energia - energia_antes
        print(f" {self.nombre} fue alimentado(+{aumento}). Energía: {energia_antes} → {self.energia}")
    
    
    def jugar(self):
        if self.energia >= 15:
            energia_antes = self.energia
            self.energia = max(0, self.energia - 15)
            print(f" {self.nombre} jugó(-15). Energía: {energia_antes} → {self.energia}")
        else:
            print(f" {self.nombre} está muy cansado para jugar. Energía: {self.energia}")
    
    def mostrar_estado(self):
        estado = " Energético" if self.energia > 70 else "Normal" if self.energia > 30 else "Cansado"
        print(f"\n--- ESTADO DE {self.nombre.upper()} ---")
        print(f"Tipo: {self.tipo}")
        print(f"Energía: {self.energia}/100 - {estado}")


if __name__ == "__main__":
   
    mascota1 = Mascota("Firulais", "Perro")
    mascota2 = Mascota("Mishi", "Gato")
    
    
    print("=== ESTADO INICIAL ===")
    mascota1.mostrar_estado()
    mascota2.mostrar_estado()
    
   
    print("\n=== ALIMENTACIÓN ===")
    mascota1.alimentar()
    mascota2.alimentar()
    
   
    print("\n=== JUGANDO ===")
    mascota1.jugar()
    mascota2.jugar()
    
 
    print("\n=== MÁS OPERACIONES ===")
    mascota1.alimentar()
    mascota1.alimentar()  
    mascota1.jugar()
    mascota1.jugar()
    mascota1.jugar()  
    
   
    print("\n=== ESTADO FINAL ===")
    mascota1.mostrar_estado()
    mascota2.mostrar_estado()