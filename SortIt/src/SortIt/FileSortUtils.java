package SortIt;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class FileSortUtils {
    //класс содержит служебные методы для сортировки файлов и содержимого файлов

    public static void copyFilesToTempDir(String tempDirectoryName, String subTempDirectoryName, String inputFileName) {
        //метод для копирования исходных файлов в временную паку, внутри которой будет происходить их объединение
        //метод вызывается в классе Main перед сортировкий слиянием фуказанных пользователем файлов
        File fileOutput = new File(tempDirectoryName + subTempDirectoryName + "/" + inputFileName);
        BufferedReader reader = null;
        BufferedWriter writer = null;
        try {
            reader = new BufferedReader(new FileReader(inputFileName));
            writer = new BufferedWriter(new FileWriter(fileOutput));

            String lineResult;
            // счетчик count служит для правильного количества символов переноса строки внутри файла, и используется только в 2 первых записях, для последующих записей он не нужен
            int count = 0;
            while ((lineResult = reader.readLine()) != null) {
                if (count == 0) {
                    count++;
                    //защита от пустых значений
                    if (lineResult.trim().length() != 0) {
                        writer.write(lineResult+ "\n");
                    }

                } else if (count == 1) {
                    count++;
                    //защита от пустых значений
                    if (lineResult.trim().length() != 0) {
                        writer.write(lineResult);
                    }

                }else {
                    writer.write("\n");
                    //защита от пустых значений
                    if (lineResult.trim().length() != 0) {
                        writer.write(lineResult);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally{
            if ( reader != null )try{reader.close();}catch(Exception e){}
            if ( writer != null )try{writer.close();}catch(Exception e){}
        }
    }

    public static void writeResultSortingArrayToInputFile(Object[] array, String inputFileName) {
        //метод для записи отсоритрованного массива во исходный файл, этот метод используется для сортировки содержимого файла
        //этот метод вызывается в методах классов FileSortInteger и FileSortStrings, если содержимое исходного файла(файлов), переданных пользователем не было отсортировано
        //метод вызывается если мы сотрируем небольшой файл целиком помущаящийся в оперативную память, и не требующий создания временных файлов
        BufferedWriter writerResult = null;
        try {
            writerResult = new BufferedWriter(new FileWriter(inputFileName));

            for(int i = 0; i < array.length-1; i++) {
                writerResult.write(array[i] + "\n");
            }
            writerResult.write(String.valueOf(array[array.length-1]));
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally{
            if ( writerResult != null )try{writerResult.close();}catch(Exception e){}
        }
    }

    public static void writeResultSortingFileToInputFile(String tempDirectoryName, String subTempDirectoryName, String resultFileName, String outputFileName) {
        //метод для присваивания содержания отсортированного файла из временной папки в файл-результат
        File fileInputResult = null;

        //если в метод вместо первых двух значений были переданы пустые строки, то значит работа производится с исходными файлами, указанными пользователем при старте программы
        //причем переписка содержимого ведется напрямую в файл-рзультат, а значит создание новых файлов не требуется, и удаление файла после окончания работы тоже не требуется
        if(tempDirectoryName.trim().length() != 0 && subTempDirectoryName.trim().length() != 0) {
            fileInputResult = new File(tempDirectoryName + subTempDirectoryName + "/" + resultFileName);
        } else {
            fileInputResult = new File(resultFileName);
        }

        BufferedReader readerResult = null;
        BufferedWriter writerResult = null;
        try {
            readerResult = new BufferedReader(new FileReader(fileInputResult));
            writerResult = new BufferedWriter(new FileWriter(outputFileName));

            String lineResult;
            // счетчик count служит для правильного количества символов переноса строки внутри файла, и используется только в 2 первых записях, для последующих записей он не нужен
            int count = 0;
            while ((lineResult = readerResult.readLine()) != null) {
                if (count == 0) {
                    count++;
                    //защита от пустых значений
                    if (lineResult.trim().length() != 0) {
                        writerResult.write(lineResult+ "\n");
                    }

                } else if (count == 1) {
                    count++;
                    //защита от пустых значений
                    if (lineResult.trim().length() != 0) {
                        writerResult.write(lineResult);
                    }

                }else {
                    //count++;
                    writerResult.write("\n");
                    //защита от пустых значений
                    if (lineResult.trim().length() != 0) {
                        writerResult.write(lineResult);
                    }
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally{
            if ( readerResult != null )try{readerResult.close();}catch(Exception e){}
            if ( writerResult != null )try{writerResult.close();}catch(Exception e){}

            if(tempDirectoryName.trim().length() != 0 && subTempDirectoryName.trim().length() != 0) {
                fileInputResult.delete();
            }
        }
    }

    public static void writeOut(Object[] array, String fileName, String tempDirectoryName, String subTempDirectoryName) {
        //метод для записи отсоритрованного массива во временный файл, этот метод используется для сортировки содержимого файла
        //этот метод вфзфвается в методах классов FileSortInteger и FileSortStrings, если содержимое исходного файла(файлов), переданных пользователем не было отсортировано
        File file = new File(tempDirectoryName+subTempDirectoryName + "/" + fileName);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file));
            for(int i = 0; i < array.length; i++) {
                writer.write(array[i] + "\n");
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally{
            if ( writer != null )try{writer.close();}catch(Exception e){}
        }
    }

    public static String getWordBeforeDot(String filename) {
        //выделяем из файла типа "xxx.yyy" часть "xxx"
        String[] fn = filename.split("\\.");
        return fn[0];
    }

    public static Integer[] getIntegerArrayFromStringList(ArrayList<String> list) {
        // из списка строк получаем масив чисел
        ArrayList<Integer> listInteger = new ArrayList<>();//промежуточный список с числами
        for(int i = 0; i < list.size(); i++) {
            int l;
            try {
                l = Integer.parseInt(list.get(i));//проверка на возможность конвертации строки в число. данные,которые невозможно сконвертировать, не записваются
                listInteger.add(l);
            } catch (NumberFormatException e) {
                System.out.println(list.get(i) + " невозможно преобразовать в число");
            }
        }
        Integer[] arrayInteger = new Integer[listInteger.size()];// возвращаемый массив
        for(int i = 0; i < listInteger.size(); i++) {
            arrayInteger[i] = listInteger.get(i);
        }
        return arrayInteger;
    }

    public static String[] getStringArrayFromStringList(ArrayList<String> list) {
        // из списка строк получаем масив строк
        ArrayList<String> listStringsNotEmpty = new ArrayList<>();//промежуточный список с не пустыми строками
        for(int i = 0; i < list.size(); i++) {
            if (list.get(i).trim().length() != 0) {
                listStringsNotEmpty.add(list.get(i));//проверка на наличие пустых строк, пустые строки удаляются не записваются
            }
        }
        String[] array = new String[listStringsNotEmpty.size()];// возвращаемый массив
        for(int i = 0; i < listStringsNotEmpty.size(); i++) {
            array[i] = listStringsNotEmpty.get(i);
        }
        return array;
    }

    public static void deleteDir(File file) {
        //функция удаления папки, сначало удаляем все файлы из папки (при их налмчии), а потом и саму папку
        File[] contents = file.listFiles();
        if (contents != null) {
            for (File f : contents) {
                if (! Files.isSymbolicLink(f.toPath())) {
                    deleteDir(f);
                }
            }
        }
        file.delete();
    }
}