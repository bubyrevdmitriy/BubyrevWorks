package ITMO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task2 {
    public static void main(String[] args) {

        System.out.println("Задание №2");
        System.out.println("Написать метод, который записывает в файл строку, переданную параметром.");

        try(FileWriter writer = new FileWriter("Task2File.txt", false))
        {
            // запись всей строки
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите строку 1");
            String nString1 = reader.readLine();
            System.out.println("Введите строку 2");
            String nString2 = reader.readLine();
            //String text = "Hello Gold!";
            writer.write(nString1);
            // запись по символам
            writer.append('\n');
            writer.append(nString2);
            System.out.println("строка успешно добавлена!");

            reader.close();
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }







        /*OutputStream out;
        List<String> AviatorPoem = new ArrayList<>();

        try {

            out = new FileOutputStream("Task2File.txt");
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите строку");
            String nString = reader.readLine();
            writer.append(nString);
            System.out.println("строка успешно добавлена!");
           out.close();
           reader.close();
        }
        catch(Exception e) {
            System.out.println("Ошибка ввода данных");
        }*/
    }
}
