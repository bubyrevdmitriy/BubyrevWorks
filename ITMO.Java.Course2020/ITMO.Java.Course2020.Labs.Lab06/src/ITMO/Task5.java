package ITMO;



public class Task5 {
    public static void main(String[] args) {

        System.out.println("Задание №5");

        System.out.println("** Написать Observable StringBuilder. Для этого понадобится абстрактный класс\n" +
                "OnChangeListener c методом onChange(StringBuilder sb), который вызывается\n" +
                "внутри собственного класса-наследника StringBuilder если тот изменился.");

        String string1 = "aaa";
        String string2 = "bbb";
        String string3 = "ccc";

        StringBuilder stringBuilder1 = new StringBuilder(string1);
        //StringBuilder(string1).addC;
        stringBuilder1.append(", ");
        stringBuilder1.append(string2);
        stringBuilder1.append(", ");
        stringBuilder1.append(string3);



    }
}
