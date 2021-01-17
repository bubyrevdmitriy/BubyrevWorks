package com.company;

public class Main {

    public static void main(String[] args) {
        // write your code here
        System.out.println("тестируем статический метод");
        Vector[] vectors = Vector.ArrayVectors(5);
        System.out.println("Наш массив векторов:");
        for (int i = 0; i < vectors.length; i++) {
            System.out.print(vectors[i].toString());
            System.out.println(" ");
        }
        System.out.println();

        System.out.println("Задаем вектор 1");
        Vector vector1 = new Vector(2, 2, 2);
        System.out.println(vector1.toString());
        System.out.println("Задаем вектор 2");
        Vector vector2 = new Vector(10, 10, 5);
        System.out.println(vector2.toString());

        System.out.println("Длинна вектора 1");
        System.out.println(vector1.vectorLength());

        System.out.println("Скалярное произведение вектора 1 и вектора 2");
        System.out.println(vector1.vectorScalarMultiple(vector2));

        System.out.println("Угл между вектором 1 и вектором 2");
        System.out.println(vector1.vectorAngle(vector2));

        System.out.println("Сумма вектора 1 и вектора 2");
        Vector vectorSum = vector1.VectorSum(vector2);
        System.out.println(vectorSum.toString());

        System.out.println("Вычитание из вектора 1 и вектора 2");
        Vector vectorDif = vector1.VectorDif(vector2);
        System.out.println(vectorDif.toString());

        System.out.println("Умножение вектора 1 на вектор 2");
        Vector vectorMultiple = vector1.VectorMultiple(vector2);
        System.out.println(vectorMultiple.toString());

    }
}