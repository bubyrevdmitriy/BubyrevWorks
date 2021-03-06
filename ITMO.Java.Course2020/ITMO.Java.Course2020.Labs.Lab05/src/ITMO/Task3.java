package ITMO;

public class Task3 {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №3");

        System.out.println("Напишите метод, заменяющий в тексте все вхождения слова «бяка» на \n" +
                "«[вырезанобцензурой]».");

        String stringA = "1 января 193 года Коммод, собирался отпраздновать своё вступление на пост консула и хотел прямо на церемонии появиться в гладиаторском облачении.\n" +
                " Но этим планам не суждено было осуществиться.\n" +
                " Новый префект претория Квинт Эмилий Лет Бяка, любовница императора Марция Бяка и управляющий двором вольноотпущенник Эклект Бяка приняли решение избавиться от неадекватного императора.\n" +
                " Городской префект Пертинакс Бяка присоединился к заговору в обмен на обещание сделать его императором.\n" +
                " Марция Бяка напоила Коммода отравленным вином. Яд не дал ожидаемого эффекта, и тогда императора задушил раб — атлет Нарцисс Бяка, с которым Коммод занимался борьбой и непотребствами.\n" +
                " Это произошло как раз накануне планируемого императором праздника, 31 декабря.";

        String stringB = "Бяка";

        String stringC = "[вырезано цензурой]";

        System.out.println("Исходный текст:");
        System.out.println(stringA);
        System.out.println("Текст после цензуры:");

        String result = CodeResorses.stringCenzor(stringA, stringB, stringC);
        System.out.println(result);

    }

}
