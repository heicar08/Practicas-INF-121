class Fruta:
    def __init__(self, nombre="", tipo="", vitaminas_iniciales=None):
        self.nombre = nombre
        self.tipo = tipo
        self.nroVitaminas = 0
        self.v = [""] * 30
        
        if vitaminas_iniciales:
            for vit in vitaminas_iniciales:
                self.agregar_vitamina(vit)
    
    def agregar_vitamina(self, vitamina):
        if self.nroVitaminas < 30:
            self.v[self.nroVitaminas] = vitamina
            self.nroVitaminas += 1
            return True
        return False
    
    def mostrar_vitaminas(self):
        print(f"Vitaminas de {self.nombre}: {self.v[:self.nroVitaminas]}")
    
    def es_citrica(self):
        return self.tipo.lower() == "cítrica"
    
    def obtener_vitaminas(self):
        return self.v[:self.nroVitaminas]

def instanciar_frutas():
    fruta1 = Fruta("Naranja", "Cítrica", ["C", "A", "B"])
    
    fruta2 = Fruta("Manzana", "Templada")
    fruta2.agregar_vitamina("C")
    fruta2.agregar_vitamina("K")
    
    fruta3 = Fruta()
    fruta3.nombre = "Kiwi"
    fruta3.tipo = "Subtropical"
    fruta3.agregar_vitamina("C")
    fruta3.agregar_vitamina("E")
    fruta3.agregar_vitamina("K")
    
    return [fruta1, fruta2, fruta3]

def fruta_con_mas_vitaminas(frutas):
    max_vitaminas = 0
    fruta_max = None
    
    for fruta in frutas:
        if fruta.nroVitaminas > max_vitaminas:
            max_vitaminas = fruta.nroVitaminas
            fruta_max = fruta
    
    if fruta_max:
        print(f"Fruta con más vitaminas: {fruta_max.nombre} ({fruta_max.nroVitaminas} vitaminas)")
    return fruta_max

def mostrar_vitaminas_fruta_x(frutas, nombre_fruta):
    for fruta in frutas:
        if fruta.nombre.lower() == nombre_fruta.lower():
            fruta.mostrar_vitaminas()
            return
    print(f"Fruta '{nombre_fruta}' no encontrada")

def contar_vitaminas_citricas(frutas):
    count = 0
    for fruta in frutas:
        if fruta.es_citrica():
            count += fruta.nroVitaminas
    print(f"Total de vitaminas en frutas cítricas: {count}")
    return count

def ordenar_por_vitaminas(frutas):
    def obtener_primer_vitamina(fruta):
        return fruta.v[0] if fruta.nroVitaminas > 0 else ""
    
    frutas_ordenadas = sorted(frutas, key=obtener_primer_vitamina)
    
    print("\nFrutas ordenadas por primera vitamina:")
    for fruta in frutas_ordenadas:
        if fruta.nroVitaminas > 0:
            print(f"{fruta.nombre}: {fruta.v[0]}")
        else:
            print(f"{fruta.nombre}: Sin vitaminas")

if __name__ == "__main__":
    print("=== SISTEMA DE FRUTAS ===\n")
    
    frutas = instanciar_frutas()
    
    print("--- Frutas instanciadas de 3 maneras diferentes ---")
    for i, fruta in enumerate(frutas, 1):
        print(f"{i}. {fruta.nombre} | Tipo: {fruta.tipo} | Vitaminas: {fruta.nroVitaminas}")
    
    print("\n" + "="*50)
    fruta_con_mas_vitaminas(frutas)
    
    print("\n" + "="*50)
    print("Vitaminas de fruta específica (Kiwi):")
    mostrar_vitaminas_fruta_x(frutas, "Kiwi")
    
    print("\n" + "="*50)
    contar_vitaminas_citricas(frutas)
    
    print("\n" + "="*50)
    ordenar_por_vitaminas(frutas)