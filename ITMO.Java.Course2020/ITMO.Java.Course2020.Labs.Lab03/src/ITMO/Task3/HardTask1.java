package ITMO.Task3;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class HardTask1 {

    public static void main(String[] args) {
        System.out.println("Сложные задания. Задание №1");

        System.out.println("Написать метод, который проверяет, входит ли в сортированный массив\n" +
                        " заданный элемент или нет.\n" +
                        " Используйте перебор и двоичный поиск для решения этой задачи.\n" +
                "Сравните время выполнения обоих решений для больших массивов (например, 100000 элементов).");
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите количество элементов в массиве");
            String nString = reader.readLine();
            int n = Integer.parseInt(nString);
            //---
            int[] myArray = new int[n];
            for (int i = 0; i < n; i++) {
                myArray[i] = 2*i;
            }
            //---
            /*System.out.println("наш массив чисел:");
            for (int i = 0; i < myArray.length; i++) {
                System.out.print( myArray[i]);
                System.out.print(" ");
            }*/
            System.out.println();

            System.out.println("Введите значение искомого элемента в массиве");
            String searchString = reader.readLine();
            int searchElement = Integer.parseInt(searchString);

            int result=0;

            //--------------------------------------------------------------------------------------------------------
            System.out.println("выбор метода поиска.");

            System.out.println("Введите 1, если хотите выбрать метод перебора,\n" +
                    "Введите 2, если хотите выбрать двоичный поиск");

            String variationString = reader.readLine();
            int variation = Integer.parseInt(variationString);
            switch (variation) {
                case 1:
                    System.out.println("Выбран метод перебора");
                    //public static int linearSearchIndex(int arr[], int elementToSearch)
                    long startTime1 = System.currentTimeMillis();

                    result=CodeResourses.linearSearchIndex(myArray, searchElement);



                    long timeSpent1 = System.currentTimeMillis() - startTime1;
                    System.out.println("программа выполнялась " + timeSpent1 + " миллисекунд");
                    break;
                case 2:
                    System.out.println("Выбран двоичный поиск");
                    //public static int binarySearch(int arr[], int elementToSearch)
                    long startTime2 = System.currentTimeMillis();

                    result=CodeResourses.binarySearch(myArray, searchElement);



                    long timeSpent2 = System.currentTimeMillis() - startTime2;
                    System.out.println("программа выполнялась " + timeSpent2 + " миллисекунд");
                    break;
                default:
                    System.out.println("ошибка выбора");
                    break;
            }
            //---------------------------------------------------------------------------------------------------------
            System.out.println("Номер элемента в массиве:");
            System.out.println(result);

            reader.close();//  Block of code to try
        }
        catch(Exception e) {
            //  Block of code to handle errors
            System.out.println("Ошибка ввода данных");
        }
        // write your code here




        // write your code here
    }
}
