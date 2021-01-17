package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task1 {
    public static void main(String[] args) {

        System.out.println("Задание №1");

        System.out.println("Заполните массив случайным числами и выведете максимальное, минимальное и\n " +
                        "среднее значение.\n " +
                        "Для генерации случайного числа используйте метод Math.random(), который\n " +
                "возвращает значение в промежутке [0, 1].");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите количество элементов в массиве");
            String nString = reader.readLine();
            int n = Integer.parseInt(nString);
            //---
            double[] myArray = new double[n];
            for (int i = 0; i < n; i++) {
                myArray[i] = 10*Math.random();
            }
            //---
            System.out.println("наш массив чисел:");
            for (int i = 0; i < myArray.length; i++) {
                System.out.print(String.format("%.3g%n", myArray[i]));
                System.out.print(" ");
            }
            System.out.println();
            //---
            double min=0;
            double average=0;
            double max=0;
            //---
            for (int i = 0; i < myArray.length; i++) {
                if (max<myArray[i]){max=myArray[i];}
            }
            //---
            double sum=0;
            for (int i = 0; i < myArray.length; i++) {
                sum=sum+myArray[i];
            }
            average=sum/(myArray.length)+sum%(myArray.length);
            //---
            min=max;
            for (int i = 0; i < myArray.length; i++) {
                if (min>myArray[i]){min=myArray[i];}
            }

            //------------------------------------
            System.out.println("Минимальное число:");
            System.out.println(String.format("%.3g%n", min));
            System.out.println("Среднее число:");
            System.out.println(String.format("%.3g%n", average));
            System.out.println("Максимальное число:");
            System.out.println(String.format("%.3g%n", max));

            reader.close();//  Block of code to try
        }
        catch(Exception e) {
            //  Block of code to handle errors
            System.out.println("Ошибка ввода данных");
        }

    }
}
