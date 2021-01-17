package ITMO;

import java.io.File;
import java.io.FilenameFilter;

public class Task6 {
    public static void main(String[] args) {

        System.out.println("Задание №6");
        System.out.println("Написать метод, который в каталоге ищет текстовые файлы, в которых содержится\n" +
                "определенная строка, и который возвращает список имен таких файлов.");

        try {

            // будем искать в папке tmp
            String dir = "C:\\Users\\bubyr\\IdeaProjects\\ITMO.Java.Course2020.Labs.Lab09";
            //имя файла
            String baseName = "Task3";
            // в этой папке будем искать файлы с расширением .txt
            String ext = ".txt";
            // вызываем метод поиска файлов с расширением .xml в папке tmp
            findFiles(dir, baseName, ext);

        }
        catch(Exception e) {
            System.out.println("Ошибка ввода данных");
        }
    }

    // метод поиска
    private static void findFiles(String dir, String baseName, String ext) {
        File file = new File(dir);
        if(!file.exists()) System.out.println(dir + " папка не существует");
        File[] listFiles = file.listFiles(new MyFileNameFilter(baseName, ext));
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
