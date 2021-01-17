package com.company;

public class Task7 {
    public static void main(String[] args) {

        System.out.println("Задание №7");

        System.out.println("Дан массив чисел. Найдите первое уникальное в это массиве число.\n Например, для массива [1, 2, 3, 1, 2, 4] это число 3.");

        int[] myArray = new int[]{ 1,2,3,4,5,6,7,8,9,10,1,2,4,5,7,8,9,10 };

        System.out.println("наш массив чисел до:");
        for (int i = 0; i < myArray.length; i++) {
            System.out.print( myArray[i]);
            System.out.print(" ");
        }
        System.out.println(" ");
        for (int i = 0; i < myArray.length; i++) {
            double midResult=0;
            //System.out.print(myArray[i]);
            for (int j = i+1; j < myArray.length; j++) {
                if(myArray[i]==myArray[j]) {
                    midResult++;

                    break;
                }
            }
            //System.out.println(" "+midResult);
            if (midResult==0) {
                System.out.println(myArray[i]);
                break;
            }
        }

        // write your code here
    }
}
