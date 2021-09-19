package SortIt;

import java.io.*;
import java.util.ArrayList;

import static SortIt.FileSortUtils.*;
import static SortIt.MergeSortForIntegersArray.mergeSortIntegers;

public class FileSortInteger {
    //класс содержит методы для проверки числового файла на отсортированность и сортировки неотсоритрованного числового файла
    //массивность методов вызвана необходимостью предусмотреть работу с файлами, целиком неп помещающимися в оперативную память, в таком случае происходит:
    //разделение большого файла на много небольших, сортировка содержимого небольших файлов. Сортировка слиянием небольших файлов обратно в общий файл

    public static void sortFileInteger(String inputFileName, String condition, String tempDirectoryName) {
        //метод для отсортировки содержимого неотсортированногострокового файла

        //максимальное количество строк в списке массиве/списке, который хранится в оперативной памяти,
        //при превышении количества строк происходит запись содержимого во временный файл и обнуление счетчика
        int maxChunkSize = 2000;

        try {
            //создаем врееменную папку, присваиваем ей уникальное название
            String subTempDirectoryName = getWordBeforeDot(inputFileName) + "_" + Math.round(1000000 * Math.random());
            File theDir = new File(tempDirectoryName + subTempDirectoryName);
            if (!theDir.exists()) {
                theDir.mkdirs();
            }

            ArrayList<String> firstTempFileNames = new ArrayList<>();
            BufferedReader inputReader = null;
            ArrayList<String> lines = new ArrayList<String>();//список считанных из файла строк

            try {
                inputReader = new BufferedReader(new FileReader(inputFileName));
                String line = null;
                //счетчик количества строк в списке lines, при достижения этим параметром предельного значения происходит сортировка содержимого и запись во временный файл
                int currChunkSize = 0;

                while ((line = inputReader.readLine()) != null) {
                    lines.add(line);//записываем строку в список
                    currChunkSize += line.length() + 1;//увеличиваем счетчик чтрок в списке

                    if (currChunkSize >= maxChunkSize) {
                        currChunkSize = 0;
                        Integer[] array = getIntegerArrayFromStringList(lines);//создание массива из списка
                        mergeSortIntegers(array, array.length, condition);//сортировка массива
                        String fileName = Math.round(1000000 * Math.random()) + ".txt";//создаем врееменный файл, присваиваем ему уникальное название
                        firstTempFileNames.add(fileName);
                        writeOut(array, fileName, tempDirectoryName, subTempDirectoryName);
                        lines.clear(); //очистка временного списка считанных из файла строк
                    }
                }

                Integer[] array = getIntegerArrayFromStringList(lines);//создание массива из списка
                mergeSortIntegers(array, array.length, condition);//сортировка массива

                // проверка, сохранили ли мы что то в папку, может и не надо ничего записывать в файл
                if (firstTempFileNames.size()>0) {
                    //если сортируемый файл оказался большим и мы разделили его на несколько частей, то дописваем в папку с временными файлами список lines
                    String fileName = Math.round(1000000 * Math.random()) + ".txt";//создаем врееменный файл, присваиваем ему уникальное название
                    firstTempFileNames.add(fileName);
                    writeOut(array, fileName, tempDirectoryName, subTempDirectoryName);//записваем информацию во временную папку
                    lines.clear();//очистка временного списка считанных из файла строк

                    String[] arrayOfFirstDividedFileNames = getStringArrayFromStringList(firstTempFileNames);//получаем строковый массив названий временных файлов
                    String resultFileName = mergeSortFilesInteger(arrayOfFirstDividedFileNames, tempDirectoryName, subTempDirectoryName, condition);//вызываем метод сортировки файлов слиянием

                    writeResultSortingFileToInputFile(tempDirectoryName, subTempDirectoryName, resultFileName, inputFileName);//возвращаем значение отсортированного массива в исходный файл
                } else {
                    //если сортируемый файл оказался небольшим, и целиком поместился в оперативную память, то записываем отсортированные значения в исходный файл
                    writeResultSortingArrayToInputFile(array, inputFileName);
                }
            } catch (IOException io) {
                throw io;
            } finally {
                deleteDir(theDir);//удаляем папку

                if (inputReader != null) try {
                    inputReader.close();
                } catch (Exception e) {
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static String mergeSortFilesInteger(String[] a, String tempDirectoryName, String subTempDirectoryName, String condition) {
        //метод для сортировки объединением числовых файлов
        // проверяем не 1 ли элемент в массиве?
        if (a.length == 1) {
            return a[0];
        }
        int mid = a.length / 2;// находим средний элемент
        String[] l = new String[mid];// создаем массив для левой части переданного в метод массива
        String[] r = new String[a.length - mid];// создаем массив для правой части переданного в метод массива

        // копируем левую часть от начала до середины
        for (int i = 0; i < mid; i++) {
            l[i] = a[i];
        }
        // копируем правую часть от середины до конца массива
        for (int i = mid; i < a.length; i++) {
            r[i - mid] = a[i];
        }
        String lStringFileName = mergeSortFilesInteger(l, tempDirectoryName, subTempDirectoryName, condition); // рекурсивно запускаем метод с левым массивом
        String rStringFileName = mergeSortFilesInteger(r, tempDirectoryName, subTempDirectoryName, condition); // рекурсивно запускаем метод с правым массивом

        //объединяем содержимое 2 файлов в 1
        return mergeFilesIntegers(lStringFileName, rStringFileName, tempDirectoryName, subTempDirectoryName, condition);
    }

    private static String mergeFilesIntegers(String fileName1, String fileName2, String tempDirectoryName, String subTempDirectoryName, String condition) {
        //метод для переноса содержимого из 2 файлов в один
        File fileInput1 = new File(tempDirectoryName + subTempDirectoryName + "/" + fileName1);
        File fileInput2 = new File(tempDirectoryName + subTempDirectoryName + "/" + fileName2);

        String fileName3 = Math.round(1000000 * Math.random()) + ".txt";//создаем врееменный файл, присваиваем ему уникальное название
        File fileOutput = new File(tempDirectoryName + subTempDirectoryName + "/" + fileName3);

        BufferedReader reader1 = null;
        BufferedReader reader2 = null;
        BufferedWriter writer = null;
        try {
            reader1 = new BufferedReader(new FileReader(fileInput1));
            reader2 = new BufferedReader(new FileReader(fileInput2));
            writer = new BufferedWriter(new FileWriter(fileOutput));

            String line1 = reader1.readLine();
            String line2 = reader2.readLine();
            int pausedReader = 0;
            String previousValue = null;
            while (line1 != null && line2 != null) {
                if (condition.equals("ascending")) { //объединение содержимого файлов по возрастанию
                    if (pausedReader == 0) {
                        if (Integer.parseInt(line1) < Integer.parseInt(line2)) {
                            writer.write(line1 + "\n");
                            line1 = reader1.readLine();
                            previousValue = line2;
                            pausedReader = 2;
                        } else if (Integer.parseInt(line1) > Integer.parseInt(line2)) {
                            writer.write(line2 + "\n");
                            line2 = reader2.readLine();
                            previousValue = line1;
                            pausedReader = 1;
                        } else {
                            writer.write(line1 + "\n");
                            line1 = reader1.readLine();
                            writer.write(line2 + "\n");
                            line2 = reader2.readLine();
                        }
                    } else if (pausedReader == 1) {
                        if (Integer.parseInt(line2) < Integer.parseInt(previousValue)) {
                            writer.write(line2 + "\n");
                            line2 = reader2.readLine();
                        } else if (Integer.parseInt(line2) > Integer.parseInt(previousValue)) {
                            writer.write(previousValue + "\n");
                            previousValue = line2;
                            pausedReader = 2;
                            line1 = reader1.readLine();
                        } else {
                            writer.write(line2 + "\n");
                            writer.write(previousValue + "\n");
                            previousValue = null;
                            pausedReader = 0;
                            line1 = reader1.readLine();
                            line2 = reader2.readLine();
                        }
                    } else {
                        if (Integer.parseInt(line1) < Integer.parseInt(previousValue)) {
                            writer.write(line1 + "\n");
                            line1 = reader1.readLine();
                        } else if (Integer.parseInt(line1) > Integer.parseInt(previousValue)) {
                            writer.write(previousValue + "\n");
                            previousValue = line1;
                            pausedReader = 1;
                            line2 = reader2.readLine();
                        } else {
                            writer.write(line1 + "\n");
                            writer.write(previousValue + "\n");
                            previousValue = null;
                            pausedReader = 0;
                            line1 = reader1.readLine();
                            line2 = reader2.readLine();
                        }
                    }
                }
                if (condition.equals("descending")) { //объединение содержимого файлов по убыванию
                    if (pausedReader == 0) {
                        if (Integer.parseInt(line1) > Integer.parseInt(line2)) {
                            writer.write(line1 + "\n");
                            line1 = reader1.readLine();
                            previousValue = line2;
                            pausedReader = 2;
                        } else if (Integer.parseInt(line1) < Integer.parseInt(line2)) {
                            writer.write(line2 + "\n");
                            line2 = reader2.readLine();
                            previousValue = line1;
                            pausedReader = 1;
                        } else {
                            writer.write(line1 + "\n");
                            line1 = reader1.readLine();
                            writer.write(line2 + "\n");
                            line2 = reader2.readLine();
                        }
                    } else if (pausedReader == 1) {
                        if (Integer.parseInt(line2) > Integer.parseInt(previousValue)) {
                            writer.write(line2 + "\n");
                            line2 = reader2.readLine();
                        } else if (Integer.parseInt(line2) < Integer.parseInt(previousValue)) {
                            writer.write(previousValue + "\n");
                            previousValue = line2;
                            pausedReader = 2;
                            line1 = reader1.readLine();
                        } else {
                            writer.write(line2 + "\n");
                            writer.write(previousValue + "\n");
                            previousValue = null;
                            pausedReader = 0;
                            line1 = reader1.readLine();
                            line2 = reader2.readLine();
                        }
                    } else {
                        if (Integer.parseInt(line1) > Integer.parseInt(previousValue)) {
                            writer.write(line1 + "\n");
                            line1 = reader1.readLine();
                        } else if (Integer.parseInt(line1) < Integer.parseInt(previousValue)) {
                            writer.write(previousValue + "\n");
                            previousValue = line1;
                            pausedReader = 1;
                            line2 = reader2.readLine();
                        } else {
                            writer.write(line1 + "\n");
                            writer.write(previousValue + "\n");
                            previousValue = null;
                            pausedReader = 0;
                            line1 = reader1.readLine();
                            line2 = reader2.readLine();
                        }
                    }
                }
            }
            while (line1 != null) {
                writer.write(line1 + "\n");
                line1 = reader1.readLine();
            }

            while (line2 != null) {
                writer.write(line2 + "\n");
                line2 = reader2.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            fileInput1.delete();
            fileInput2.delete();
            if (reader1 != null) try {
                reader1.close();
            } catch (Exception e) {
            }
            if (reader2 != null) try {
                reader2.close();
            } catch (Exception e) {
            }
            if (writer != null) try {
                writer.close();
            } catch (Exception e) {
            }
        }
        return fileName3;
    }

    public static boolean isFileIntegerSorted(String inputFileName, String condition) {
        //проверка числового файла на отсортированность
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(inputFileName));
            if (condition.equals("ascending")) {
                //провека на отсортированность по возрастанию
                String line = reader.readLine();
                int minValue = Integer.parseInt(line);
                while(line!=null) {
                    if (Integer.parseInt(line) >= minValue) {
                        minValue = Integer.parseInt(line);
                        line = reader.readLine();
                    } else {
                        return false;
                    }
                }
            }
            if (condition.equals("descending")) {
                //провека на отсортированность по убыванию
                String line = reader.readLine();
                int maxValue = Integer.parseInt(line);
                while(line!=null) {
                    if (Integer.parseInt(line) <= maxValue) {
                        maxValue = Integer.parseInt(line);
                        line = reader.readLine();
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
        catch (Exception e) {
            //e.printStackTrace();
            return false;
        } finally{
            if ( reader != null )try{reader.close();}catch(Exception e){}
        }
    }
}