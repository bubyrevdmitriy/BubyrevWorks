package ITMO.Task3;


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SimpleTask2 {

    public static void main(String[] args) {
        System.out.println("Простые задания. Задание №2");

        System.out.println("Написать метод, который проверяет, является ли массив возрастающей последовательностью");

            int[] myArray = new int[]{ 1,2,3,4,5,6,7,8,9,10 };
            //---
            System.out.println("наш массив чисел:");
            for (int i = 0; i < myArray.length; i++) {
                System.out.print( myArray[i]);
                System.out.print(" ");
            }
            System.out.println();
            //---
            boolean result = CodeResourses.progressiveArray(myArray);

            if(result) {
                System.out.println("наш массив чисел - возрастающая последовательность чисел");
            } else {
                System.out.println("наш массив чисел - не возрастающая последовательность чисел");
            }



        // write your code here
    }
}