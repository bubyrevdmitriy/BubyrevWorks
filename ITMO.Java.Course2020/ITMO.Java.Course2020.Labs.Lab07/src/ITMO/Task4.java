package ITMO;

import ITMO.Filters.filterOnlyNegative;
import ITMO.Filters.filterOnlyPositive;

public class Task4 {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №4");

        System.out.println("Написать метод filter, которые принимает на вход массив (любого типа) и\n" +
                "реализацию интерфейса filter c методом apply(Object o), чтобы убрать из массива\n" +
                "лишнее.");
        System.out.println("");

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
