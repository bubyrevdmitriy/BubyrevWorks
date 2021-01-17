package ITMO.Task3;



public class SimpleTask4 {

    public static void main(String[] args) {
        System.out.println("Простые задания. Задание №4");

        System.out.println("Написать метод, переставляющий элементы массива в обратном порядке");
        int[] myArray = new int[]{ 1,2,3,4,5,6,7,8,9,10,1,2,4,5,7,8,9,10 };
        //---
        System.out.println("наш массив чисел:");
        for (int i = 0; i < myArray.length; i++) {
            System.out.print( myArray[i]);
            System.out.print(" ");
        }
        System.out.println();

        int[] myArrayResult = CodeResourses.ReverseArray(myArray);

        System.out.println("наш перевернутый массив чисел:");
        for (int i = 0; i < myArrayResult.length; i++) {
            System.out.print( myArrayResult[i]);
            System.out.print(" ");
        }

        // write your code here
    }
}