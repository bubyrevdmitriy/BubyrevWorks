package BubyrevSolution;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static BubyrevSolution.Utils.*;

public class Main {

    public static void main(String[] args) {
        // информация о требованиях тестового задания
        /*System.out.println("Условие:\n " +
                "Написать на Java программу распаковывания строки. На вход поступает строка вида\n " +
                "число[строка], на выход - строка, содержащая повторяющиеся подстроки.");
        System.out.println("Пример: \n " +
                "Вход: 3[xyz]4[xy]z\n " +
                "Выход: xyzxyzxyzxyxyxyxyz");
        System.out.println("Ограничения: \n " +
                "- одно повторение может содержать другое. Например: 2[3[x]y]  = xxxyxxxy\n " +
                "- допустимые символы на вход: латинские буквы, числа и скобки []\n " +
                "- числа означают только число повторений\n " +
                "- скобки только для обозначения повторяющихся подстрок\n " +
                "- входная строка всегда валидна.");*/
        //Создаем тестовые строки
        System.out.println("Тестовые строки(проходят валидацию): \n " +
                " 12[xyz], 3[xyz]4[xy], 3[xyz]11[xy]b, 3[xyz]a4[xy]b, 3[xyz]0[rt]b, 2[3[x]y]3[xyz], 2[3[a]b]c3[d]4[efgh]i, 2[3[a2[tt]]b]c3[d]4[efgh]i");
        System.out.println("Тестовые строки(не проходят валидацию): \n " +
                " 3[абв]4[гд]е, 3[x[[yz]0[xy]b, 3[x[[yz]0[xy]b, .[3[//]y], 2[3||[x]..y]3[xyz]**4[xy]b, t[123]y[456]");

        try {
            //запрашиваем строку у пользователя
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите строку для преобразования:");
            String stringTest = reader.readLine();
            boolean isStringTestValidated = validateString(stringTest);//запускаем функцию валидации исходной строки
            System.out.printf("К работе принята строка: %s%n", stringTest);
            if (isStringTestValidated) {
                //блок в случае удачной валидации программы
                System.out.println("Строка успешно провалидирована, приступаем к извлечению данных!");
                String stringAfterMultiple = multipleString(stringTest);//запускаем функцию, которая, при необходимости, работает в рекурсивном режиме
                System.out.println("Результат после раскрытия скобок:");
                System.out.println(stringAfterMultiple);
            } else {
                //выводим сообщение в случае неудачной валидации программы
                System.out.printf("К сожелению строка: %s  не прошла валидацию. Попробуйте ввести другую строку.%n", stringTest);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
