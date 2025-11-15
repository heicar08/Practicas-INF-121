class NumeroInvalidoException(Exception):
    def __init__(self, mensaje):
        super().__init__(mensaje)

class Calculadora:
    @staticmethod
    def sumar(a, b):
        return a + b
    
    @staticmethod
    def restar(a, b):
        return a - b
    
    @staticmethod
    def multiplicar(a, b):
        return a * b
    
    @staticmethod
    def dividir(dividendo, divisor):
        if divisor == 0:
            raise ArithmeticError("ERROR: No se puede dividir por cero")
        return dividendo / divisor
    
    @staticmethod
    def convertir_a_entero(numero_str):
        try:
            return int(numero_str)
        except ValueError:
            raise NumeroInvalidoException(f"ERROR: '{numero_str}' no es un número entero válido")
    
    @staticmethod
    def convertir_a_float(numero_str):
        try:
            return float(numero_str)
        except ValueError:
            raise NumeroInvalidoException(f"ERROR: '{numero_str}' no es un número válido")

def probar_operaciones_basicas():
    print("=== PRUEBA DE OPERACIONES BÁSICAS ===")
    a, b = 10, 5
    print(f"{a} + {b} = {Calculadora.sumar(a, b)}")
    print(f"{a} - {b} = {Calculadora.restar(a, b)}")
    print(f"{a} * {b} = {Calculadora.multiplicar(a, b)}")
    try:
        print(f"{a} / {b} = {Calculadora.dividir(a, b)}")
    except ArithmeticError as e:
        print(e)

def probar_division_por_cero():
    print("\n=== PRUEBA DE DIVISIÓN POR CERO ===")
    try:
        resultado = Calculadora.dividir(10, 0)
        print(f"Resultado: {resultado}")
    except ArithmeticError as e:
        print(f"Excepción capturada: {e}")

def probar_conversion_numeros():
    print("\n=== PRUEBA DE CONVERSIÓN DE NÚMEROS ===")
    numeros_prueba = ["123", "45.67", "abc", "-100", "12a3", "3.14"]
    for numero_str in numeros_prueba:
        try:
            numero_entero = Calculadora.convertir_a_entero(numero_str)
            print(f"'{numero_str}' convertido a entero: {numero_entero}")
        except NumeroInvalidoException as e:
            print(f"No se pudo convertir a entero: {e}")
            try:
                numero_float = Calculadora.convertir_a_float(numero_str)
                print(f"'{numero_str}' convertido a float: {numero_float}")
            except NumeroInvalidoException as e2:
                print(f"Tampoco se pudo convertir a float: {e2}")

def probar_calculadora_completa():
    print("\n=== PRUEBA COMPLETA DE CALCULADORA ===")
    operandos1 = ["15", "8", "abc", "20", "10.5"]
    operandos2 = ["3", "0", "4", "def", "2"]
    for i in range(len(operandos1)):
        print(f"\n--- Operación {i + 1} ---")
        print(f"Operando 1: {operandos1[i]}, Operando 2: {operandos2[i]}")
        try:
            try:
                num1 = Calculadora.convertir_a_entero(operandos1[i])
            except NumeroInvalidoException:
                num1 = Calculadora.convertir_a_float(operandos1[i])
            try:
                num2 = Calculadora.convertir_a_entero(operandos2[i])
            except NumeroInvalidoException:
                num2 = Calculadora.convertir_a_float(operandos2[i])
            print(f"Suma: {Calculadora.sumar(num1, num2)}")
            print(f"Resta: {Calculadora.restar(num1, num2)}")
            print(f"Multiplicación: {Calculadora.multiplicar(num1, num2)}")
            try:
                print(f"División: {Calculadora.dividir(num1, num2)}")
            except ArithmeticError as e:
                print(f"Error en división: {e}")
        except NumeroInvalidoException as e:
            print(f"Error en conversión: {e}")

def menu_interactivo():
    print("\n" + "="*50)
    print("=== CALCULADORA INTERACTIVA ===")
    print("="*50)
    while True:
        print("\nOpciones:")
        print("1. Realizar operación básica")
        print("2. Probar casos de error")
        print("3. Salir")
        opcion = input("\nSeleccione una opción: ")
        if opcion == "1":
            try:
                num1_str = input("Ingrese el primer número: ")
                num2_str = input("Ingrese el segundo número: ")
                try:
                    num1 = Calculadora.convertir_a_entero(num1_str)
                except NumeroInvalidoException:
                    num1 = Calculadora.convertir_a_float(num1_str)
                try:
                    num2 = Calculadora.convertir_a_entero(num2_str)
                except NumeroInvalidoException:
                    num2 = Calculadora.convertir_a_float(num2_str)
                print(f"\nResultados con {num1} y {num2}:")
                print(f"Suma: {Calculadora.sumar(num1, num2)}")
                print(f"Resta: {Calculadora.restar(num1, num2)}")
                print(f"Multiplicación: {Calculadora.multiplicar(num1, num2)}")
                try:
                    print(f"División: {Calculadora.dividir(num1, num2)}")
                except ArithmeticError as e:
                    print(f"División: {e}")
            except NumeroInvalidoException as e:
                print(f"Error: {e}")
        elif opcion == "2":
            print("\nEjecutando pruebas automáticas de errores...")
            probar_division_por_cero()
            probar_conversion_numeros()
        elif opcion == "3":
            print("¡Hasta luego!")
            break
        else:
            print("Opción no válida. Intente nuevamente.")

if __name__ == "__main__":
    print("=== CALCULADORA CON MANEJO DE EXCEPCIONES ===\n")
    probar_operaciones_basicas()
    probar_division_por_cero()
    probar_conversion_numeros()
    probar_calculadora_completa()
    menu_interactivo()
    print("\n=== PROGRAMA FINALIZADO ===")