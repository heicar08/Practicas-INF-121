class Producto:
    def __init__(self, nombre, precio, categoria):
        self.nombre = nombre
        self.precio = precio
        self.categoria = categoria
        self.disponible = True
    
    def __del__(self):
        print(f"Producto {self.nombre} eliminado del menú")
    
    def get_nombre(self):
        return self.nombre
    
    def get_precio(self):
        return self.precio
    
    def get_categoria(self):
        return self.categoria
    
    def get_disponible(self):
        return self.disponible
    
    def set_nombre(self, nuevo_nombre):
        self.nombre = nuevo_nombre
    
    def set_precio(self, nuevo_precio):
        if nuevo_precio >= 0:
            self.precio = nuevo_precio
        else:
            print("Error: El precio no puede ser negativo")
    
    def set_categoria(self, nueva_categoria):
        self.categoria = nueva_categoria
    
    def set_disponible(self, disponibilidad):
        self.disponible = disponibilidad
    
    def mostrar_info(self):
        estado = " Disponible" if self.disponible else "No disponible"
        print(f"Producto: {self.nombre} | Precio: Bs. {self.precio:.2f} | Categoría: {self.categoria} | {estado}")

class Pedido:
    def __init__(self, id_pedido, cliente, productos):
        self.id_pedido = id_pedido
        self.cliente = cliente
        self.productos = productos
        self.estado = "Registrado"
        self.total = self.calcular_total()
    
    def __del__(self):
        print(f"Pedido {self.id_pedido} completado y archivado")
    
    def calcular_total(self):
        return sum(producto.get_precio() for producto in self.productos)
    
    def get_id_pedido(self):
        return self.id_pedido
    
    def get_cliente(self):
        return self.cliente
    
    def get_estado(self):
        return self.estado
    
    def get_total(self):
        return self.total
    
    def set_cliente(self, nuevo_cliente):
        self.cliente = nuevo_cliente
    
    def set_estado(self, nuevo_estado):
        estados_validos = ["Registrado", "Preparado", "Entregado"]
        if nuevo_estado in estados_validos:
            self.estado = nuevo_estado
        else:
            print("Error: Estado no válido")
    
    def agregar_producto(self, producto):
        if producto.get_disponible():
            self.productos.append(producto)
            self.total = self.calcular_total()
            print(f"Producto {producto.get_nombre()} agregado al pedido")
        else:
            print(f"Producto {producto.get_nombre()} no disponible")
    
    def mostrar_detalle(self):
        print(f"\n--- DETALLE DEL PEDIDO {self.id_pedido} ---")
        print(f"Cliente: {self.cliente}")
        print(f"Estado: {self.estado}")
        print(f"Total: Bs. {self.total:.2f}")
        print("Productos:")
        for i, producto in enumerate(self.productos, 1):
            print(f"  {i}. {producto.get_nombre()} - Bs. {producto.get_precio():.2f}")

if __name__ == "__main__":
    print("=== SISTEMA DE CAFETERÍA ===\n")
    
    cafe = Producto("Café Americano", 12.50, "Bebida Caliente")
    pastel = Producto("Pastel de Chocolate", 25.00, "Postre")
    sandwich = Producto("Sandwich Club", 30.00, "Comida")
    
    cafe.mostrar_info()
    pastel.mostrar_info()
    sandwich.mostrar_info()
    
    pedido1 = Pedido("PED-001", "Juan Pérez", [cafe, pastel])
    pedido1.mostrar_detalle()
    
    pedido1.set_estado("Preparado")
    pedido1.mostrar_detalle()
    
    pedido1.agregar_producto(sandwich)
    pedido1.mostrar_detalle()
    
    pedido1.set_estado("Entregado")
    pedido1.mostrar_detalle()
    
    print(f"Nombre del café: {cafe.get_nombre()}")
    cafe.set_precio(15.00)
    print(f"Nuevo precio del café: Bs. {cafe.get_precio():.2f}")
    
    print(f"Cliente del pedido: {pedido1.get_cliente()}")
    pedido1.set_cliente("María González")
    print(f"Nuevo cliente: {pedido1.get_cliente()}")
    
    cafe.mostrar_info()
    pedido1.mostrar_detalle()
    
    print("FIN DEL PROGRAMA")