class MP4:
    def __init__(self, marca, capacidad_gb):
        self.marca = marca
        self.capacidad_gb = capacidad_gb
        self.nro_canciones = 0
        self.nro_videos = 0
        self.canciones = [["" for _ in range(100)] for _ in range(3)]
        self.videos = [["" for _ in range(3)] for _ in range(100)]
    
    def borrar(self, nombre=None, artista=None, peso=None):
        if nombre and not artista and not peso:
            for i in range(self.nro_canciones):
                if self.canciones[0][i] == nombre:
                    self._eliminar_cancion(i)
                    print(f"Canción '{nombre}' eliminada")
                    return
            print(f"Canción '{nombre}' no encontrada")
        
        elif nombre and artista and not peso:
            for i in range(self.nro_canciones):
                if self.canciones[0][i] == nombre and self.canciones[1][i] == artista:
                    self._eliminar_cancion(i)
                    print(f"Canción '{nombre}' de {artista} eliminada")
                    return
            print(f"Canción '{nombre}' de {artista} no encontrada")
        
        elif nombre and artista and peso:
            for i in range(self.nro_canciones):
                if (self.canciones[0][i] == nombre and 
                    self.canciones[1][i] == artista and 
                    self.canciones[2][i] == str(peso)):
                    self._eliminar_cancion(i)
                    print(f"Canción '{nombre}' de {artista} ({peso}KB) eliminada")
                    return
            print("Canción no encontrada con los criterios especificados")
    
    def _eliminar_cancion(self, index):
        for j in range(index, self.nro_canciones - 1):
            self.canciones[0][j] = self.canciones[0][j + 1]
            self.canciones[1][j] = self.canciones[1][j + 1]
            self.canciones[2][j] = self.canciones[2][j + 1]
        self.nro_canciones -= 1
    
    def __add__(self, cancion_info):
        nombre, artista, peso_kb = cancion_info
        if self.nro_canciones >= 100:
            print("No hay espacio para más canciones")
            return self
        
        for i in range(self.nro_canciones):
            if self.canciones[0][i] == nombre and self.canciones[1][i] == artista:
                print("La canción ya existe")
                return self
        
        espacio_necesario = peso_kb / 1024 / 1024
        if self._calcular_espacio_usado() + espacio_necesario > self.capacidad_gb:
            print("No hay suficiente espacio")
            return self
        
        self.canciones[0][self.nro_canciones] = nombre
        self.canciones[1][self.nro_canciones] = artista
        self.canciones[2][self.nro_canciones] = str(peso_kb)
        self.nro_canciones += 1
        print(f"Canción '{nombre}' agregada")
        return self
    
    def __sub__(self, video_info):
        nombre, artista, peso_mb = video_info
        if self.nro_videos >= 100:
            print("No hay espacio para más videos")
            return self
        
        for i in range(self.nro_videos):
            if self.videos[i][0] == nombre and self.videos[i][1] == artista:
                print("El video ya existe")
                return self
        
        espacio_necesario = peso_mb / 1024
        if self._calcular_espacio_usado() + espacio_necesario > self.capacidad_gb:
            print("No hay suficiente espacio")
            return self
        
        self.videos[self.nro_videos][0] = nombre
        self.videos[self.nro_videos][1] = artista
        self.videos[self.nro_videos][2] = str(peso_mb)
        self.nro_videos += 1
        print(f"Video '{nombre}' agregado")
        return self
    
    def _calcular_espacio_usado(self):
        espacio_usado = 0
        
        for i in range(self.nro_canciones):
            if self.canciones[2][i]:
                espacio_usado += float(self.canciones[2][i]) / 1024 / 1024
        
        for i in range(self.nro_videos):
            if self.videos[i][2]:
                espacio_usado += float(self.videos[i][2]) / 1024
        
        return espacio_usado
    
    def mostrar_capacidad(self):
        espacio_usado = self._calcular_espacio_usado()
        espacio_disponible = self.capacidad_gb - espacio_usado
        
        print(f"\n=== CAPACIDAD {self.marca} ===")
        print(f"Capacidad total: {self.capacidad_gb} GB")
        print(f"Espacio usado: {espacio_usado:.4f} GB")
        print(f"Espacio disponible: {espacio_disponible:.4f} GB")
        print(f"Canciones: {self.nro_canciones}")
        print(f"Videos: {self.nro_videos}")
    
    def mostrar_contenido(self):
        print("\n=== CANCIONES ===")
        for i in range(self.nro_canciones):
            print(f"{i+1}. {self.canciones[0][i]} - {self.canciones[1][i]} ({self.canciones[2][i]} KB)")
        
        print("\n=== VIDEOS ===")
        for i in range(self.nro_videos):
            print(f"{i+1}. {self.videos[i][0]} - {self.videos[i][1]} ({self.videos[i][2]} MB)")

if __name__ == "__main__":
    mp4 = MP4("Sony", 1.0)
    
    print("=== EJERCICIO 13 - MP4 ===")
    
    print("\n1. AGREGANDO CONTENIDO:")
    mp4 + ("Back To Black", "Amy Winehouse", 100.0)
    mp4 + ("Lost On You", "LP", 150.0)
    mp4 - ("Heathens", "twenty one pilots", 50.0)
    mp4 - ("Harmonica Andromeda", "KSHMR", 70.0)
    
    print("\n2. MOSTRAR CONTENIDO:")
    mp4.mostrar_contenido()
    
    print("\n3. CAPACIDAD:")
    mp4.mostrar_capacidad()
    
    print("\n4. BORRAR CANCIONES:")
    mp4.borrar("Back To Black")
    mp4.borrar("Lost On You", "LP")
    mp4.borrar("Heathens", "twenty one pilots", "50.0")
    
    print("\n5. CAPACIDAD FINAL:")
    mp4.mostrar_capacidad()