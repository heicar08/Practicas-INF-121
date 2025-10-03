class Politico:
    def __init__(self, profesion, años_exp):
        self.profesion = profesion
        self.añosExp = años_exp

class Partido:
    def __init__(self, nombre_p, rol):
        self.nombreP = nombre_p
        self.rol = rol

class Presidente(Politico, Partido):
    def __init__(self, nombre, apellido, profesion, años_exp, nombre_p, rol):
        Politico.__init__(self, profesion, años_exp)
        Partido.__init__(self, nombre_p, rol)
        self.nombre = nombre
        self.apellido = apellido
    
    def mostrar(self):
        print(f"Presidente: {self.nombre} {self.apellido}")
        print(f"Profesión: {self.profesion}")
        print(f"Años de experiencia: {self.añosExp}")
        print(f"Partido: {self.nombreP}")
        print(f"Rol: {self.rol}")

presidente1 = Presidente("Juan", "Gómez", "Abogado", 15, "Partido Progresista", "Líder")

nombre = "María"
apellido = "López"
profesion = "Economista"
experiencia = 12
partido = "Partido Democrático"
rol = "Presidente"

presidente2 = Presidente(nombre, apellido, profesion, experiencia, partido, rol)

presidentes = [
    presidente1,
    presidente2,
    Presidente("Carlos", "Rodríguez", "Ingeniero", 20, "Partido Verde", "Fundador")
]

def buscar_presidente(nombre_buscar, lista_presidentes):
    for pres in lista_presidentes:
        if pres.nombre.lower() == nombre_buscar.lower():
            return pres
    return None

nombre_buscar = "María"
presidente_encontrado = buscar_presidente(nombre_buscar, presidentes)

if presidente_encontrado:
    print(f"=== PRESIDENTE ENCONTRADO: {nombre_buscar} ===")
    presidente_encontrado.mostrar()
else:
    print(f"Presidente {nombre_buscar} no encontrado")

print("\n=== TODOS LOS PRESIDENTES ===")
for i, pres in enumerate(presidentes, 1):
    print(f"--- Presidente {i} ---")
    pres.mostrar()
    print()