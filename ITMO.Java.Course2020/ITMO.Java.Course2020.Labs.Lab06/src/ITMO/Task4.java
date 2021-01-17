package ITMO;

import ITMO.Filters.filterOnlyNegative;
import ITMO.Filters.filterOnlyPositive;

public class Task4 {
    public static void main(String[] args) {

        System.out.println("Задание №4");

        System.out.println("Написать метод filter, который принимает на вход массив (любого типа) и\n" +
                "реализацию абстрактного класса filter c методом apply(Object o), чтобы убрать\n" +
                "из массива лишнее.\n" +
                "Проверьте как он работает на строках или других объектах.\n" +
                "абстрактного класса для получения нового значения по индексу.");

        double[] d = {8.5, 7.1, -6.4, 5.8, -4.1};

        filterOnlyNegative filterOnlyNegative1 = new filterOnlyNegative();
        filterOnlyPositive filterOnlyPositive1 = new filterOnlyPositive();

        double[] onlyPositive = (double[]) filterOnlyNegative1.apply(d);
        double[] onlyNegative = (double[]) filterOnlyPositive1.apply(d);

        System.out.println("наш массив чисел:");

        CodeResourses.printArrayDouble(d);
        CodeResourses.printArrayDouble(onlyPositive);
        CodeResourses.printArrayDouble(onlyNegative);

    }
}
