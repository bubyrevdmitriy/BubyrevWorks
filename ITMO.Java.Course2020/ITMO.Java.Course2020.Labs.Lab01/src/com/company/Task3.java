package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;

import static com.sun.tools.javac.jvm.ByteCodes.swap;
import static java.util.Collections.swap;

public class Task3 {
    public static void main(String[] args) {

        System.out.println("Задание №3");

        System.out.println("Заполните массив случайным числами и отсортируйте его.\n" +
                        " Используйте сортировку пузырьком , сортировку выбором или сортировку\n вставками .");

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

            System.out.println("выбор метода сортировки.");

            System.out.println("Введите 1, если хотите выбрать метод пузырька,\n" +
                    "Введите 2, если хотите выбрать метод выбора\n " +
                    "Введите 3, если хотите выбрать метод вставок");

            String variationString = reader.readLine();
            int variation = Integer.parseInt(variationString);
            switch (variation) {
                case 1:
                    System.out.println("Выбран метод пузырька");

                    /*Внешний цикл каждый раз сокращает фрагмент массива, так как внутренний цикл каждый раз ставит в конец фрагмента максимальный элемент*/
                    for(int i = myArray.length-1 ; i > 0 ; i--){
                        for(int j = 0 ; j < i ; j++){
                            /*Сравниваем элементы попарно, если они имеют неправильный порядок, то меняем местами*/
                            if( myArray[j] > myArray[j+1] ){
                                double tmp = myArray[j];
                                myArray[j] = myArray[j+1];
                                myArray[j+1] = tmp;
                            }
                        }
                     }

                    break;
                case 2:
                    System.out.println("Выбран метод выбора");
                    //Каждый проход выбирать самый минимальный элемент и смещать его в начало.
                    // При этом каждый новый проход начинать сдвигаясь вправо, то есть первый проход — с первого элемента, второй проход — со второго.
                    for (int left = 0; left < myArray.length; left++) {
                        int minInd = left;
                        for (int i = left; i < myArray.length; i++) {
                            if (myArray[i] < myArray[minInd]) {
                                minInd = i;
                            }
                        }
                        //swap(myArray, left, minInd);
                        if( myArray[minInd] < myArray[left] ){
                            double tmp = myArray[left];
                            myArray[left] = myArray[minInd];
                            myArray[minInd] = tmp;
                        }
                    }
                    break;
                case 3:
                    System.out.println("Выбран метод вставок");

                    for (int left = 0; left < myArray.length; left++) {
                        // Вытаскиваем значение элемента
                        double value = myArray[left];
                        // Перемещаемся по элементам, которые перед вытащенным элементом
                        int i = left - 1;
                        for (; i >= 0; i--) {
                            // Если вытащили значение меньшее — передвигаем больший элемент дальше
                            if (value < myArray[i]) {
                                myArray[i + 1] = myArray[i];
                            } else {
                                // Если вытащенный элемент больше — останавливаемся
                                break;
                            }
                        }
                        // В освободившееся место вставляем вытащенное значение
                        myArray[i + 1] = value;
                    }

                    break;

                default:
                    System.out.println("ошибка выбора");
                    break;
            }


            System.out.println("сортитровка произведена");

            for (int i = 0; i < myArray.length; i++) {
                System.out.print(String.format("%.3g%n", myArray[i]));
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
