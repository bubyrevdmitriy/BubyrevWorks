package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task8 {
    public static void main(String[] args) {

        System.out.println("Задание №8");

        System.out.println("Дан массив и число K. Найдите первые K самых часто встречающихся\n элементов.");

        int[] myArray = new int[]{ 1,2,3,4,5,6,7,8,9,10,1,2,4,5,7,8,9,10,1,2,3,4,5,6,7,8,9 };

        System.out.println("наш массив чисел до:");
        for (int i = 0; i < myArray.length; i++) {
            System.out.print( myArray[i]);
            System.out.print(" ");
        }
        System.out.println(" ");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите число К");
            String kString = reader.readLine();
            int k = Integer.parseInt(kString);
            //---
            List<Integer> myList = new ArrayList<Integer>();

            int maxFreqValue=0;
            //int maxFreqId=0;
            int maxMidResult=0;
            //int midResultMax=0;

            for (int i = 0; i < myArray.length; i++) {

                int midResult=0;
                //System.out.print(myArray[i]);
                for (int j = i+1; j < myArray.length; j++) {
                    if(myArray[i]==myArray[j]) {
                        midResult++;
                    }
                }
                //System.out.println(" "+midResult);
                if (midResult>maxMidResult) {
                    maxMidResult=midResult;
                    maxFreqValue=myArray[i];
                    //maxFreqId=i;
                }
            }
            //------------------------------------------------------------------
            int calc =0;
            for (int i = 0; i < myArray.length; i++) {
                if(myArray[i]==maxFreqValue) {
                    System.out.print( myArray[i]);
                    calc++;
                    System.out.print(" ");
                }
                if(calc>=k){break;}

            }
            //-------------------------------------------------------------


            reader.close();//  Block of code to try
        }
        catch(Exception e) {
            //  Block of code to handle errors
            System.out.println("Ошибка ввода данных");
        }


        // write your code here
    }
}
