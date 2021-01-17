package ITMO.Task3;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleTask1 {

    public static void main(String[] args) {
        System.out.println("Простые задания. Задание №1");

        System.out.println("Реализуйте метод indexOf():\n " +
                "Метод должен принимать массив и элемент в качестве параметров, возвращать индекс найденного элемента (-1, если такой не найден)");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите количество элементов в массиве");
            String nString = reader.readLine();
            int n = Integer.parseInt(nString);
            //---
            int[] myArray = new int[n];
            for (int i = 0; i < n; i++) {
                myArray[i] = i;
            }
            //---
            System.out.println("наш массив чисел:");
            for (int i = 0; i < myArray.length; i++) {
                System.out.print( myArray[i]);
                System.out.print(" ");
            }
            System.out.println();

            System.out.println("Введите значение искомого элемента в массиве");
            String searchElementString = reader.readLine();
            int searchElement = Integer.parseInt(searchElementString);
            //---

            int middleResult = CodeResourses.linearSearchIndex(myArray, searchElement);
            //System.out.println(middleResult);
            CodeResourses.printIndexSearch(searchElement, middleResult);

            //---
            reader.close();//  Block of code to try
        }
        catch(Exception e) {
            //  Block of code to handle errors
            System.out.println("Ошибка ввода данных");
        }
        // write your code here
    }
}
