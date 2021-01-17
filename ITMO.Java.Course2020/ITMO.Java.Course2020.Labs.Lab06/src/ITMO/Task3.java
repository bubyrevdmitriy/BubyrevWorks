package ITMO;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task3 {
    public static void main(String[] args) {

        System.out.println("Задание №3");

        System.out.println("Используя Stringbuilder, читайте слова с консоли и затем склейте их в одну строку\n" +
                "через запятую.");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Введите строку №1:");
            String string1 = reader.readLine();
            //---
            System.out.println("Введите строку №2:");
            String string2 = reader.readLine();
            //--
            System.out.println("Введите строку №3:");
            String string3 = reader.readLine();
            //--

            //---------------------------------
            StringBuilder stringBuilder1 = new StringBuilder(string1);
            stringBuilder1.append(", ");
            stringBuilder1.append(string2);
            stringBuilder1.append(", ");
            stringBuilder1.append(string3);
            //------------------------------------
            System.out.println("Строка полученная с помощью StringBuilder");
            System.out.println(stringBuilder1);

            reader.close();//  Block of code to try
        }
        catch(Exception e) {
            //  Block of code to handle errors
            System.out.println("Ошибка ввода данных");
        }



    }
}
