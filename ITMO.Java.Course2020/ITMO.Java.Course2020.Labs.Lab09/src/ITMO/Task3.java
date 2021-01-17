package ITMO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task3 {
    public static void main(String[] args) {

        System.out.println("Задание №3");
        System.out.println("Используя решение 1 и 2, напишите метод, который склеивает два текстовый\n" +
                "файла один.");

        InputStream in1;
        List<String> Poem1 = new ArrayList<>();

        InputStream in2;
        List<String> Poem2 = new ArrayList<>();

        try {
            in1 = new FileInputStream("Task3File1.txt");
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(in1));
            String line1 = null;
            while ((line1 = reader1.readLine()) != null) {
                Poem1.add(line1);
            }

            in2 = new FileInputStream("Task3File2.txt");
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(in2));
            String line2 = null;
            while ((line2 = reader2.readLine()) != null) {
                Poem2.add(line2);
            }

            File file = new File("Task3File3.txt");

            // Создание файла
            file.createNewFile();

            // Создание объекта FileWriter
            FileWriter writer = new FileWriter(file);

            //FileWriter writer = new FileWriter("Task3File3.txt");
            String temp1, temp2;

            for (int i = 0; i < Poem1.size(); i++) {
                temp1 = Poem1.get(i);
                System.out.println(temp1);
                writer.write(temp1);
                // запись по символам
                writer.append('\n');
            }

            for (int i = 0; i < Poem2.size(); i++) {
                temp2 = Poem2.get(i);
                System.out.println(temp2);
                writer.write(temp2);
                //writer.append(temp2);
                // запись по символам
                writer.append('\n');
            }
            writer.flush();
            writer.close();
            reader1.close();
            reader2.close();
        }
        catch(Exception e) {
            System.out.println("Ошибка ввода данных");
        }
    }
}
