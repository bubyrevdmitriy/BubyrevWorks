package ITMO;

import javax.xml.transform.Result;
import java.util.Arrays;

public class Task5 {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №5");

        System.out.println("Напишите метод, который инвертирует слова в строке. Предполагается, что в\n" +
                "строке нет знаков препинания, и слова разделены пробелами.\n" +
                "Sample Output:\n" +
                "The given string is: This is a test string\n" +
                "The string reversed word by word is:\n" +
                "sihT si a tset gnirts");

        String stringA ="This is a test string";


        System.out.println("Исходная строка");
        System.out.println(stringA);

        String stringResult=CodeResorses.stringReverse(stringA);

            System.out.println(stringResult);

    }

}
