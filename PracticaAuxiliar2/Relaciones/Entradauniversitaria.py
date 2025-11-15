class Persona:
    def __init__(self, nombre, edad, dni):
        self.nombre = nombre
        self.edad = edad
        self.dni = dni

class Facultad:
    def __init__(self, nombre, codigo):
        self.nombre = nombre
        self.codigo = codigo

class Fraternidad:
    def __init__(self, nombre, encargado):
        self.nombre = nombre
        self.encargado = encargado
        self.bailarines = []
    
    def agregar_bailarin(self, bailarin):
        if bailarin.fraternidad:
            print(f"Error: {bailarin.nombre} ya pertenece a {bailarin.fraternidad.nombre}")
            return False
        self.bailarines.append(bailarin)
        bailarin.fraternidad = self
        print(f"{bailarin.nombre} agregado a {self.nombre}")
        return True

class Bailarin(Persona):
    def __init__(self, nombre, edad, dni, facultad):
        super().__init__(nombre, edad, dni)
        self.facultad = facultad
        self.fraternidad = None

class SistemaFraternidades:
    def __init__(self):
        self.fraternidades = []
        self.facultades = []
        self.bailarines = []
    
    def registrar_facultad(self, facultad):
        self.facultades.append(facultad)
    
    def registrar_fraternidad(self, fraternidad):
        self.fraternidades.append(fraternidad)
    
    def registrar_bailarin(self, nombre, edad, dni, facultad_nombre):
        facultad = next((f for f in self.facultades if f.nombre == facultad_nombre), None)
        if not facultad:
            print(f"Error: Facultad {facultad_nombre} no encontrada")
            return None
        
        bailarin = Bailarin(nombre, edad, dni, facultad)
        self.bailarines.append(bailarin)
        return bailarin
    
    def asignar_bailarin_fraternidad(self, bailarin_nombre, fraternidad_nombre):
        bailarin = next((b for b in self.bailarines if b.nombre == bailarin_nombre), None)
        fraternidad = next((f for f in self.fraternidades if f.nombre == fraternidad_nombre), None)
        
        if not bailarin or not fraternidad:
            print("Bailarín o fraternidad no encontrados")
            return False
        
        return fraternidad.agregar_bailarin(bailarin)
    
    def mostrar_bailarines_fraternidad_facultad(self):
        print("\n=== BAILARINES POR FRATERNIDAD Y FACULTAD ===")
        for fraternidad in self.fraternidades:
            print(f"\nFraternidad: {fraternidad.nombre}")
            print(f"Encargado: {fraternidad.encargado.nombre}")
            if fraternidad.bailarines:
                for bailarin in fraternidad.bailarines:
                    print(f"  - {bailarin.nombre} | Facultad: {bailarin.facultad.nombre} | Edad: {bailarin.edad}")
            else:
                print("  No tiene bailarines")
    
    def mostrar_edades_participantes(self):
        print("\n=== EDADES DE LOS PARTICIPANTES ===")
        for bailarin in self.bailarines:
            print(f"{bailarin.nombre}: {bailarin.edad} años")
    
    def verificar_multiples_fraternidades(self):
        print("\n=== VERIFICACIÓN DE FRATERNIDADES MÚLTIPLES ===")
        bailarines_sin_fraternidad = []
        bailarines_con_fraternidad = []
        
        for bailarin in self.bailarines:
            if bailarin.fraternidad:
                bailarines_con_fraternidad.append(bailarin)
            else:
                bailarines_sin_fraternidad.append(bailarin)
        
        print(f"Bailarines sin fraternidad: {len(bailarines_sin_fraternidad)}")
        for bailarin in bailarines_sin_fraternidad:
            print(f"  - {bailarin.nombre}")
        
        print(f"Bailarines con fraternidad: {len(bailarines_con_fraternidad)}")
        for bailarin in bailarines_con_fraternidad:
            print(f"  - {bailarin.nombre} -> {bailarin.fraternidad.nombre}")
        
        return len(bailarines_sin_fraternidad) == 0

sistema = SistemaFraternidades()

facultad1 = Facultad("Ingeniería", "FING001")
facultad2 = Facultad("Medicina", "FMED002")

sistema.registrar_facultad(facultad1)
sistema.registrar_facultad(facultad2)

encargado1 = Persona("Carlos Ruiz", 25, "12345678")
encargado2 = Persona("María Lopez", 24, "87654321")

fraternidad1 = Fraternidad("Los Tigres", encargado1)
fraternidad2 = Fraternidad("Los Leones", encargado2)

sistema.registrar_fraternidad(fraternidad1)
sistema.registrar_fraternidad(fraternidad2)

bailarin1 = sistema.registrar_bailarin("Juan Pérez", 20, "11111111", "Ingeniería")
bailarin2 = sistema.registrar_bailarin("Ana García", 21, "22222222", "Ingeniería")
bailarin3 = sistema.registrar_bailarin("Luis Martínez", 22, "33333333", "Medicina")
bailarin4 = sistema.registrar_bailarin("Sofia Rodríguez", 19, "44444444", "Medicina")
bailarin5 = sistema.registrar_bailarin("Pedro Sánchez", 20, "55555555", "Ingeniería")

sistema.asignar_bailarin_fraternidad("Juan Pérez", "Los Tigres")
sistema.asignar_bailarin_fraternidad("Ana García", "Los Tigres")
sistema.asignar_bailarin_fraternidad("Luis Martínez", "Los Leones")
sistema.asignar_bailarin_fraternidad("Sofia Rodríguez", "Los Leones")

print("=== SISTEMA DE FRATERNIDADES UNIVERSITARIAS ===")

sistema.mostrar_bailarines_fraternidad_facultad()

sistema.mostrar_edades_participantes()

sistema.verificar_multiples_fraternidades()

print(f"\n=== INTENTO DE ASIGNACIÓN MÚLTIPLE ===")
sistema.asignar_bailarin_fraternidad("Juan Pérez", "Los Leones")

print(f"\n=== ASIGNANDO ÚLTIMO BAILARÍN ===")
sistema.asignar_bailarin_fraternidad("Pedro Sánchez", "Los Tigres")

sistema.mostrar_bailarines_fraternidad_facultad()
sistema.verificar_multiples_fraternidades()