package SortIt;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import static SortIt.FileSortInteger.*;
import static SortIt.FileSortString.*;
import static SortIt.FileSortUtils.*;

public class Main {
    //основной класс программы, соержит методы для приема, валидации и обработки пользовательского запроса

    public static String tempDirectoryName = "/tempDirectoryForMergeSortingFiles/";

    public static void main(String[] args) {
        System.out.println("Сортировка вайлов слиянием");
        System.out.println("1. режим сортировки (-a или -d), необязательный, по умолчанию сортируем по возрастанию;\n" +
                "2. тип данных (-s или -i), обязательный;\n" +
                "3. имя выходного файла, обязательное;\n" +
                "4. остальные параметры – имена входных файлов, не менее одного.\n" +
                "Примеры запуска из командной строки для Windows:\n" +
                "sort-it.exe -i file.txt file1.txt file11.txt file111.txt (для целых чисел по возрастанию)\n" +
                "sort-it.exe -i file.txt file1.txt file11.txt file111.txt (для строк по возрастанию)\n" +
                "sort-it.exe -d -s file.txt file1.txt file11.txt file111.txt (для строк по убыванию)");

        try {
            //создаем объект, храняший исходные данные для сортировки, режимы сортировки, название файлов
            SortSettings sortSettings = takeStringFromUserAndCreateSortSettings();

            String condition = null; // условие сортировки возрастание/убывание
            if (sortSettings.isSortAscending()) condition = "ascending";// сортировка по возрастанию
            if (sortSettings.isSortDescending()) condition = "descending";// сортировка по убыванию


            if (sortSettings.isSortIntegers()) {
                //сортируем числа
                System.out.println("Приступаем к сортировке числовых файлов!");
                String[] inputFileNames = sortSettings.getInputFileNames();//массив с названиями исходных файлов
                for(int i = 0; i < inputFileNames.length; i++) {
                    boolean isFileSorted = isFileIntegerSorted(inputFileNames[i], condition);//проверяем отсортированы ли данные в исходных файлах
                    if (!isFileSorted) {
                        System.out.println("Файл: " + inputFileNames[i] + " не отсортирован. Сортировка будет произведена в автоматическом режиме!");
                        sortFileInteger(inputFileNames[i], condition, tempDirectoryName);//сортируем данные внутри неотсортированного файла
                    }
                }
                if (inputFileNames.length == 1) {
                    //если присутствует только 1 входящий файл, то переписываем его в файл-результат
                    writeResultSortingFileToInputFile(" ", " ", inputFileNames[0], sortSettings.getOutPutFileName());
                } else{
                    //Создание временной папки на диске, внутри которой будет происходить объединение слиянием исходных файлов и их производных;
                    String subTempDirectoryName = "forSortingIntoFile" + getWordBeforeDot(sortSettings.getOutPutFileName()) + "_" + Math.round(1000000 * Math.random());
                    File theDir = new File(tempDirectoryName + subTempDirectoryName);
                    if (!theDir.exists()) {
                        theDir.mkdirs();
                    }
                    //копирование во временную папку исходных файлов
                    for(int i = 0; i < inputFileNames.length; i++) {
                        copyFilesToTempDir(tempDirectoryName, subTempDirectoryName, inputFileNames[i]);
                    }
                    //сортировка слиянием числовых файлов
                    String resultFileName = mergeSortFilesInteger(inputFileNames, tempDirectoryName, subTempDirectoryName, condition);
                    //переписываем результат сортировки в файл-результат
                    writeResultSortingFileToInputFile(tempDirectoryName, subTempDirectoryName, resultFileName, sortSettings.getOutPutFileName());
                    deleteDir(theDir);//удаление временной папки на диске
                    System.out.println("Сортировка завершена!");
                }
            }

            if (sortSettings.isSortStrings()) {
                //сортируем строки
                System.out.println("Приступаем к сортировке строковх файлов!");
                String[] inputFileNames = sortSettings.getInputFileNames();//массив с названиями исходных файлов
                for(int i = 0; i < inputFileNames.length; i++) {
                    boolean isFileSorted = isFileStringSorted(inputFileNames[i], condition);//проверяем отсортированы ли данные в исходных файлах
                    if (!isFileSorted) {
                        System.out.println("Файл: " + inputFileNames[i] + " не отсортирован. Сортировка будет произведена в автоматическом режиме!");
                        sortFileString(inputFileNames[i], condition, tempDirectoryName);//сортируем данные внутри неотсортированного файла
                    }
                }
                if (inputFileNames.length == 1) {
                    //если присутствует только 1 входящий файл, то переписываем его в файл-результат
                    writeResultSortingFileToInputFile(" ", " ", inputFileNames[0], sortSettings.getOutPutFileName());
                } else{
                    //Создание временной папки на диске, внутри которой будет происходить объединение слиянием исходных файлов и их производных;
                    String subTempDirectoryName = "forSortingIntoFile" + getWordBeforeDot(sortSettings.getOutPutFileName()) + "_" + Math.round(1000000 * Math.random());
                    File theDir = new File(tempDirectoryName + subTempDirectoryName);
                    if (!theDir.exists()) {
                        theDir.mkdirs();
                    }
                    //копирование во временную папку исходных файлов
                    for(int i = 0; i < inputFileNames.length; i++) {
                        copyFilesToTempDir(tempDirectoryName, subTempDirectoryName, inputFileNames[i]);
                    }
                    //сортировка слиянием строковых файлов
                    String resultFileName = mergeSortFilesString(inputFileNames, tempDirectoryName, subTempDirectoryName, condition);
                    //переписываем результат сортировки в файл-результат
                    writeResultSortingFileToInputFile(tempDirectoryName, subTempDirectoryName, resultFileName, sortSettings.getOutPutFileName());
                    deleteDir(theDir);//удаление временной папки на диске
                    System.out.println("Сортировка завершена!");
                }
            }
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("к сожелению, произошла непредвиденная ошибка, попробуйте еще раз");
        }
    }

    private static SortSettings takeStringFromUserAndCreateSortSettings() {
        //метод для считывания у пользователя строки, и получения из строки данных для сортировки
        BufferedReader userScreenReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите строку с данными для сортировки:");
        SortSettings sortSettings = new SortSettings();
        try {
            String inputString;
            inputString = userScreenReader.readLine();//считываем строку у пользователя
            while(!validateString(inputString)) {
                //цикл будет повторяться, пока строка, полученная от пользователя, не пройдет валидацию
                System.out.println("Введенная строка содержит некорректные данные, пожалуйста, попробуйте еще раз!");
                inputString = userScreenReader.readLine();
            }
            String[] subStr = getStringArrayFromStringDivideByVoid(inputString);// разделяем строку, полученную от пользователя, на массив строк

            if(subStr[1].equals("-a") || subStr[2].equals("-a")) {
                sortSettings.setSortAscending(true);// режим сортировки - по возрастанию
            }
            if(subStr[1].equals("-d") || subStr[2].equals("-d")) {
                sortSettings.setSortDescending(true);// режим сортировки - по убыванию
            }
            if(subStr[1].equals("-s") || subStr[2].equals("-s")) {
                sortSettings.setSortStrings(true);// сортируем строки
            }
            if(subStr[1].equals("-i") || subStr[2].equals("-i")) {
                sortSettings.setSortIntegers(true);// сортируем числа
            }
            //список из исходных названий файлов с информацией, полученных от пользователя, до их проверки
            ArrayList<String> inputFileListBeforeChecking = new ArrayList<>();
            //список из исходных названий файлов с информацией, полученных от пользователя, после их проверки
            ArrayList<String> inputFileListAfterChecking = new ArrayList<>();

            if (subStr[2].equals("-a") || subStr[2].equals("-d") || subStr[2].equals("-s") || subStr[2].equals("-i")) {
                sortSettings.setOutPutFileName(subStr[3]);
                inputFileListBeforeChecking.addAll(Arrays.asList(subStr).subList(4, subStr.length));
            } else {
                sortSettings.setOutPutFileName(subStr[2]);
                inputFileListBeforeChecking.addAll(Arrays.asList(subStr).subList(3, subStr.length));
            }

            File file;
            //провера, все ли имена файлов, указанные пользователем, действительно являются файлами
            for(int i = 0; i < inputFileListBeforeChecking.size(); i++) {
                file = new File(inputFileListBeforeChecking.get(i));
                if(file.exists() && !file.isDirectory()) {
                    inputFileListAfterChecking.add(inputFileListBeforeChecking.get(i));//если такой файл имеется, то записываем его в список
                } else {
                    System.out.println("Невозможно найти файл с именем: " + inputFileListBeforeChecking.get(i));//если такого файла нет, то в список не заносим, показываем предупреждение
                }
            }
            sortSettings.setInputFileNames(getStringArrayFromStringList(inputFileListAfterChecking));

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            if (userScreenReader != null) try {
                userScreenReader.close();
            } catch (Exception e) {
            }
        }
        return sortSettings;
    }

    private static boolean validateString(String stringTest) {
        //валидация строки, полученной от польхователя
        try {
            boolean containOutputFile = false;//наличие  выходного файла
            boolean containInputFiles = false;// наличие списка исходных файлов

            String outPutFileName;//имя выходного файла
            //список из исходных названий файлов с информацией, полученных от пользователя, до их проверки
            ArrayList<String> inputFileNamesBeforeChecking = new ArrayList<String>();
            //список из исходных названий файлов с информацией, полученных от пользователя, после их проверки
            ArrayList<String> inputFileListAfterChecking = new ArrayList<>();

            String[] subStr = getStringArrayFromStringDivideByVoid(stringTest);// разделяем строку, полученную от пользователя, на массив строк

            if(subStr[0].equals("sort-it.exe")) {
                //проверяем есть ли в строке, переданной пользователем, обязательного поля с указанием типа данных
                if(subStr[1].equals("-s") || subStr[2].equals("-s") || subStr[1].equals("-i") || subStr[2].equals("-i")) {

                    if (subStr[2].equals("-a") || subStr[2].equals("-d") || subStr[2].equals("-s") || subStr[2].equals("-i")) {
                        outPutFileName = subStr[3];
                        inputFileNamesBeforeChecking.addAll(Arrays.asList(subStr).subList(4, subStr.length));
                    } else {
                        outPutFileName = subStr[2];
                        inputFileNamesBeforeChecking.addAll(Arrays.asList(subStr).subList(3, subStr.length));
                    }

                    String pattern =  "^[\\w,\\s-]+\\.[A-Za-z]{3}$";// паттерн для проверки правильности указания имени выходного файла
                    containOutputFile = outPutFileName.matches(pattern);//проверяем, согласуется ли полученное имя файла с паттерном

                    File file;
                    //провера, все ли имена файлов, указанные пользователем, действительно являются файлами
                    for(int i = 0; i < inputFileNamesBeforeChecking.size(); i++) {
                        file = new File(inputFileNamesBeforeChecking.get(i));
                        if(file.exists() && !file.isDirectory()) {
                            inputFileListAfterChecking.add(inputFileNamesBeforeChecking.get(i));//если такой файл имеется, то записываем его в список
                        } else {
                        }
                    }

                    if (inputFileListAfterChecking.size()>0) {
                        containInputFiles = true;//если хоть один файл из списка файлов, переданных пользователем, существует, то все нормально
                    }

                    if (containOutputFile && containInputFiles) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static String[] getStringArrayFromStringDivideByVoid(String string) {
        // разделяем строку, полученную от пользователя, на массив строк
        String[] subStr;
        String delimiter = " "; // Разделитель
        subStr = string.split(delimiter); // Разделения строки str с помощью метода split()
        return subStr;
    }
}