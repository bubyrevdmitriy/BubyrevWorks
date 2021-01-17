package com.company;

public class Task4 {
    public static void main(String[] args) {

        System.out.println("Задание №4");

        System.out.println("Напишите программу, которая вычислит простые числа в пределах от 2 до 100.\n" +
                        "Для решения этой задачи понадобится вычислить остаток от деления. В Java для\n " +
                "этого есть оператор % (например, 103 % 10 это 3).");

        System.out.println("Расчет начат!");

        for(int i = 2 ; i <=100 ; i++){
            double midResult=0;
            for(int j = i ; j >= 1 ; j--){

                if( i%j == 0){
                    midResult++;
                }
            }
            //System.out.println(midResult);
            if(midResult==2){
                System.out.println(i);
            }
        }



        System.out.println("Расчет завершен!");
        // write your code here
    }
}
