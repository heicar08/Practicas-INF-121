class Carta:
    def __init__(self, codigo, descripcion, remitente, destinatario):
        self.codigo = codigo
        self.descripcion = descripcion
        self.remitente = remitente
        self.destinatario = destinatario

class Buzon:
    def __init__(self, nro):
        self.nro = nro
        self.nroC = 0
        self.c = [["", "", ""] for _ in range(50)]
        self.descripciones = {}
    
    def agregar_carta(self, carta):
        if self.nroC < 50:
            self.c[self.nroC] = [carta.codigo, carta.remitente, carta.destinatario]
            self.descripciones[carta.codigo] = carta.descripcion
            self.nroC += 1
            return True
        return False
    
    def eliminar_carta(self, codigo):
        for i in range(self.nroC):
            if self.c[i][0] == codigo:
                for j in range(i, self.nroC - 1):
                    self.c[j] = self.c[j + 1]
                self.c[self.nroC - 1] = ["", "", ""]
                if codigo in self.descripciones:
                    del self.descripciones[codigo]
                self.nroC -= 1
                print(f"Carta {codigo} eliminada")
                return True
        print(f"Carta {codigo} no encontrada")
        return False
    
    def cartas_recibidas(self, destinatario):
        count = 0
        for i in range(self.nroC):
            if self.c[i][2] == destinatario:
                count += 1
        return count
    
    def remitente_recibio_carta(self, remitente):
        for i in range(self.nroC):
            if self.c[i][1] == remitente:
                for j in range(self.nroC):
                    if self.c[j][2] == remitente:
                        print(f"✅ {remitente} recibió carta de {self.c[j][1]}")
                        return True
        print(f"❌ {remitente} no recibió cartas")
        return False
    
    def buscar_palabra_clave(self, palabra):
        coincidencias = []
        for i in range(self.nroC):
            codigo = self.c[i][0]
            if codigo in self.descripciones and palabra.lower() in self.descripciones[codigo].lower():
                coincidencias.append(self.c[i])
        return coincidencias
    
    def mostrar_cartas(self):
        print(f"\n--- BUZÓN {self.nro} ({self.nroC} cartas) ---")
        for i in range(self.nroC):
            print(f"{i+1}. Código: {self.c[i][0]} | De: {self.c[i][1]} | Para: {self.c[i][2]}")

def instanciar_buzones():
    buzon1 = Buzon("001")
    buzon2 = Buzon("002") 
    buzon3 = Buzon("003")
    
    # a) Instanciar 3 buzones diferentes, cada uno con al menos 3 cartas
    carta1 = Carta("C123", "Querido Juan, te escribo para decirte que el amor verdadero existe...", "Juan Álvarez", "Peter Chaves")
    carta2 = Carta("C456", "Querido Pepe, te escribo para decirte que ella no te ama por lo tanto... contrata la carta.", "Pepe Mujica", "Wilmer Pérez")
    carta3 = Carta("C789", "Hola Paty, espero que estés bien y que encuentres el amor que mereces...", "Paty Vasques", "Pepe Mujica")
    carta4 = Carta("C101", "Estimado colega, me complace informarle sobre nuestro nuevo proyecto de amor...", "Carlos López", "Ana García")
    carta5 = Carta("C102", "Querida amiga, te extraño mucho y recuerdo con amor nuestros momentos...", "Maria Ruiz", "Pedro Martínez")
    carta6 = Carta("C103", "Hola amor, te escribo para decirte cuánto te amo y lo importante que eres...", "Laura Torres", "Carlos Sánchez")
    carta7 = Carta("C104", "Apreciado señor, le informamos sobre su cuenta...", "Banco Nacional", "Wilmer Pérez")
    carta8 = Carta("C105", "Querido hermano, la familia te manda mucho amor...", "Ana García", "Carlos López")
    carta9 = Carta("C106", "Hola mi amor, no puedo esperar para verte...", "Pedro Martínez", "Maria Ruiz")
    
    # Agregar cartas a los buzones
    buzon1.agregar_carta(carta1)
    buzon1.agregar_carta(carta2)
    buzon1.agregar_carta(carta3)
    
    buzon2.agregar_carta(carta4)
    buzon2.agregar_carta(carta5)
    buzon2.agregar_carta(carta6)
    
    buzon3.agregar_carta(carta7)
    buzon3.agregar_carta(carta8)
    buzon3.agregar_carta(carta9)
    
    return buzon1, buzon2, buzon3

if __name__ == "__main__":
    print("=== SISTEMA DE BUZONES ===\n")
    
    # a) Instanciar 3 buzones diferentes
    buzon1, buzon2, buzon3 = instanciar_buzones()
    buzones = [buzon1, buzon2, buzon3]
    
    print("--- a) Buzones instanciados con cartas ---")
    for buzon in buzones:
        buzon.mostrar_cartas()
    
    # c) Verificar cuántas cartas recibió el destinatario "X"
    print("\n--- c) Cartas recibidas por 'Pepe Mujica' ---")
    for buzon in buzones:
        count = buzon.cartas_recibidas("Pepe Mujica")
        print(f"Buzón {buzon.nro}: {count} cartas")
    
    print("\n--- c) Cartas recibidas por 'Wilmer Pérez' ---")
    for buzon in buzones:
        count = buzon.cartas_recibidas("Wilmer Pérez")
        print(f"Buzón {buzon.nro}: {count} cartas")
    
    # d) Eliminar la carta de la matriz cuyo código sea "X"
    print("\n--- d) Eliminar carta 'C456' ---")
    buzon1.eliminar_carta("C456")
    buzon1.mostrar_cartas()
    
    # e) Verificar si algún remitente ha recibido alguna carta
    print("\n--- e) Verificar si remitente recibió carta ---")
    buzon1.remitente_recibio_carta("Pepe Mujica")
    buzon1.remitente_recibio_carta("Juan Álvarez")
    buzon2.remitente_recibio_carta("Carlos López")
    
    # f) Buscar palabra clave "amor" en las descripciones
    print("\n--- f) Buscar palabra clave 'amor' ---")
    for buzon in buzones:
        coincidencias = buzon.buscar_palabra_clave("amor")
        if coincidencias:
            print(f"\nCoincidencias en Buzón {buzon.nro}:")
            for carta in coincidencias:
                print(f"Código: {carta[0]} | De: {carta[1]} | Para: {carta[2]}")
        else:
            print(f"\nBuzón {buzon.nro}: No hay coincidencias con 'amor'")
    
    # g) Mostrar código, remitente y destinatario por cada coincidencia
    print("\n--- g) Información detallada de coincidencias ---")
    palabra = "amor"
    for buzon in buzones:
        coincidencias = buzon.buscar_palabra_clave(palabra)
        for carta in coincidencias:
            desc = buzon.descripciones.get(carta[0], "")
            print(f"\nPalabra '{palabra}' encontrada en:")
            print(f"  Código: {carta[0]}")
            print(f"  Remitente: {carta[1]}")
            print(f"  Destinatario: {carta[2]}")
            print(f"  Descripción: {desc}")