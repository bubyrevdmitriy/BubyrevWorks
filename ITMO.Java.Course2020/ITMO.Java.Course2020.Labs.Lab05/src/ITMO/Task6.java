package ITMO;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Task6 {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №6");

        System.out.println("* Постройте частотный словарь букв русского (или английского) алфавита.\n" +
                "(опустим проблему выбора и анализа корпуса языка, достаточно будет взять текст\n" +
                "небольшой длины).\n" +
                "Для чтения текста из файла можно использовать такую конструкцию:\n" +
                "String content = new String(Files.readAllBytes(Paths.get(\"readMe.txt\")), \"UTF-8\");");

        System.out.println("Считаем файл в наше приложение:");

        //String content = new String(Files.readAllBytes(Paths.get("readMe.txt")), "UTF-8");

        //Path file = Paths.get("C:\\Users\\bubyr\\IdeaProjects\\ITMO.Java.Course2020.Labs.Lab05test\\Aviator.txt");//C:\Users\bubyr\IdeaProjects\ITMO.Java.Course2020.Labs.Lab05
        InputStream in;
        List<String> AviatorPoem = new ArrayList<>();
        try {
            in = new FileInputStream("Aviator.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = null;
            while ((line = reader.readLine()) != null) {
                AviatorPoem.add(line);
            }
            in.close();
        } catch (IOException x) {
            System.err.println(x);
        }

        String temp;
       for (int i = 0; i < AviatorPoem.size(); i++) {
            temp = AviatorPoem.get(i);
            System.out.println(temp);
        }
        System.out.println("");
        Ideone.makeRusFrequency(AviatorPoem);






    }

}
