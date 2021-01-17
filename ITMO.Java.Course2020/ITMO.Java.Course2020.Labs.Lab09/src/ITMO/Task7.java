package ITMO;

import java.io.File;
import java.io.FilenameFilter;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.function.Function;

public class Task7 {
    public static void main(String[] args) {

        System.out.println("Задание №7");
        System.out.println("Написать метод, который в каталоге ищет файлы удовлетворяющие любому\n" +
                "критерию, который задается с помощью java.util.function.Function.");

        Function<Integer, String> convert = x-> "Task" + String.valueOf(x);
        //System.out.println(convert.apply(3)); // 5 долларов

        try {

            // будем искать в папке tmp
            String dir = "C:\\Users\\bubyr\\IdeaProjects\\ITMO.Java.Course2020.Labs.Lab09";
            //имя файла
            String baseName = convert.apply(2);
            System.out.println(baseName);
            // в этой папке будем искать файлы с расширением .txt
            String ext = ".txt";
            // вызываем метод поиска файлов с расширением .xml в папке tmp
            findFiles(dir, baseName, ext);
            //String userHome = System.getProperty();
            System.out.println("userHome");

        }
        catch(Exception e) {
            System.out.println("Ошибка ввода данных");
        }
    }

    // метод поиска
    private static void findFiles(String dir, String baseName, String ext) {
        File file = new File(dir);
        if(!file.exists()) System.out.println(dir + " папка не существует");
        File[] listFiles = file.listFiles(new Task6.MyFileNameFilter(baseName, ext));
        if(listFiles.length == 0){
            System.out.println(dir + " не содержит файлов с расширением " + ext);
        }else{
            for(File f : listFiles)
                System.out.println("Файл: " + dir + File.separator + f.getName());
        }
    }

    // Реализация интерфейса FileNameFilter
    public static class MyFileNameFilter implements FilenameFilter {

        private String baseName;
        private String ext;

        public MyFileNameFilter(String baseName, String ext){
            this.baseName = baseName.toLowerCase();
            this.ext = ext.toLowerCase();
        }
        @Override
        public boolean accept(File dir, String name) {
            //String temp = name.toLowerCase().contains(baseName);
            return name.toLowerCase().contains(baseName)&&name.toLowerCase().endsWith(ext);
        }
    }

}