from datetime import datetime, timedelta
from typing import List

class Pagina:
    def __init__(self, numero: int, contenido: str):
        self.numero = numero
        self.contenido = contenido
    
    def mostrarContenido(self):
        print(f"Página {self.numero}: {self.contenido}")

class Autor:
    def __init__(self, nombre: str, nacionalidad: str):
        self.nombre = nombre
        self.nacionalidad = nacionalidad
    
    def mostrarInfo(self):
        print(f"Autor: {self.nombre} - Nacionalidad: {self.nacionalidad}")

class Estudiante:
    def __init__(self, codigo: str, nombre: str):
        self.codigo = codigo
        self.nombre = nombre
    
    def mostrarInfo(self):
        print(f"Estudiante: {self.nombre} - Código: {self.codigo}")

class Libro:
    def __init__(self, titulo: str, isbn: str, paginas_contenido: List[str]):
        self.titulo = titulo
        self.isbn = isbn
        self.paginas = self._crearPaginas(paginas_contenido)
    
    def _crearPaginas(self, contenidos: List[str]) -> List[Pagina]:
        paginas = []
        for i, contenido in enumerate(contenidos, 1):
            paginas.append(Pagina(i, contenido))
        return paginas
    
    def leer(self):
        print(f"\n--- Leyendo libro: {self.titulo} ---")
        for pagina in self.paginas:
            pagina.mostrarContenido()

class Horario:
    def __init__(self, dias_apertura: str, hora_apertura: str, hora_cierre: str):
        self.dias_apertura = dias_apertura
        self.hora_apertura = hora_apertura
        self.hora_cierre = hora_cierre
    
    def mostrarHorario(self):
        print(f"Horario: {self.dias_apertura} de {self.hora_apertura} a {self.hora_cierre}")

class Prestamo:
    def __init__(self, estudiante: Estudiante, libro: Libro):
        self.fecha_prestamo = datetime.now()
        self.fecha_devolucion = self.fecha_prestamo + timedelta(days=15)
        self.estudiante = estudiante
        self.libro = libro
    
    def mostrarInfo(self):
        print(f"Préstamo - Libro: {self.libro.titulo}")
        print(f"Estudiante: {self.estudiante.nombre}")
        print(f"Fecha préstamo: {self.fecha_prestamo.strftime('%Y-%m-%d')}")
        print(f"Fecha devolución: {self.fecha_devolucion.strftime('%Y-%m-%d')}")

class Biblioteca:
    def __init__(self, nombre: str):
        self.nombre = nombre
        self.horario = Horario("Lunes a Viernes", "08:00", "20:00")
        self.libros_disponibles = []
        self.autores_registrados = []
        self.prestamos_activos = []
    
    def agregarLibro(self, libro: Libro):
        self.libros_disponibles.append(libro)
        print(f"Libro '{libro.titulo}' agregado a la biblioteca")
    
    def agregarAutor(self, autor: Autor):
        self.autores_registrados.append(autor)
        print(f"Autor '{autor.nombre}' registrado en la biblioteca")
    
    def prestarLibro(self, estudiante: Estudiante, libro: Libro):
        if libro in self.libros_disponibles:
            prestamo = Prestamo(estudiante, libro)
            self.prestamos_activos.append(prestamo)
            self.libros_disponibles.remove(libro)
            print(f"Libro '{libro.titulo}' prestado a {estudiante.nombre}")
            return prestamo
        else:
            print(f"El libro '{libro.titulo}' no está disponible")
            return None
    
    def mostrarEstado(self):
        print(f"\n=== ESTADO DE LA BIBLIOTECA {self.nombre.upper()} ===")
        
        print("\n--- Horario de atención ---")
        self.horario.mostrarHorario()
        
        print(f"\n--- Autores registrados ({len(self.autores_registrados)}) ---")
        for autor in self.autores_registrados:
            autor.mostrarInfo()
        
        print(f"\n--- Libros disponibles ({len(self.libros_disponibles)}) ---")
        for libro in self.libros_disponibles:
            print(f"- {libro.titulo} (ISBN: {libro.isbn})")
        
        print(f"\n--- Préstamos activos ({len(self.prestamos_activos)}) ---")
        for prestamo in self.prestamos_activos:
            prestamo.mostrarInfo()
            print("---")
    
    def cerrarBiblioteca(self):
        print(f"\n🔒 Cerrando biblioteca {self.nombre}...")
        self.prestamos_activos.clear()
        print("Todos los préstamos han sido eliminados del sistema")

def demostrar_relaciones():
    print("🚀 DEMOSTRACIÓN DE RELACIONES ENTRE CLASES\n")
    
    print("1. CREACIÓN DE OBJETOS INDEPENDIENTES (Agregación)")
    
    autor1 = Autor("Gabriel García Márquez", "Colombiana")
    autor2 = Autor("Mario Vargas Llosa", "Peruana")
    
    estudiante1 = Estudiante("2023001", "Juan Pérez")
    estudiante2 = Estudiante("2023002", "María López")
    
    libro1 = Libro("Cien años de soledad", "978-8437604947", [
        "Muchos años después, frente al pelotón de fusilamiento...",
        "Macondo era entonces una aldea de veinte casas de barro...",
        "José Arcadio Buendía soñaba con ciudades de hielo..."
    ])
    
    libro2 = Libro("La ciudad y los perros", "978-8420471832", [
        "El Jaguar silbó suavemente en la oscuridad...",
        "Alberto se sentía observado por todos lados...",
        "La academia militar era un mundo aparte..."
    ])
    
    print("✓ Objetos creados independientemente\n")
    
    print("2. DEMOSTRACIÓN DE AGREGACIÓN (Biblioteca - Libro/Autor)")
    
    biblioteca = Biblioteca("Biblioteca Central UMSA")
    
    biblioteca.agregarLibro(libro1)
    biblioteca.agregarLibro(libro2)
    biblioteca.agregarAutor(autor1)
    biblioteca.agregarAutor(autor2)
    
    print("✓ Relación de Agregación demostrada\n")
    
    print("3. DEMOSTRACIÓN DE COMPOSICIÓN (Libro - Página, Biblioteca - Horario)")
    
    print("Composición Libro-Página:")
    libro1.leer()
    
    print("\nComposición Biblioteca-Horario:")
    biblioteca.horario.mostrarHorario()
    
    print("✓ Relación de Composición demostrada\n")
    
    print("4. DEMOSTRACIÓN DE ASOCIACIÓN (Préstamo - Estudiante - Libro)")
    
    prestamo1 = biblioteca.prestarLibro(estudiante1, libro1)
    prestamo2 = biblioteca.prestarLibro(estudiante2, libro2)
    
    print("\nDetalles de asociaciones en préstamos:")
    if prestamo1:
        prestamo1.mostrarInfo()
    
    print("✓ Relación de Asociación demostrada\n")
    
    print("5. ESTADO COMPLETO DEL SISTEMA")
    biblioteca.mostrarEstado()
    
    print("6. CIERRE DE BIBLIOTECA (Composición)")
    biblioteca.cerrarBiblioteca()
    
    print("\n7. VERIFICACIÓN DE OBJETOS INDEPENDIENTES")
    print("Los siguientes objetos siguen existiendo después del cierre:")
    autor1.mostrarInfo()
    estudiante1.mostrarInfo()
    print(f"Libro '{libro1.titulo}' sigue existiendo")

if __name__ == "__main__":
    demostrar_relaciones()