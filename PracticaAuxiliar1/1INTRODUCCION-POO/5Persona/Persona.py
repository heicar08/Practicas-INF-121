class Persona:
    def __init__(self, nombre, paterno, materno, edad, ci):
        self.nombre = nombre
        self.paterno = paterno
        self.materno = materno
        self.edad = edad
        self.ci = ci
    
    def mostrar_datos(self):
        print(f"\n--- DATOS DE LA PERSONA ---")
        print(f"Nombre: {self.nombre}")
        print(f"Apellido Paterno: {self.paterno}")
        print(f"Apellido Materno: {self.materno}")
        print(f"Edad: {self.edad} años")
        print(f"CI: {self.ci}")
    
    def mayor_de_edad(self):
        if self.edad >= 18:
            print(f"{self.nombre} es MAYOR de edad ({self.edad} años)")
            return True
        else:
            print(f"{self.nombre} es MENOR de edad ({self.edad} años)")
            return False
    
    def modificar_edad(self, nueva_edad):
        if nueva_edad >= 0:
            edad_anterior = self.edad
            self.edad = nueva_edad
            print(f"Edad modificada: {edad_anterior} → {nueva_edad} años")
        else:
            print("Error: La edad no puede ser negativa")
    
    def mismo_paterno(self, otra_persona):
        mismo = self.paterno.lower() == otra_persona.paterno.lower()
        if mismo:
            print(f" {self.nombre} y {otra_persona.nombre} tienen el MISMO apellido paterno: {self.paterno}")
        else:
            print(f" {self.nombre} y {otra_persona.nombre} tienen DIFERENTE apellido paterno")
            print(f"   {self.paterno} ≠ {otra_persona.paterno}")
        return mismo


if __name__ == "__main__":
    
    persona1 = Persona("Juan", "Perez", "Gomez", 25, "1234567")
    persona2 = Persona("Maria", "Lopez", "Rodriguez", 16, "7654321")
    persona3 = Persona("Carlos", "Perez", "Martinez", 30, "9876543")
    
    print("=== MOSTRAR DATOS ===")
    persona1.mostrar_datos()
    persona2.mostrar_datos()
    
    print("\n=== VERIFICAR MAYORÍA DE EDAD ===")
    persona1.mayor_de_edad()
    persona2.mayor_de_edad()
    
    print("\n=== MODIFICAR EDAD ===")
    persona2.modificar_edad(18)
    persona2.mayor_de_edad()
    
    print("\n=== COMPARAR APELLIDOS PATERNO ===")
    persona1.mismo_paterno(persona2)  
    persona1.mismo_paterno(persona3)  