package ITMO;

import ITMO.Services.BinarySearchMultiThread;
import ITMO.Services.CodeResourses;
import ITMO.Services.CommonResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task3 {
    public static void main(String[] args) {

        System.out.println("Задание №3");
        System.out.println("Напишите метод, осуществляющий двоичный поиск в несколько потоков.\n" +
                "Используйте лямбды.");
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
            System.out.println("наш массив чисел:");
            for (int i = 0; i < myArray.length; i++) {
                System.out.print( myArray[i]);
                System.out.print(" ");
            }

            System.out.println();

            System.out.println("Введите значение искомого элемента в массиве");
            String searchString = reader.readLine();
            int searchElement = Integer.parseInt(searchString);

            Operationable operation;
            operation = (x,y)->x+y;

            CommonResource res = new CommonResource();

            long startTime1 = System.currentTimeMillis();

            /*int result= CodeResourses.binarySearchIndex(myArray, 0, myArray.length-1,searchElement);
            System.out.println("Номер элемента в массиве(индекс+1):");
            System.out.println(operation.calculate(result,1));*/


            BinarySearchMultiThread binarySearchMultiThread = new BinarySearchMultiThread(myArray, 0, myArray.length-1,searchElement, res);//search in left subarray
            binarySearchMultiThread.start();

            System.out.println("Номер элемента в массиве(индекс+1):");
            System.out.println(operation.calculate(res.getX(),1));

            long timeSpent1 = System.currentTimeMillis() - startTime1;
            System.out.println("программа выполнялась " + timeSpent1 + " миллисекунд");


            reader.close();//  Block of code to try


        }
        catch(Exception e) {
            System.out.println("Ошибка ввода данных");
        }
    }
    interface Operationable{
        int calculate(int x, int y);
    }
}
