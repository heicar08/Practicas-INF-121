class Nadador:
    def __init__(self, estilo_natacion):
        self.estiloNatacion = estilo_natacion
    
    def nadar(self):
        print(f"Nadando estilo {self.estiloNatacion}")

class Ciclista:
    def __init__(self, tipo_bicicleta):
        self.tipoBicicleta = tipo_bicicleta
    
    def pedalear(self):
        print(f"Pedaleando en bicicleta tipo {self.tipoBicicleta}")

class Corredor:
    def __init__(self, distancia_preferida):
        self.distanciaPreferida = distancia_preferida
    
    def correr(self):
        print(f"Corriendo una distancia de {self.distanciaPreferida} km")

class Triatleta(Nadador, Ciclista, Corredor):
    def __init__(self, estilo_natacion, tipo_bicicleta, distancia_preferida, nombre):
        Nadador.__init__(self, estilo_natacion)
        Ciclista.__init__(self, tipo_bicicleta)
        Corredor.__init__(self, distancia_preferida)
        self.nombre = nombre
    
    def mostrar_acciones(self):
        print(f"=== ACCIONES DE {self.nombre.upper()} ===")
        self.nadar()
        self.pedalear()
        self.correr()

triatleta1 = Triatleta("Crol", "Carretera", 10, "Ana García")
triatleta2 = Triatleta("Espalda", "Montaña", 5, "Carlos López")

triatleta1.mostrar_acciones()
print()
triatleta2.mostrar_acciones()