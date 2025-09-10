import math

class AlgebraVectorial:
    def __init__(self, x, y, z=0):
        self.x = x
        self.y = y
        self.z = z
    
    def __repr__(self):
        return f"({self.x}, {self.y}, {self.z})"
    
    def __add__(self, other):
        return AlgebraVectorial(self.x + other.x, self.y + other.y, self.z + other.z)

    def __sub__(self, other):
        return AlgebraVectorial(self.x - other.x, self.y - other.y, self.z - other.z)

    def dot(self, other):
        return self.x * other.x + self.y * other.y + self.z * other.z

    def cross(self, other):
        return AlgebraVectorial(
            self.y * other.z - self.z * other.y,
            self.z * other.x - self.x * other.z,
            self.x * other.y - self.y * other.x
        )
    
    def norm(self):
        return math.sqrt(self.x**2 + self.y**2 + self.z**2)
    
    def es_perpendicular(self, other):
        return self.dot(other) == 0

    def es_paralelo(self, other):
        return self.cross(other).norm() == 0
    
    def proyeccion_sobre(self, other):
        factor = self.dot(other) / (other.norm()**2)
        return AlgebraVectorial(other.x * factor, other.y * factor, other.z * factor)
    
    def componente_en(self, other):
        return self.dot(other) / other.norm()

if __name__ == "__main__":
    a = AlgebraVectorial(2, 3)
    b = AlgebraVectorial(4, 6)

    print("Vector a:", a)
    print("Vector b:", b)
    print("a · b:", a.dot(b))
    print("a x b:", a.cross(b))
    print("¿Perpendiculares?:", a.es_perpendicular(b))
    print("¿Paralelos?:", a.es_paralelo(b))
    print("Proyección de a sobre b:", a.proyeccion_sobre(b))
    print("Componente de a en b:", a.componente_en(b))
