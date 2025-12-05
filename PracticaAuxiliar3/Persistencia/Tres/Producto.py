import pickle
import os

class Producto:
    def __init__(self, codigo, nombre, precio):
        self.codigo = codigo
        self.nombre = nombre
        self.precio = precio
    
    def __str__(self):
        return f"{self.codigo} | {self.nombre:20} | ${self.precio:10.2f}"

class ArchivoProducto:
    def __init__(self, nomA="productos.dat"):
        self.nomA = nomA
        self.productos = []
        self.cargar()
    
    def crearArchivo(self):
        try:
            with open(self.nomA, 'wb') as f:
                pickle.dump([], f)
            return True
        except:
            return False
    
    def guardaProducto(self, p):
        self.productos.append(p)
        self.guardar()
    
    def buscaProducto(self, c):
        for p in self.productos:
            if p.codigo == c:
                return p
        return None
    
    def promedioPrecios(self):
        if not self.productos:
            return 0
        total = sum(p.precio for p in self.productos)
        return total / len(self.productos)
    
    def productoMasCaro(self):
        if not self.productos:
            return None
        return max(self.productos, key=lambda p: p.precio)
    
    def guardar(self):
        try:
            with open(self.nomA, 'wb') as f:
                pickle.dump(self.productos, f)
        except:
            pass
    
    def cargar(self):
        try:
            if os.path.exists(self.nomA):
                with open(self.nomA, 'rb') as f:
                    self.productos = pickle.load(f)
        except:
            self.productos = []
    
    def obtenerTodos(self):
        return self.productos.copy()
    
    def eliminarProducto(self, codigo):
        for i, p in enumerate(self.productos):
            if p.codigo == codigo:
                del self.productos[i]
                self.guardar()
                return True
        return False
    
    def generarEjemplos(self):
        if not self.productos:
            ejemplos = [
                Producto(1001, "Laptop", 1200.50),
                Producto(1002, "Mouse", 25.99),
                Producto(1003, "Teclado", 45.75),
                Producto(1004, "Monitor", 350.00),
                Producto(1005, "Tablet", 550.80)
            ]
            for p in ejemplos:
                self.guardaProducto(p)

class SistemaProductos:
    def __init__(self):
        self.archivo = ArchivoProducto()
    
    def iniciar(self):
        while True:
            print("\n" + "="*50)
            print("SISTEMA DE PRODUCTOS")
            print("="*50)
            print("1. Crear archivo")
            print("2. Mostrar todos")
            print("3. Agregar producto")
            print("4. Buscar producto")
            print("5. Promedio precios")
            print("6. Producto más caro")
            print("7. Eliminar producto")
            print("8. Generar ejemplos")
            print("0. Salir")
            print("="*50)
            
            op = input("Opción: ").strip()
            
            if op == "0":
                print("¡Adiós!")
                break
            elif op == "1":
                self.crear_archivo()
            elif op == "2":
                self.mostrar_todos()
            elif op == "3":
                self.agregar_producto()
            elif op == "4":
                self.buscar_producto()
            elif op == "5":
                self.promedio_precios()
            elif op == "6":
                self.producto_mas_caro()
            elif op == "7":
                self.eliminar_producto()
            elif op == "8":
                self.generar_ejemplos()
    
    def crear_archivo(self):
        if self.archivo.crearArchivo():
            print("✓ Archivo creado")
        else:
            print("✗ Error al crear archivo")
    
    def mostrar_todos(self):
        productos = self.archivo.obtenerTodos()
        if not productos:
            print("No hay productos")
            return
        
        print("\n" + "="*50)
        print(f"{'Código':<10} {'Nombre':<20} {'Precio':<10}")
        print("-"*50)
        for p in productos:
            print(f"{p.codigo:<10} {p.nombre:<20} ${p.precio:<10.2f}")
        print("="*50)
        print(f"Total: {len(productos)} productos")
    
    def agregar_producto(self):
        try:
            codigo = int(input("Código: "))
            if codigo <= 0:
                print("Código debe ser positivo")
                return
            
            nombre = input("Nombre: ").strip()
            if not nombre:
                print("Nombre requerido")
                return
            
            precio = float(input("Precio: "))
            if precio <= 0:
                print("Precio debe ser positivo")
                return
            
            p = Producto(codigo, nombre, precio)
            self.archivo.guardaProducto(p)
            print("✓ Producto agregado")
            
        except ValueError:
            print("Entrada inválida")
    
    def buscar_producto(self):
        try:
            codigo = int(input("Código a buscar: "))
            p = self.archivo.buscaProducto(codigo)
            
            if p:
                print(f"\nProducto encontrado:")
                print(f"Código: {p.codigo}")
                print(f"Nombre: {p.nombre}")
                print(f"Precio: ${p.precio:.2f}")
            else:
                print("Producto no encontrado")
                
        except ValueError:
            print("Código inválido")
    
    def promedio_precios(self):
        promedio = self.archivo.promedioPrecios()
        print(f"\nPromedio de precios: ${promedio:.2f}")
    
    def producto_mas_caro(self):
        p = self.archivo.productoMasCaro()
        if p:
            print(f"\nProducto más caro:")
            print(f"Código: {p.codigo}")
            print(f"Nombre: {p.nombre}")
            print(f"Precio: ${p.precio:.2f}")
        else:
            print("No hay productos")
    
    def eliminar_producto(self):
        try:
            codigo = int(input("Código a eliminar: "))
            if self.archivo.eliminarProducto(codigo):
                print("✓ Producto eliminado")
            else:
                print("Producto no encontrado")
        except ValueError:
            print("Código inválido")
    
    def generar_ejemplos(self):
        self.archivo.generarEjemplos()
        print("✓ Ejemplos generados")

if __name__ == "__main__":
    app = SistemaProductos()
    app.iniciar()