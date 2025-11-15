class FondosInsuficientesException(Exception):
    def __init__(self, mensaje):
        super().__init__(mensaje)

class CuentaBancaria:
    def __init__(self, numero_cuenta, titular, saldo_inicial=0):
        self.numero_cuenta = numero_cuenta
        self.titular = titular
        self.saldo = saldo_inicial
    
    def depositar(self, monto):
        if monto <= 0:
            raise ValueError("Error: El monto a depositar debe ser positivo")
        self.saldo += monto
        print(f"Depósito exitoso: +${monto:.2f}")
    
    def retirar(self, monto):
        if monto <= 0:
            raise ValueError("Error: El monto a retirar debe ser positivo")
        
        if monto > self.saldo:
            raise FondosInsuficientesException(
                f"Fondos insuficientes. Saldo: ${self.saldo:.2f}, Retiro: ${monto:.2f}"
            )
        
        self.saldo -= monto
        print(f"Retiro exitoso: -${monto:.2f}")
    
    def mostrar_info(self):
        print(f"Cuenta: {self.numero_cuenta}")
        print(f"Titular: {self.titular}")
        print(f"Saldo: ${self.saldo:.2f}")

def probar_depositos():
    print("=== PRUEBA DE DEPÓSITOS ===")
    cuenta = CuentaBancaria("12345", "Juan Pérez", 1000)
    cuenta.mostrar_info()
    
    try:
        print("\nDepósito válido:")
        cuenta.depositar(500)
        cuenta.mostrar_info()
    except ValueError as e:
        print(f"Error: {e}")
    
    try:
        print("\nDepósito con monto negativo:")
        cuenta.depositar(-100)
    except ValueError as e:
        print(f"Error esperado: {e}")
    
    try:
        print("\nDepósito con monto cero:")
        cuenta.depositar(0)
    except ValueError as e:
        print(f"Error esperado: {e}")

def probar_retiros():
    print("\n=== PRUEBA DE RETIROS ===")
    cuenta = CuentaBancaria("12345", "Juan Pérez", 1000)
    cuenta.mostrar_info()
    
    try:
        print("\nRetiro válido:")
        cuenta.retirar(300)
        cuenta.mostrar_info()
    except (ValueError, FondosInsuficientesException) as e:
        print(f"Error: {e}")
    
    try:
        print("\nRetiro que supera el saldo:")
        cuenta.retirar(1500)
    except FondosInsuficientesException as e:
        print(f"Error esperado: {e}")
    
    try:
        print("\nRetiro con monto negativo:")
        cuenta.retirar(-50)
    except ValueError as e:
        print(f"Error esperado: {e}")

def probar_operaciones_completas():
    print("\n=== PRUEBA DE OPERACIONES COMPLETAS ===")
    cuenta = CuentaBancaria("12345", "Juan Pérez", 1000)
    
    print("Estado inicial:")
    cuenta.mostrar_info()
    
    operaciones = [
        ("depositar", 200),
        ("retirar", 150),
        ("depositar", -50),
        ("retirar", 1200),
        ("retirar", 0),
        ("depositar", 100),
        ("retirar", 800)
    ]
    
    for operacion, monto in operaciones:
        print(f"\n--- {operacion.upper()} ${monto:.2f} ---")
        try:
            if operacion == "depositar":
                cuenta.depositar(monto)
            elif operacion == "retirar":
                cuenta.retirar(monto)
            cuenta.mostrar_info()
        except (ValueError, FondosInsuficientesException) as e:
            print(f"Error: {e}")

def menu_interactivo():
    print("\n" + "="*50)
    print("=== SISTEMA BANCARIO INTERACTIVO ===")
    print("="*50)
    
    cuenta = CuentaBancaria("12345", "Juan Pérez", 1000)
    
    while True:
        print(f"\nSaldo actual: ${cuenta.saldo:.2f}")
        print("\nOpciones:")
        print("1. Depositar")
        print("2. Retirar")
        print("3. Ver información")
        print("4. Salir")
        
        opcion = input("Seleccione una opción: ")
        
        if opcion == "1":
            try:
                monto = float(input("Monto a depositar: $"))
                cuenta.depositar(monto)
            except ValueError as e:
                print(f"Error: {e}")
        
        elif opcion == "2":
            try:
                monto = float(input("Monto a retirar: $"))
                cuenta.retirar(monto)
            except (ValueError, FondosInsuficientesException) as e:
                print(f"Error: {e}")
        
        elif opcion == "3":
            cuenta.mostrar_info()
        
        elif opcion == "4":
            print("Saliendo del sistema bancario...")
            break
        
        else:
            print("Opción no válida")

if __name__ == "__main__":
    print("=== SISTEMA BANCARIO CON MANEJO DE EXCEPCIONES ===\n")
    
    probar_depositos()
    probar_retiros()
    probar_operaciones_completas()
    
    menu_interactivo()
    
    print("\n=== PROGRAMA FINALIZADO ===")
    