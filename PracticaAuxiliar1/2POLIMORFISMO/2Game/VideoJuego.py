class Videojuego:
    def __init__(self, nombre=None, plataforma=None, cantidad_jugadores=0):
      
        if nombre is None:
            self.nombre = ""
            self.plataforma = ""
            self.cantidad_jugadores = 0
        else:
            self.nombre = nombre
            self.plataforma = plataforma if plataforma else "Multiplataforma"
            self.cantidad_jugadores = cantidad_jugadores
    
    # Sobrecarga con métodos de clase (alternativa)
    @classmethod
    def crear_solo_nombre(cls, nombre):
        return cls(nombre=nombre, plataforma="Multiplataforma")
    
    @classmethod
    def crear_completo(cls, nombre, plataforma):
        return cls(nombre=nombre, plataforma=plataforma)
    
    # Para la sobrecarga de métodos usamos parámetros por defecto
    def agregar_jugadores(self, cantidad=None):
        if cantidad is None:
            self.cantidad_jugadores += 1
        else:
            self.cantidad_jugadores += cantidad
    
    def mostrar(self):
        return f"Videojuego: {self.nombre}, Plataforma: {self.plataforma}, Jugadores: {self.cantidad_jugadores}"


if __name__ == "__main__":
   
    juego1 = Videojuego() 
    juego1.nombre = "FIFA 24"
    juego1.plataforma = "PlayStation"
    
    juego2 = Videojuego.crear_solo_nombre("Minecraft")
    juego3 = Videojuego.crear_completo("Call of Duty", "PC")
    
    print("Antes de agregar jugadores:")
    print(juego1.mostrar())
    print(juego2.mostrar())
    print(juego3.mostrar())
    
  
    juego1.agregar_jugadores()      
    juego2.agregar_jugadores(4)     
    juego3.agregar_jugadores(2)     
    
    print("\nDespués de agregar jugadores:")
    print(juego1.mostrar())
    print(juego2.mostrar())
    print(juego3.mostrar())