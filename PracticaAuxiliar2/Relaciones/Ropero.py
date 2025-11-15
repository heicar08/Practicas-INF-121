class Ropa:
    def __init__(self, tipo, material):
        self.tipo = tipo
        self.material = material
    
    def __str__(self):
        return f"{self.tipo} - {self.material}"


class Ropero:
    def __init__(self, material):
        self.material = material
        self.ropa = [None] * 20
        self.nro_ropas = 0
    
    def adicionar_prenda(self, prenda):
        if self.nro_ropas < 20:
            self.ropa[self.nro_ropas] = prenda
            self.nro_ropas += 1
            print(f"Prenda '{prenda.tipo}' agregada al ropero")
        else:
            print("El ropero está lleno, no se puede agregar más prendas")
    
    def adicionar_n_prendas(self, prendas):
        for prenda in prendas:
            if self.nro_ropas < 20:
                self.adicionar_prenda(prenda)
            else:
                print("No se pueden agregar más prendas, ropero lleno")
                break
    
    def eliminar_prendas_material_tipo(self, material=None, tipo=None):
        nuevas_prendas = []
        eliminadas = 0
        
        for i in range(self.nro_ropas):
            prenda_actual = self.ropa[i]
            if prenda_actual:
                if (material and prenda_actual.material == material) or (tipo and prenda_actual.tipo == tipo):
                    eliminadas += 1
                else:
                    nuevas_prendas.append(prenda_actual)
        
        self.ropa = nuevas_prendas + [None] * (20 - len(nuevas_prendas))
        self.nro_ropas = len(nuevas_prendas)
        
        print(f"Se eliminaron {eliminadas} prendas")
    
    def mostrar_prendas_material_tipo(self, material=None, tipo=None):
        print(f"\n--- Prendas (Material: {material}, Tipo: {tipo}) ---")
        encontradas = False
        
        for i in range(self.nro_ropas):
            prenda_actual = self.ropa[i]
            if prenda_actual:
                if (not material or prenda_actual.material == material) and (not tipo or prenda_actual.tipo == tipo):
                    print(f"- {prenda_actual}")
                    encontradas = True
        
        if not encontradas:
            print("No se encontraron prendas con esos criterios")


ropero = Ropero("Madera")

prendas = [
    Ropa("Camisa", "Algodón"),
    Ropa("Pantalón", "Jean"),
    Ropa("Vestido", "Seda"),
    Ropa("Camisa", "Lino"),
    Ropa("Chaqueta", "Cuero"),
    Ropa("Pantalón", "Algodón"),
    Ropa("Blusa", "Seda"),
    Ropa("Falda", "Jean")
]

print("=== ADICIONAR PRENDAS AL ROPERO ===")
ropero.adicionar_n_prendas(prendas)

print("\n=== MOSTRAR TODAS LAS PRENDAS ===")
ropero.mostrar_prendas_material_tipo()

print("\n=== ELIMINAR PRENDAS DE MATERIAL 'Jean' ===")
ropero.eliminar_prendas_material_tipo(material="Jean")

print("\n=== MOSTRAR PRENDAS DESPUÉS DE ELIMINAR ===")
ropero.mostrar_prendas_material_tipo()

print("\n=== MOSTRAR PRENDAS DE MATERIAL 'Algodón' ===")
ropero.mostrar_prendas_material_tipo(material="Algodón")

print("\n=== MOSTRAR PRENDAS DE TIPO 'Camisa' ===")
ropero.mostrar_prendas_material_tipo(tipo="Camisa")

print("\n=== ELIMINAR PRENDAS DE TIPO 'Camisa' ===")
ropero.eliminar_prendas_material_tipo(tipo="Camisa")

print("\n=== MOSTRAR TODAS LAS PRENDAS FINALES ===")
ropero.mostrar_prendas_material_tipo()