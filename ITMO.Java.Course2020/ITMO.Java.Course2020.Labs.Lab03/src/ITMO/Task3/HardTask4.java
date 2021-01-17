package ITMO.Task3;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class  HardTask4 {

    public static void main(String[] args) {
        System.out.println("Сложные задания. Задание №4");

        System.out.println("Реализовать быструю сортировку.\n " +
                "Попробуйте разные подходы к выбору опорного элемента (первый, средний\n " +
                "из трех), сравните результат на одних и тех же данных.");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите количество элементов в массиве");
            String nString = reader.readLine();
            int n = Integer.parseInt(nString);
            //---
            int[] myArray = new int[n];
            for (int i = 0; i < n; i++) {
                myArray[i] = (int) (100*Math.random());
            }
            //---
            System.out.println("наш массив чисел:");
            for (int i = 0; i < myArray.length; i++) {
                System.out.print( myArray[i]);
                System.out.print(" ");
            }
            System.out.println();
            //---

            System.out.println("выбор опорного элемента.");

            System.out.println("Введите 1, если хотите назначить опорным элементом элемент в первой половине масива,\n" +
                    "Введите 2, если хотите назначить опорным элементом элемент в середине масива\n " +
                    "Введите 3, если хотите назначить опорным элементом элемент во второй половине масива");

            String variationString = reader.readLine();
            int variation = Integer.parseInt(variationString);
            switch (variation) {
                case 1:
                    System.out.println("Опорным элементом назначен элемент в первой половине масива");
                    long startTime1 = System.currentTimeMillis();

                    CodeResourses.quickSortMin(myArray, 0, myArray.length-1);

                    long timeSpent1 = System.currentTimeMillis() - startTime1;
                    System.out.println("программа выполнялась " + timeSpent1 + " миллисекунд");
                    break;
                case 2:
                    System.out.println("Опорным элементом назначен элемент в середине масива");
                    long startTime2 = System.currentTimeMillis();

                    CodeResourses.quickSort(myArray, 0, myArray.length-1);

                    long timeSpent2 = System.currentTimeMillis() - startTime2;
                    System.out.println("программа выполнялась " + timeSpent2 + " миллисекунд");
                    break;
                case 3:
                    System.out.println("Опорным элементом назначен элемент во второй половине масива");
                    long startTime3 = System.currentTimeMillis();

                    CodeResourses.quickSortMax(myArray, 0, myArray.length-1);

                    long timeSpent3 = System.currentTimeMillis() - startTime3;
                    System.out.println("программа выполнялась " + timeSpent3 + " миллисекунд");
                    break;

                default:
                    System.out.println("ошибка выбора");
                    break;
            }


            System.out.println("сортитровка произведена");

            for (int i = 0; i < myArray.length; i++) {
                System.out.print(myArray[i]);
                System.out.print(" ");
            }

            reader.close();//  Block of code to try
        }
        catch(Exception e) {
            //  Block of code to handle errors
            System.out.println("Ошибка ввода данных");
        }
        // write your code here



    }
}