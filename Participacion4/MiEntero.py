import math
class MiEntero:
    def __init__(self, valor):
        self.valor = valor
    
    def get_valor(self):
        return self.valor

    def es_par(self):
        return self.valor % 2 == 0
    
    def es_impar(self):
        return self.valor % 2 != 0
    
    def es_primo(self):
        if self.valor <= 1:
            return False
        if self.valor == 2:
            return True
        if self.valor % 2 == 0:
            return False
        
        for i in range(3, int(math.sqrt(self.valor)) + 1, 2):
            if self.valor % i == 0:
                return False
        return True
    
    def equals_int(self, otro_valor):
        return self.valor == otro_valor
    
    def equals_mientero(self, otro):
        return self.valor == otro.valor

    def es_par_static(valor):
        return valor % 2 == 0

    def es_impar_static(valor):
        return valor % 2 != 0
    
    def es_primo_static(valor):
        if valor <= 1:
            return False
        if valor == 2:
            return True
        if valor % 2 == 0:
            return False
        
        for i in range(3, int(math.sqrt(valor)) + 1, 2):
            if valor % i == 0:
                return False
        return True
    
    
    def es_par_mientero(entero):
        return entero.valor % 2 == 0
    
    def es_impar_mientero(entero):
        return entero.valor % 2 != 0
    
    def es_primo_mientero(entero):
        return MiEntero.es_primo_static(entero.valor)
    
   
    def parse_int_chars(chars):
        return int(''.join(chars))
    
    
    def parse_int_str(string):
        return int(string)

if __name__ == "__main__":
  
    num1 = MiEntero(7)
    num2 = MiEntero(12)
    num3 = MiEntero(29)
    
    print("=== MÉTODOS DE INSTANCIA ===")
    print(f"num1 ({num1.get_valor()}):")
    print(f"  Es par: {num1.es_par()}")
    print(f"  Es impar: {num1.es_impar()}")
    print(f"  Es primo: {num1.es_primo()}")
    
    print(f"\nnum2 ({num2.get_valor()}):")
    print(f"  Es par: {num2.es_par()}")
    print(f"  Es impar: {num2.es_impar()}")
    print(f"  Es primo: {num2.es_primo()}")
    
    print(f"\nnum3 ({num3.get_valor()}):")
    print(f"  Es par: {num3.es_par()}")
    print(f"  Es impar: {num3.es_impar()}")
    print(f"  Es primo: {num3.es_primo()}")
    
    print("\n=== MÉTODOS EQUALS ===")
    print(f"num1 equals 7: {num1.equals_int(7)}")
    print(f"num1 equals num2: {num1.equals_mientero(num2)}")
    print(f"num1 equals num3: {num1.equals_mientero(num3)}")
    
    print("\n=== MÉTODOS ESTÁTICOS (int) ===")
    print(f"es_par_static(15): {MiEntero.es_par_static(15)}")
    print(f"es_impar_static(15): {MiEntero.es_impar_static(15)}")
    print(f"es_primo_static(15): {MiEntero.es_primo_static(15)}")
    
    print("\n=== MÉTODOS ESTÁTICOS (MiEntero) ===")
    print(f"es_par_mientero(num2): {MiEntero.es_par_mientero(num2)}")
    print(f"es_impar_mientero(num2): {MiEntero.es_impar_mientero(num2)}")
    print(f"es_primo_mientero(num2): {MiEntero.es_primo_mientero(num2)}")
    
  
    print("\n=== MÉTODOS DE CONVERSIÓN ===")
    chars = ['1', '2', '3']
    string = "456"
    
    print(f"parse_int_chars(['1','2','3']): {MiEntero.parse_int_chars(chars)}")
    print(f"parse_int_str('456'): {MiEntero.parse_int_str(string)}")