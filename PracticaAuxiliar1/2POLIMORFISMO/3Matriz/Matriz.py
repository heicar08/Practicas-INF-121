class Matriz:
    def __init__(self, matriz=None, filas=4, columnas=4):
        self.filas = filas
        self.columnas = columnas
        
        if matriz is None:
            self.matriz = [[1.0 if i == j else 0.0 for j in range(columnas)] for i in range(filas)]
        else:
         
            self.matriz = matriz
            self.filas = len(matriz)
            self.columnas = len(matriz[0]) if matriz else 0
    
   
    def sumar(self, otra_matriz):
        if not self._validar_dimensiones(otra_matriz):
            print(" ERROR: No se pueden sumar matrices de diferentes dimensiones")
            print(f"   Matriz 1: {self.filas}x{self.columnas}")
            print(f"   Matriz 2: {otra_matriz.filas}x{otra_matriz.columnas}")
            return None
        
        resultado = [[0.0 for _ in range(self.columnas)] for _ in range(self.filas)]
        for i in range(self.filas):
            for j in range(self.columnas):
                resultado[i][j] = self.matriz[i][j] + otra_matriz.matriz[i][j]
        return Matriz(resultado, self.filas, self.columnas)
    
    # c) Método para restar matrices
    def restar(self, otra_matriz):
        if not self._validar_dimensiones(otra_matriz):
            print(" ERROR: No se pueden restar matrices de diferentes dimensiones")
            print(f"   Matriz 1: {self.filas}x{self.columnas}")
            print(f"   Matriz 2: {otra_matriz.filas}x{otra_matriz.columnas}")
            return None
        
        resultado = [[0.0 for _ in range(self.columnas)] for _ in range(self.filas)]
        for i in range(self.filas):
            for j in range(self.columnas):
                resultado[i][j] = self.matriz[i][j] - otra_matriz.matriz[i][j]
        return Matriz(resultado, self.filas, self.columnas)
    
    # d) Método para verificar si dos matrices son iguales
    def igual(self, otra_matriz):
        if not self._validar_dimensiones(otra_matriz):
            return False
        
        for i in range(self.filas):
            for j in range(self.columnas):
                if self.matriz[i][j] != otra_matriz.matriz[i][j]:
                    return False
        return True
    
    def _validar_dimensiones(self, otra_matriz):
        return self.filas == otra_matriz.filas and self.columnas == otra_matriz.columnas
    
    def mostrar(self):
        print(f"Matriz {self.filas}x{self.columnas}:")
        for fila in self.matriz:
            print([f"{x:.1f}" for x in fila])
        print()


if __name__ == "__main__":
    print("=== EJERCICIO 3 - MATRICES CON DIFERENTES TAMAÑOS ===\n")
    
   
    print("1. Matriz 4x4:")
    matriz_4x4 = Matriz()
    matriz_4x4.mostrar()
    
   
    print("2. Matriz 2x4:")
    matriz_2x4 = [
        [1.0, 2.0, 3.0, 4.0],
        [5.0, 6.0, 7.0, 8.0]
    ]
    matriz2 = Matriz(matriz_2x4, 2, 4)
    matriz2.mostrar()
    
   
    print("3. Matriz 3x3:")
    matriz_3x3 = [
        [1.0, 2.0, 3.0],
        [4.0, 5.0, 6.0],
        [7.0, 8.0, 9.0]
    ]
    matriz3 = Matriz(matriz_3x3, 3, 3)
    matriz3.mostrar()
    
  
    print("4. Intentando sumar matrices 4x4 y 2x4:")
    resultado_suma = matriz_4x4.sumar(matriz2)
    
    print("5. Intentando sumar matrices 4x4 y 3x3:")
    resultado_suma2 = matriz_4x4.sumar(matriz3)
    

    print("6. Sumando matrices 3x3:")
    matriz3_2 = Matriz(matriz_3x3, 3, 3)
    suma_correcta = matriz3.sumar(matriz3_2)
    if suma_correcta:
        suma_correcta.mostrar()