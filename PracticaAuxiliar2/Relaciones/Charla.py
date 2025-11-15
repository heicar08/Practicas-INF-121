class Persona:
    def __init__(self, nombre, apellido, edad, ci):
        self.nombre = nombre
        self.apellido = apellido
        self.edad = edad
        self.ci = ci

class Speaker(Persona):
    def __init__(self, nombre, apellido, edad, ci, especialidad):
        super().__init__(nombre, apellido, edad, ci)
        self.especialidad = especialidad

class Participante(Persona):
    def __init__(self, nombre, apellido, edad, ci, nroTicket):
        super().__init__(nombre, apellido, edad, ci)
        self.nroTicket = nroTicket

class Charla:
    def __init__(self, lugar, nombreCharla, speaker):
        self.lugar = lugar
        self.nombreCharla = nombreCharla
        self.speaker = speaker
        self.np = 0
        self.participantes = [None] * 50
    
    def agregar_participante(self, participante):
        if self.np < 50:
            self.participantes[self.np] = participante
            self.np += 1
            return True
        return False
    
    def obtener_participantes(self):
        return [p for p in self.participantes if p is not None]

class Evento:
    def __init__(self, nombre):
        self.nombre = nombre
        self.nc = 0
        self.charlas = [None] * 50
    
    def agregar_charla(self, charla):
        if self.nc < 50:
            self.charlas[self.nc] = charla
            self.nc += 1
            return True
        return False
    
    def obtener_charlas(self):
        return [c for c in self.charlas if c is not None]
    
    def edad_promedio_participantes(self):
        print("\n=== EDAD PROMEDIO DE PARTICIPANTES ===")
        total_edad = 0
        total_participantes = 0
        participantes_unicos = set()
        
        for charla in self.obtener_charlas():
            for participante in charla.obtener_participantes():
                if participante.ci not in participantes_unicos:
                    participantes_unicos.add(participante.ci)
                    total_edad += participante.edad
                    total_participantes += 1
        
        if total_participantes > 0:
            promedio = total_edad / total_participantes
            print(f"Edad promedio: {promedio:.2f} años")
            print(f"Total participantes únicos: {total_participantes}")
            return promedio
        else:
            print("No hay participantes en el evento")
            return 0
    
    def buscar_persona(self, nombre, apellido):
        print(f"\n=== BUSCANDO PERSONA: {nombre} {apellido} ===")
        encontrado = False
        
        for charla in self.obtener_charlas():
            if charla.speaker and charla.speaker.nombre == nombre and charla.speaker.apellido == apellido:
                print(f"Encontrado como SPEAKER en: '{charla.nombreCharla}'")
                encontrado = True
            
            for participante in charla.obtener_participantes():
                if participante.nombre == nombre and participante.apellido == apellido:
                    print(f"Encontrado como PARTICIPANTE en: '{charla.nombreCharla}'")
                    encontrado = True
        
        if not encontrado:
            print("Persona no encontrada en el evento")
        
        return encontrado
    
    def eliminar_charlas_por_speaker_ci(self, ci):
        print(f"\n=== ELIMINANDO CHARLAS DEL SPEAKER CON CI: {ci} ===")
        charlas_eliminadas = 0
        nuevas_charlas = []
        
        for charla in self.obtener_charlas():
            if charla.speaker and charla.speaker.ci == ci:
                print(f"Eliminada charla: '{charla.nombreCharla}'")
                charlas_eliminadas += 1
            else:
                nuevas_charlas.append(charla)
        
        self.charlas = nuevas_charlas + [None] * (50 - len(nuevas_charlas))
        self.nc = len(nuevas_charlas)
        print(f"Total charlas eliminadas: {charlas_eliminadas}")
        return charlas_eliminadas
    
    def ordenar_charlas_por_participantes(self):
        print("\n=== CHARLAS ORDENADAS POR NÚMERO DE PARTICIPANTES ===")
        charlas_ordenadas = sorted(self.obtener_charlas(), 
                                 key=lambda charla: charla.np, 
                                 reverse=True)
        
        for i, charla in enumerate(charlas_ordenadas, 1):
            print(f"{i}. '{charla.nombreCharla}' - {charla.np} participantes - Speaker: {charla.speaker.nombre}")
        
        return charlas_ordenadas

evento = Evento("Conferencia de Tecnología 2024")

speaker1 = Speaker("Ana", "García", 35, "1234567", "Inteligencia Artificial")
speaker2 = Speaker("Carlos", "López", 40, "7654321", "Blockchain")
speaker3 = Speaker("María", "Rodríguez", 32, "1111111", "Ciberseguridad")

participante1 = Participante("Juan", "Pérez", 25, "2222222", 1001)
participante2 = Participante("Laura", "Martínez", 28, "3333333", 1002)
participante3 = Participante("Pedro", "Sánchez", 30, "4444444", 1003)
participante4 = Participante("Sofia", "Díaz", 22, "5555555", 1004)
participante5 = Participante("Miguel", "Torres", 26, "6666666", 1005)
participante6 = Participante("Elena", "Castro", 29, "7777777", 1006)

charla1 = Charla("Auditorio A", "Introducción a IA", speaker1)
charla2 = Charla("Auditorio B", "Blockchain en Finanzas", speaker2)
charla3 = Charla("Sala C", "Seguridad Informática", speaker3)
charla4 = Charla("Auditorio A", "IA Avanzada", speaker1)

charla1.agregar_participante(participante1)
charla1.agregar_participante(participante2)
charla1.agregar_participante(participante3)

charla2.agregar_participante(participante2)
charla2.agregar_participante(participante4)

charla3.agregar_participante(participante1)
charla3.agregar_participante(participante5)
charla3.agregar_participante(participante6)

charla4.agregar_participante(participante3)
charla4.agregar_participante(participante4)
charla4.agregar_participante(participante5)
charla4.agregar_participante(participante6)

evento.agregar_charla(charla1)
evento.agregar_charla(charla2)
evento.agregar_charla(charla3)
evento.agregar_charla(charla4)

print("=== SISTEMA DE EVENTOS Y CHARLAS ===")

evento.edad_promedio_participantes()

evento.buscar_persona("Ana", "García")
evento.buscar_persona("Juan", "Pérez")
evento.buscar_persona("Luis", "Fernández")

evento.ordenar_charlas_por_participantes()

evento.eliminar_charlas_por_speaker_ci("1234567")

print("\n=== CHARLAS DESPUÉS DE ELIMINACIÓN ===")
for charla in evento.obtener_charlas():
    print(f"'{charla.nombreCharla}' - {charla.np} participantes")

evento.ordenar_charlas_por_participantes()