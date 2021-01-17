package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task2 {
    public static void main(String[] args) {

        System.out.println("Задание №2");

        System.out.println("Найти алгебраическую сумму для выражения: 1^k + 2^k + 3^k + … + N^k . N и степень k\n вводит пользователь.");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите число N(N>2):");
            String nString = reader.readLine();
            int n = Integer.parseInt(nString);
            //---
            System.out.println("Введите число k:");
            String kString = reader.readLine();
            int k = Integer.parseInt(kString);
            //-------------------------------------

            double result=0;

            for(int i = 1; i <= n; i++){
                result=result+Math.pow(i, k);
            }

            //------------------------------------
            System.out.println(result);

            reader.close();//  Block of code to try
        }
        catch(Exception e) {
            //  Block of code to handle errors
            System.out.println("Ошибка ввода данных");
        }

        // write your code here
    }
}
