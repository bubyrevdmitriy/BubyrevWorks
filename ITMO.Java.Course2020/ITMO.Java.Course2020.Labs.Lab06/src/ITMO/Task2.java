package ITMO;

public class Task2 {
    public static void main(String[] args) {

        System.out.println("Задание №2");
        System.out.println("Перепишите метод, проверяющий слово на палиндромность с помощью\n" +
                "Stringbuilder. В таком методе должно быть 1-3 строки.");

        String string1 = "tenet";
        String string2 = "sator";

        System.out.println("Первое слово: " + string1);
        System.out.println("Второе слово: " + string2);

        System.out.println("Является ли первое слово палиндромом:");
        StringBuilder stringBuilder1 = new StringBuilder(string1);
        boolean string1Result=CodeResourses.polindrom(stringBuilder1);
        System.out.println(string1Result);

        System.out.println("Является ли второе слово палиндромом:");
        StringBuilder stringBuilder2 = new StringBuilder(string2);
        boolean string2Result=CodeResourses.polindrom(stringBuilder2);

        System.out.println(string2Result);

    }
}
