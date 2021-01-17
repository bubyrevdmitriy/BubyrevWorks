package ITMO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task2 {
    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №2");

        System.out.println("Написать метод, который проверяет, является ли двумерные массив квадратным.");
        System.out.println("");
        try
        {
            /*int num1=0, num2;
            num2 = 62 / num1;
            System.out.println(num2);*/
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите количество  строк в массиве");
            String iString = reader.readLine();
            int i = Integer.parseInt(iString);
            System.out.println("Введите количество столбцов массиве");
            String jString = reader.readLine();
            int j = Integer.parseInt(jString);

            int[][] array = new int[i][j];

            //заполнение массива
            for (int i1 = 0; i1 < array.length; i1++) {
                for (int j1 = 0; j1 < array[i1].length; j1++) {
                    array[i1][j1] =i1*j1;
                }
            }
            //печать массива
            for (int i1 = 0; i1 < array.length; i1++) {
                for (int j1 = 0; j1 < array[i1].length; j1++) {
                    System.out.print(array[i1][j1] + "\t");
                }
                System.out.println();
            }


            boolean result = CodeResourses.arraySquare(array);
            System.out.println("является ли массив квадратным:");
            System.out.println(result);



            reader.close();//  Block of code to try
            System.out.println("программа отработала успшено");
        }
        catch (Exception e)
        {
            //error handling code
            System.out.println("произошла ошибка");
        }
    }
}
