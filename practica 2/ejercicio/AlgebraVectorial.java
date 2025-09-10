package ejercicio;
public class AlgebraVectorial {
    private double x, y, z;
    public AlgebraVectorial(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public AlgebraVectorial(double x, double y) {
        this(x, y, 0);
    }
    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }
    public AlgebraVectorial add(AlgebraVectorial other) {
        return new AlgebraVectorial(this.x + other.x, this.y + other.y, this.z + other.z);
    }
    public AlgebraVectorial subtract(AlgebraVectorial other) {
        return new AlgebraVectorial(this.x - other.x, this.y - other.y, this.z - other.z);
    }
    public double dot(AlgebraVectorial other) {
        return this.x * other.x + this.y * other.y + this.z * other.z;
    }
    public AlgebraVectorial cross(AlgebraVectorial other) {
        return new AlgebraVectorial(
            this.y * other.z - this.z * other.y,
            this.z * other.x - this.x * other.z,
            this.x * other.y - this.y * other.x
        );
    }
    public double norm() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }
    public boolean esPerpendicular(AlgebraVectorial other) {
        return this.dot(other) == 0;
    }
    public boolean esParalelo(AlgebraVectorial other) {
        return this.cross(other).norm() == 0;
    }
    public AlgebraVectorial proyeccionSobre(AlgebraVectorial other) {
        double factor = this.dot(other) / Math.pow(other.norm(), 2);
        return new AlgebraVectorial(other.x * factor, other.y * factor, other.z * factor);
    }
    public double componenteEn(AlgebraVectorial other) {
        return this.dot(other) / other.norm();
    }
}