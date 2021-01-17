package ITMO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {

        System.out.println("Задание №1");
        System.out.println("Написать метод, который читает текстовый файл и возвращает его в виде списка\n" +
                "строк.");

        InputStream in;
        List<String> AviatorPoem = new ArrayList<>();

        try {

            in = new FileInputStream("Aviator.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                AviatorPoem.add(line);
            }
            String temp;
            for (int i = 0; i < AviatorPoem.size(); i++) {
                temp = AviatorPoem.get(i);
                System.out.println(temp);
            }
            in.close();

        }
        catch(Exception e) {
            System.out.println("Ошибка ввода данных");
        }
    }
}
