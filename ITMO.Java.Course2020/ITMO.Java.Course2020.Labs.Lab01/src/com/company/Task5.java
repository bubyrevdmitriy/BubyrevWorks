package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task5 {
    public static void main(String[] args) {

        System.out.println("Задание №5");

        System.out.println("Вычислить N-е число Фибоначчи.");

        System.out.println("в нашем примере ряд чисел Фиббоначи будет начинаться с чисел 1 и 1.");

        System.out.println("Введите число N(N>2):");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String nString = reader.readLine();
            int n = Integer.parseInt(nString);
            //-------------------------------------
            int n0 = 1;
            int n1 = 1;
            int result=0;
            //System.out.print(n0+" "+n1+" ");
            for(int i = 3; i <= n; i++){
                result=n0+n1;
                //System.out.print(n2+" ");
                n0=n1;
                n1=result;
            }

            //------------------------------------
            System.out.println("Число Фиббоначи N="+n+"будет равен:");
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
