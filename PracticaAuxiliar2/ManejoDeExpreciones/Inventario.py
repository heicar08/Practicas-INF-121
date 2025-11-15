class ProductoNoEncontradoException(Exception):
    def __init__(self, mensaje):
        super().__init__(mensaje)

class StockInsuficienteException(Exception):
    def __init__(self, mensaje):
        super().__init__(mensaje)

class Producto:
    def __init__(self, codigo, nombre, precio, stock):
        self.codigo = codigo
        self.nombre = nombre
        self.precio = precio
        self.stock = stock
    
    def __str__(self):
        return f"{self.codigo}: {self.nombre} - ${self.precio} - Stock: {self.stock}"

class Inventario:
    def __init__(self):
        self.productos = []
    
    def agregar_producto(self, producto):
        for p in self.productos:
            if p.codigo == producto.codigo:
                raise ValueError(f"Error: El código {producto.codigo} ya existe")
        
        if producto.precio < 0:
            raise ValueError("Error: El precio no puede ser negativo")
        
        if producto.stock < 0:
            raise ValueError("Error: El stock no puede ser negativo")
        
        self.productos.append(producto)
        print(f"Producto {producto.nombre} agregado al inventario")
    
    def buscar_producto(self, codigo):
        for producto in self.productos:
            if producto.codigo == codigo:
                return producto
        raise ProductoNoEncontradoException(f"Producto con código {codigo} no encontrado")
    
    def vender_producto(self, codigo, cantidad):
        producto = self.buscar_producto(codigo)
        
        if producto.stock < cantidad:
            raise StockInsuficienteException(
                f"Stock insuficiente. Disponible: {producto.stock}, Solicitado: {cantidad}"
            )
        
        producto.stock -= cantidad
        total = producto.precio * cantidad
        print(f"Venta realizada: {cantidad} x {producto.nombre} = ${total}")
        return total
    
    def mostrar_inventario(self):
        print("\n=== INVENTARIO ACTUAL ===")
        if not self.productos:
            print("No hay productos en el inventario")
        else:
            for producto in self.productos:
                print(producto)

def probar_agregar_productos():
    print("=== PRUEBA AGREGAR PRODUCTOS ===")
    inventario = Inventario()
    
    try:
        p1 = Producto("P001", "Laptop", 1200.50, 10)
        p2 = Producto("P002", "Mouse", 25.99, 50)
        p3 = Producto("P003", "Teclado", 45.75, 30)
        
        inventario.agregar_producto(p1)
        inventario.agregar_producto(p2)
        inventario.agregar_producto(p3)
        
        inventario.mostrar_inventario()
        
    except ValueError as e:
        print(f"Error al agregar producto: {e}")

def probar_errores_agregar():
    print("\n=== PRUEBA ERRORES AL AGREGAR ===")
    inventario = Inventario()
    
    try:
        p1 = Producto("P001", "Producto Normal", 100, 10)
        inventario.agregar_producto(p1)
        
        p2 = Producto("P001", "Producto Duplicado", 200, 5)
        inventario.agregar_producto(p2)
    except ValueError as e:
        print(f"Error esperado: {e}")
    
    try:
        p3 = Producto("P003", "Precio Negativo", -50, 10)
        inventario.agregar_producto(p3)
    except ValueError as e:
        print(f"Error esperado: {e}")
    
    try:
        p4 = Producto("P004", "Stock Negativo", 50, -5)
        inventario.agregar_producto(p4)
    except ValueError as e:
        print(f"Error esperado: {e}")

def probar_buscar_producto():
    print("\n=== PRUEBA BUSCAR PRODUCTO ===")
    inventario = Inventario()
    
    p1 = Producto("P001", "Monitor", 300, 15)
    inventario.agregar_producto(p1)
    
    try:
        producto = inventario.buscar_producto("P001")
        print(f"Producto encontrado: {producto}")
    except ProductoNoEncontradoException as e:
        print(f"Error: {e}")
    
    try:
        inventario.buscar_producto("P999")
    except ProductoNoEncontradoException as e:
        print(f"Error esperado: {e}")

def probar_ventas():
    print("\n=== PRUEBA VENTAS ===")
    inventario = Inventario()
    
    p1 = Producto("P001", "Tablet", 250, 5)
    inventario.agregar_producto(p1)
    
    try:
        inventario.vender_producto("P001", 3)
        inventario.mostrar_inventario()
        
        inventario.vender_producto("P001", 5)
    except (ProductoNoEncontradoException, StockInsuficienteException) as e:
        print(f"Error en venta: {e}")
    
    try:
        inventario.vender_producto("P999", 1)
    except ProductoNoEncontradoException as e:
        print(f"Error esperado: {e}")

def menu_interactivo():
    print("\n" + "="*50)
    print("=== SISTEMA DE INVENTARIO INTERACTIVO ===")
    print("="*50)
    
    inventario = Inventario()
    
    while True:
        print("\nOpciones:")
        print("1. Agregar producto")
        print("2. Buscar producto")
        print("3. Vender producto")
        print("4. Mostrar inventario")
        print("5. Salir")
        
        opcion = input("Seleccione una opción: ")
        
        if opcion == "1":
            try:
                codigo = input("Código: ")
                nombre = input("Nombre: ")
                precio = float(input("Precio: "))
                stock = int(input("Stock: "))
                
                producto = Producto(codigo, nombre, precio, stock)
                inventario.agregar_producto(producto)
                
            except ValueError as e:
                print(f"Error: {e}")
        
        elif opcion == "2":
            try:
                codigo = input("Código a buscar: ")
                producto = inventario.buscar_producto(codigo)
                print(f"Producto encontrado: {producto}")
            except ProductoNoEncontradoException as e:
                print(f"Error: {e}")
        
        elif opcion == "3":
            try:
                codigo = input("Código del producto: ")
                cantidad = int(input("Cantidad a vender: "))
                total = inventario.vender_producto(codigo, cantidad)
                print(f"Venta exitosa. Total: ${total}")
            except (ProductoNoEncontradoException, StockInsuficienteException, ValueError) as e:
                print(f"Error en venta: {e}")
        
        elif opcion == "4":
            inventario.mostrar_inventario()
        
        elif opcion == "5":
            print("Saliendo del sistema...")
            break
        
        else:
            print("Opción no válida")

if __name__ == "__main__":
    print("=== SISTEMA DE INVENTARIO CON MANEJO DE EXCEPCIONES ===\n")
    
    probar_agregar_productos()
    probar_errores_agregar()
    probar_buscar_producto()
    probar_ventas()
    
    menu_interactivo()
    
    print("\n=== PROGRAMA FINALIZADO ===")