package ITMO;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {

        System.out.println("Задание №5");
        System.out.println("Написать метод, который в каталоге ищет файлы, в имени которых содержится\n" +
                "определенная строка, и который возвращает список имен таких файлов.");

        try {

            // будем искать в папке tmp
            String dir = "C:\\Users\\bubyr\\IdeaProjects\\ITMO.Java.Course2020.Labs.Lab09";
            //имя файла
            String baseName = "Task3";

            // вызываем метод поиска файлов
            findFiles(dir, baseName);

        }
        catch(Exception e) {
            System.out.println("Ошибка ввода данных");
        }
    }

    // метод поиска
    private static void findFiles(String dir, String baseName) {
        File file = new File(dir);
        if(!file.exists()) System.out.println(dir + " папка не существует");
        File[] listFiles = file.listFiles(new MyFileNameFilter(baseName));
        if(listFiles.length == 0){
            System.out.println(dir + " не содержит файлов с именем, включающем " + baseName);
        }else{
            for(File f : listFiles)
                System.out.println("Файл: " + dir + File.separator + f.getName());
        }
    }

    // Реализация интерфейса FileNameFilter
    public static class MyFileNameFilter implements FilenameFilter{

        private String baseName;

        public MyFileNameFilter(String baseName){
            this.baseName = baseName.toLowerCase();
        }
        @Override
        public boolean accept(File dir, String name) {
            return name.toLowerCase().contains(baseName);
        }
    }

}






