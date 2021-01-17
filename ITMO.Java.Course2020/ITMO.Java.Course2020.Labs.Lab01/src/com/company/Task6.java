package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task6 {
    public static void main(String[] args) {

        System.out.println("Задание №6");

        System.out.println("Дан массив чисел и число. Удалите все вхождения числа в массив (пропусков\n быть не должно).");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите количество элементов в массиве");
            String nString = reader.readLine();
            int n = Integer.parseInt(nString);
            //---
            double[] myArray = new double[n];
            for (int i = 0; i < n; i++) {
                myArray[i] = Math.round(100*Math.random());
            }
            //---
            System.out.println("наш массив чисел до удаления:");
            for (int i = 0; i < myArray.length; i++) {
                System.out.print( myArray[i]);
                System.out.print(" ");
            }
            System.out.println();
            //---
            List<Double> myList = new ArrayList<Double>();
            for(Double temp:myArray) {
                myList.add(temp);
            }
            //System.out.println("наш лист чисел:");
            //System.out.println("наш лист чисел: " + myList);





            System.out.println("Введите число для улаления");
            String nDeleteString = reader.readLine();
            double nDelete = Double.parseDouble(nDeleteString);
            //---



            for (int i = 0; i < myList.size(); i++)
            {
                double k = myList.get(i);
                if(k==nDelete)
                {
                    //System.out.println("удаление"+k+nDelete);
                    myList.remove(i);
                    i--;
                }
                //else {System.out.println("неудаление"+k+nDelete);}
            }



            double[] myArrayResult = new double[myList.size()];
            for (int i = 0; i < myArrayResult.length; i++) {
                myArrayResult[i] = myList.get(i);
            }
            //---
            System.out.println("наш массив чисел после удаления:");
            for (int i = 0; i < myArrayResult.length; i++) {
                System.out.print( myArrayResult[i]);
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
