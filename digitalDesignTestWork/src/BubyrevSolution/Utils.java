package BubyrevSolution;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean isNumeric(String strNum)//функция проверяет, является ли переданное строковое значение
    {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean validateString(String stringTest) //валидируем исходную строку
    {
        boolean isStringTestValidated = false;
        isStringTestValidated = stringTest.matches("([0-9]*[\\[[a-z]\\]]*)*");//проверяем совпадает ли переданая нам строка с ожидаемым паттерном
        if (isStringTestValidated) {
            int countOpenBrackets=0;//количество открывающих скобок
            int countCloseBrackets=0;//количество закрывающих скобок
            String currentElement;//текущий элемент в переборе элементов
            Pattern p1 = Pattern.compile("((\\[)|(\\])){1}+");
            Matcher m1 = p1.matcher(stringTest);
            while(m1.find()){
                currentElement = m1.group();
                if (currentElement.equals("[")) {
                    countOpenBrackets++;
                }
                if (currentElement.equals("]")) {
                    countCloseBrackets++;
                    if (countCloseBrackets>countOpenBrackets) {
                        return false;
                    }
                }
            }

            if(countOpenBrackets!=countCloseBrackets) {//проверяем равное количество открывающих и закрывающих скобок в исходной строке
                return false;
            }

            Pattern p2 = Pattern.compile("[a-zA-Z]\\[.+?\\]");//ищем ошибки уровня "буква[...]" //t[123]y[456]
            Matcher m2 = p2.matcher(stringTest);
            while(m2.find()){
                return false;
            }
        }
        return isStringTestValidated;
    }

    public static int findNumberBeforeBrackets(String newSubString)// находим число перед группой [...]
    {
        int result=0;
        Pattern p1 = Pattern.compile("^([0-9]+)");//ищем числовую группу, стоящую на первой позиции в строке
        Matcher m1 = p1.matcher(newSubString);
        while(m1.find()){
            result = Integer.parseInt(m1.group());
        }
        return result;
    }

    public static String findLettersForMultiplying(String newSubString)// находим значение внутри скобок [...], если внутри блока нет еще одного блока [...]
    {
        String result="";
        Pattern p2 = Pattern.compile("([a-zA-Z]+)");//ищем буквенные значения внутри скобок [...]
        Matcher m2 = p2.matcher(newSubString);
        while(m2.find()){
            result = m2.group();
        }
        return result;
    }

    public static String findLettersForRecursion(String newSubString)// находим значение внутри скобок [...], если внутри блока есть блок/блоки [...]
    {
        String result="";
        Pattern p2 = Pattern.compile("\\[.+\\]");//ищем блок букв и элементов [...] внутри главных скобок [...] жадный алгоритм
        Matcher m2 = p2.matcher(newSubString);
        while(m2.find()){
            result = m2.group().substring(1);//убираем открывающую скобку, стоящую на первой позиции
        }
        return result;
    }

    public static String multipleString(String stringTest)//функция раскрытия скобок исходной строки
    {
        List<String> listOfElements = new ArrayList<>();//составляем лист из элементов переданной строки
        Pattern p = Pattern.compile("([a-zA-Z]+)|([0-9]+)|(\\[)|(\\])");//разбиваем переданную строку на группы: числа, буквы, скобки
        Matcher m = p.matcher(stringTest);
        while(m.find()){
            listOfElements.add(m.group());
        }
        String currentElement; //текущий элемент в переборе элементов
        boolean isOpenBrackets = false; //открыты ли скобки в данный момент
        int countOpenBrackets=0;//количество открывающих скобок
        int countCloseBrackets=0;//количество закрывающих скобок
        int periodStart=0;//данаая переменная необходима для сбора из отдельных элементов новых подстрочек, определяет первый элемент, с которого начнется вставка элементов listOfElements для в подстроку
        int periodStop=0;//данаая переменная необходима для сбора из отдельных элементов новых подстрочек, определяет крайний элемент из списка listOfElements для вставки в подстроку
        String newSubString = "";//подстрока, которую будем выделять из базовой строки
        String stringResult = "";//результирующая строка, которую возвращает функция
        for (int i = 0; i < listOfElements.size(); i++) {
            currentElement = listOfElements.get(i);
            if (currentElement.equals("[") || currentElement.equals("]")) {
                if (currentElement.equals("[")) {
                    isOpenBrackets = true;
                    countOpenBrackets++;
                }
                if (currentElement.equals("]")) {
                    countCloseBrackets++;
                    if (countOpenBrackets == countCloseBrackets) {
                        isOpenBrackets = false;
                        periodStop = i;
                        for (int x = periodStart; x <= periodStop; x++) {
                            newSubString = newSubString + listOfElements.get(x);//собираем подстроку из элементов
                        }
                        if (countOpenBrackets>1) {
                            int countToMultiple = findNumberBeforeBrackets(newSubString);
                            String lettersToMultiple = findLettersForRecursion(newSubString);
                            String subStringResult = "";
                            for (int z = 0; z < countToMultiple; z++) {
                                subStringResult = subStringResult + multipleString(lettersToMultiple);//вызов рекурсии и умножение строки
                            }
                            stringResult = stringResult + subStringResult;//добавляем подстроку к строке-результату
                        } else {
                            int countToMultiple = findNumberBeforeBrackets(newSubString);
                            String lettersToMultiple = findLettersForMultiplying(newSubString);
                            String subStringResult = "";
                            for (int z = 0; z < countToMultiple; z++) {
                                subStringResult = subStringResult + lettersToMultiple;//умножаем строку
                            }
                            stringResult = stringResult + subStringResult;//добавляем подстроку к строке-результату
                        }
                        newSubString="";
                        countOpenBrackets = 0;
                        countCloseBrackets = 0;
                        periodStart = periodStop + 1;
                    }
                }
            } else {
                if (!isNumeric(currentElement) && !isOpenBrackets) {
                    stringResult = stringResult + currentElement;//добавляем элемент к строке-результату
                    periodStart = i + 1;
                }
            }
        }
        return stringResult;
    }
}
