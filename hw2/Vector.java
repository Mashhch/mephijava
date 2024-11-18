package hw2;

import java.util.Random;

public class Vector {
    private final double x, y, z;

    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double length() {
        return Math.sqrt(x * x + y * y + z * z);
    }

    public double dotProduct(Vector vec) {
        return this.x * vec.x + this.y * vec.y + this.z * vec.z;
    }

    public Vector crossProduct(Vector vec) {
        double crossX = this.y * vec.z - this.z * vec.y;
        double crossY = this.z * vec.x - this.x * vec.z;
        double crossZ = this.x * vec.y - this.y * vec.x;
        return new Vector(crossX, crossY, crossZ);
    }

    public double cosineAngle(Vector vec) {
        double dotProduct = this.dotProduct(vec);
        double lengthsProduct = this.length() * vec.length();
        return dotProduct / lengthsProduct;
    }

    public Vector add(Vector vec) {
        return new Vector(this.x + vec.x, this.y + vec.y, this.z + vec.z);
    }

    public Vector subtract(Vector vec) {
        return new Vector(this.x - vec.x, this.y - vec.y, this.z - vec.z);
    }

    public static Vector[] randomVectors(int n) {
        Random random = new Random();
        Vector[] vectors = new Vector[n];
        for (int i = 0; i < n; i++) {
            double x = Math.round(random.nextDouble() * 100) / 100.0;
            double y = Math.round(random.nextDouble() * 100) / 100.0;
            double z = Math.round(random.nextDouble() * 100) / 100.0;
            vectors[i] = new Vector(x, y, z);
        }
        return vectors;
    }

    @Override
    public String toString() {
        return "Vector {" + "x = " + x + ", y = " + y + ", z = " + z + '}';
    }

    public static void VectorArrayToString(Vector[] vectors) {
        System.out.printf("Массив из %d векторов:%n", vectors.length);
        for (Vector vec : vectors) {
            System.out.println(vec);
        }
    }

    public static void main(String[] args) {

        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(4, 5, 6);

        System.out.println("Длина вектора v1: " + v1.length());
        System.out.println("Скалярное произведение v1 и v2: " + v1.dotProduct(v2));
        System.out.println("Векторное произведение v1 и v2: " + v1.crossProduct(v2));
        System.out.println("Косинус угла между v1 и v2: " + v1.cosineAngle(v2));
        System.out.println("Сумма векторов v1 и v2: " + v1.add(v2));
        System.out.println("Разность векторов v1 и v2: " + v1.subtract(v2));

        Vector[] randomVectors = Vector.randomVectors(3);
        Vector.VectorArrayToString(randomVectors);

    }
}
