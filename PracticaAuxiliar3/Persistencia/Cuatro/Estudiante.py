import pickle
import os

class Estudiante:
    def __init__(self, ru, nombre, paterno, materno, edad):
        self.ru = ru
        self.nombre = nombre
        self.paterno = paterno
        self.materno = materno
        self.edad = edad

class Nota:
    def __init__(self, materno, notaFinal, estudiante):
        self.materno = materno
        self.notaFinal = notaFinal
        self.estudiante = estudiante

class ArchiNota:
    def __init__(self, nombreArchi="notas.dat"):
        self.nombreArchi = nombreArchi
        self.notas = []
        self.cargar()

    def agregar_estudiantes(self, lista_notas):
        self.notas.extend(lista_notas)
        self.guardar()

    def promedio_notas(self):
        if not self.notas:
            return 0
        total = sum(n.notaFinal for n in self.notas)
        return total / len(self.notas)

    def mejores_estudiantes(self):
        if not self.notas:
            return []
        max_nota = max(n.notaFinal for n in self.notas)
        return [n for n in self.notas if n.notaFinal == max_nota]

    def eliminar_por_materia(self, materia):
        self.notas = [n for n in self.notas if n.materno != materia]
        self.guardar()

    def guardar(self):
        with open(self.nombreArchi, 'wb') as f:
            pickle.dump(self.notas, f)

    def cargar(self):
        if os.path.exists(self.nombreArchi):
            with open(self.nombreArchi, 'rb') as f:
                self.notas = pickle.load(f)

if __name__ == "__main__":
    archi = ArchiNota()
    e1 = Estudiante("123", "Ana", "Lopez", "Perez", 20)
    e2 = Estudiante("124", "Luis", "Garcia", "Martinez", 21)
    n1 = Nota("Matematica", 85, e1)
    n2 = Nota("Fisica", 90, e2)
    n3 = Nota("Matematica", 88, e2)
    archi.agregar_estudiantes([n1, n2, n3])
    print(f"Promedio: {archi.promedio_notas()}")
    mejores = archi.mejores_estudiantes()
    print(f"Mejor(es) estudiante(s): {[(m.estudiante.nombre, m.notaFinal) for m in mejores]}")
    archi.eliminar_por_materia("Matematica")
    print(f"Notas despues de eliminar Matematica: {len(archi.notas)}")