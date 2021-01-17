package ITMO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task3 {
    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №3");

        System.out.println("Написать метод, который, в двумерном массиве (матрице) ищет строку, сумма\n" +
                "элементов которой является максимальной среди всех строк матрицы.");
        System.out.println("");
        try
        {
            /*int num1=0, num2;
            num2 = 62 / num1;
            System.out.println(num2);*/
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            /*System.out.println("Введите количество  строк в массиве");
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
            }*/
            int[][] array = new int[3][4];
            array[0][0] = 5;//записали значение 5 в ячейку на пересечении нулевой строки и нулевого столбца
            array[0][1] = 7; //записали значение 7 в ячейку на пересечении нулевой строки и первого столбца
            array[0][2]  = 3;
            array[0][3] = 17;
            array[1][0] = 7;
            array[1][1] = 0;
            array[1][2] = 1;
            array[1][3] = 12;
            array[2][0] = 8;
            array[2][1] = 1;
            array[2][2] = 2;
            array[2][3] = 30;

            //печать массива
            for (int i1 = 0; i1 < array.length; i1++) {
                for (int j1 = 0; j1 < array[i1].length; j1++) {
                    System.out.print(array[i1][j1] + "\t");
                }
                System.out.println();
            }

            int result=CodeResourses.maxString(array);
            System.out.println("Максимальная строка в массиве:");
            System.out.println(result);




            //reader.close();//  Block of code to try
            System.out.println("программа отработала успшено");
        }
        catch (Exception e)
        {
            //error handling code
            System.out.println("произошла ошибка");
        }
    }
}
