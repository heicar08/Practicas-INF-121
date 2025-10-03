class Producto:
    def __init__(self, nombre, precio, stock):
        self.nombre = nombre
        self.precio = precio
        self.stock = stock
    
    def vender(self, cantidad):
        if cantidad <= self.stock:
            self.stock -= cantidad
            total_venta = cantidad * self.precio
            print(f"Venta realizada: {cantidad} {self.nombre}(s) - Total: Bs. {total_venta:.2f}")
            print(f"Stock actualizado: {self.stock} unidades")
            return total_venta
        else:
            print(f"Error: No hay suficiente stock. Stock disponible: {self.stock}")
            return 0
    
    def reabastecer(self, cantidad):
        self.stock += cantidad
        print(f"Reabastecimiento: +{cantidad} {self.nombre}(s)")
        print(f"Stock actualizado: {self.stock} unidades")
        return self.stock
    
    def mostrar_info(self):
        print(f"\n--- INFORMACIÓN DEL PRODUCTO ---")
        print(f"Nombre: {self.nombre}")
        print(f"Precio: Bs. {self.precio:.2f}")
        print(f"Stock: {self.stock} unidades")
        print(f"Valor total en inventario: Bs. {self.stock * self.precio:.2f}")


if __name__ == "__main__":
    
    producto1 = Producto("Laptop", 2500.00, 10)
    producto2 = Producto("Mouse", 50.00, 25)
    
    
    producto1.mostrar_info()
    producto2.mostrar_info()
    
   
    print("\n--- OPERACIONES DE VENTA ---")
    producto1.vender(3)
    producto2.vender(10)
    producto2.vender(20)  
    
   
    print("\n--- REABASTECIMIENTO ---")
    producto1.reabastecer(5)
    producto2.reabastecer(15)
    
   
    producto1.mostrar_info()
    producto2.mostrar_info()