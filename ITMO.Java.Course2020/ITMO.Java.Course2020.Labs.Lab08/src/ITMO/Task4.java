package ITMO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task4 {
    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №4");

        System.out.println("*Дан массив чисел, и дан массив весов такой же длины. Задача: написать метод,\n" +
                "который бы случайно выбирал число из первого массива с весом из второго.");
        System.out.println("");
        try
        {
            /*int num1=0, num2;
            num2 = 62 / num1;
            System.out.println(num2);*/
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите количество элементов в массиве");
            String nString = reader.readLine();
            int n = Integer.parseInt(nString);
            //заполнение первого масива
            int[] myArray1 = new int[n];
            for (int i = 0; i < n; i++) {
                myArray1[i] = i;
            }
            //заполнение второго массива
            int[] myArray2 = new int[n];
            for (int i = 0; i < n; i++) {
                myArray2[i] = i*10;
            }

            int[] myArray3 = CodeResourses.randomArray(myArray1, myArray2);

            System.out.println("первое число");
            System.out.println(myArray3[0]);
            System.out.println("второе число");
            System.out.println(myArray3[1]);

            System.out.println("программа отработала успшено");
            reader.close();//  Block of code to try
        }
        catch (Exception e)
        {
            //error handling code
            System.out.println("произошла ошибка");
        }
    }
}
