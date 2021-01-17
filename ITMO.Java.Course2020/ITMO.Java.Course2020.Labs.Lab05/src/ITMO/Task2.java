package ITMO;

import java.util.Arrays;

public class Task2 {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №2");

        System.out.println("Написать метод, который проверяет является ли слово палиндромом.");

            String stringA = "tenet";
            String stringB = "sator";

        System.out.println("Первое слово: " + stringA);
        System.out.println("Второе слово: " + stringB);

        System.out.println("Является ли первое слово палиндромом:");
        char[] charA = new char[stringA.length()];
        for (int i = 0; i < stringA.length(); i++) {
            charA[i] = stringA.charAt(i);
        }
        //System.out.println(Arrays.toString(charA));
        //System.out.println(charA.length);

        boolean stringAResult=CodeResorses.polindrom(charA);

        System.out.println(stringAResult);

        System.out.println("Яыляется ли второе слово палиндромом:");
        char[] charB = new char[stringB.length()];
        for (int i = 0; i < stringB.length(); i++) {
            charB[i] = stringB.charAt(i);
        }
        //System.out.println(Arrays.toString(charB));

        boolean stringBResult=CodeResorses.polindrom(charB);

        System.out.println(stringBResult);




    }

}
