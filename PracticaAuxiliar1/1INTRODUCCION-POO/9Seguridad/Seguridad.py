class CamaraSeguridad:
    def __init__(self, id_camara, ubicacion, tipo, estado):
        self.id_camara = id_camara
        self.ubicacion = ubicacion
        self.tipo = tipo  
        self.estado = estado  
    
    def activar_camara(self):
        self.estado = "Activa"
        print(f"Cámara {self.id_camara} ACTIVADA en {self.ubicacion}")
    
    def detectar_movimiento(self, zona):
        if self.estado == "Activa":
            print(f" Cámara {self.id_camara} detectó movimiento en {zona}")
            return True
        return False
    
    def mostrar_info(self):
        print(f"Cámara {self.id_camara} | Ubicación: {self.ubicacion} | Tipo: {self.tipo} | Estado: {self.estado}")

class OficialEncubierto:
    def __init__(self, nombre, identificacion, especialidad, ubicacion):
        self.nombre = nombre
        self.identificacion = identificacion
        self.especialidad = especialidad
        self.ubicacion = ubicacion
        self.disponible = True
    
    def reportar_sospechoso(self, descripcion):
        if self.disponible:
            print(f" Oficial {self.nombre} reporta sospechoso: {descripcion}")
            return True
        return False
    
    def cambiar_ubicacion(self, nueva_ubicacion):
        self.ubicacion = nueva_ubicacion
        print(f" Oficial {self.nombre} se movió a {nueva_ubicacion}")
    
    def mostrar_info(self):
        estado = "Disponible" if self.disponible else "Ocupado"
        print(f"Oficial {self.nombre} | ID: {self.identificacion} | Especialidad: {self.especialidad} | Ubicación: {self.ubicacion} | {estado}")

class SistemaRadio:
    def __init__(self, id_radio, canal, rango):
        self.id_radio = id_radio
        self.canal = canal
        self.rango = rango
        self.encendido = False
    
    def encender_radio(self):
        self.encendido = True
        print(f" Radio {self.id_radio} ENCENDIDA - Canal: {self.canal}")
    
    def transmitir_mensaje(self, mensaje, destino):
        if self.encendido:
            print(f" Transmitiendo a {destino}: '{mensaje}'")
            return True
        print(" Radio apagada, no se puede transmitir")
        return False
    
    def mostrar_info(self):
        estado = "Encendido" if self.encendido else "Apagado"
        print(f"Radio {self.id_radio} | Canal: {self.canal} | Rango: {self.rango}km | Estado: {estado}")

class CocineroPolicial:
    def __init__(self, nombre, años_experiencia, especialidad_culinaria):
        self.nombre = nombre
        self.años_experiencia = años_experiencia
        self.especialidad_culinaria = especialidad_culinaria
        self.alertado = False
    
    def preparar_comida(self, pedido):
        print(f" Cocinero {self.nombre} preparando: {pedido}")
        return f"Pedido listo: {pedido}"
    
    def activar_protocolo_emergencia(self):
        self.alertado = True
        print(f" Cocinero {self.nombre} ACTIVÓ PROTOCOLO DE EMERGENCIA")
        return "Protocolo activado - Refuerzos solicitados"
    
    def mostrar_info(self):
        alerta = "EN ALERTA" if self.alertado else "Normal"
        print(f"Cocinero {self.nombre} | Experiencia: {self.años_experiencia}años | Especialidad: {self.especialidad_culinaria} | Estado: {alerta}")


if __name__ == "__main__":
    print("=== PUESTO DE SALCHIPAPAS ENCUBIERTO - SISTEMA ANTI-DELINCUENCIA ===\n")
    
    
    camara1 = CamaraSeguridad("CAM-001", "Entrada principal", "Fija", "Activa")
    camara2 = CamaraSeguridad("CAM-002", "Área de mesas", "Movil", "Inactiva")
    
    oficial1 = OficialEncubierto("Carlos Ramirez", "OF-789", "Vigilancia cercana", "Interior puesto")
    oficial2 = OficialEncubierto("Ana Torres", "OF-456", "Intervención rápida", "Exterior puesto")
    
    radio1 = SistemaRadio("RAD-001", "Canal Seguro 7", 5)
    radio2 = SistemaRadio("RAD-002", "Canal Emergencia 1", 10)
    
    cocinero = CocineroPolicial("Jorge Mendoza", 8, "Salchipapas especiales")
    
    
    print("--- INFORMACIÓN DEL SISTEMA ---")
    camara1.mostrar_info()
    camara2.mostrar_info()
    oficial1.mostrar_info()
    oficial2.mostrar_info()
    radio1.mostrar_info()
    radio2.mostrar_info()
    cocinero.mostrar_info()
    
    print("\n--- SIMULACIÓN DE OPERACIÓN ---")
    
    camara2.activar_camara()
    radio1.encender_radio()
    radio2.encender_radio()
    
    print("\n--- DETECCIÓN DE ACTIVIDAD ---")
    camara1.detectar_movimiento("Entrada lateral")
    oficial1.reportar_sospechoso("Sujeto con comportamiento sospechoso cerca del puesto")
    
    print("\n--- COORDINACIÓN POR RADIO ---")
    radio1.transmitir_mensaje("Sospechoso detectado en entrada lateral", "Todos los oficiales")
    oficial2.cambiar_ubicacion("Entrada lateral")
    
    
    print("\n--- PROTOCOLO DE EMERGENCIA ---")
    cocinero.preparar_comida("Salchipapa especial")
    cocinero.activar_protocolo_emergencia()
    radio2.transmitir_mensaje("Solicito refuerzos inmediatos", "Central")
    
    print("\n--- ESTADO FINAL DEL SISTEMA ---")
    camara1.mostrar_info()
    oficial2.mostrar_info()
    radio2.mostrar_info()
    cocinero.mostrar_info()