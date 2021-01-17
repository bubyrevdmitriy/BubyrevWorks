package ITMO;

import ITMO.CompratorClasses.ComparatorAlthabet;
import ITMO.CompratorClasses.ComparatorLength;
import ITMO.CompratorClasses.ComparatorNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static ITMO.CodeResourses.printArrayString;

public class Task3 {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №3");

        System.out.println("Дан массив строк (с консоли, или через параметры командной строки). Выведете\n" +
                "их все разными способами:\n" +
                "По алфавиту\n" +
                "По возрастанию длины\n" +
                "По количеству вхождений цифр в строку\n" +
                "* Для сортировка используется Arrays.sort() с параметром Comparator.\n" +
                "Для вывода строк из массива можно использовать Arrays.toString().");
        System.out.println("");

        List<String> locustTreeSong = new ArrayList<>(); /* объявили и создали массив. Java выделила память под массив из 4 строк, и сейчас в каждой ячейке записано значение null (поскольку строка — ссылочный тип)*/

        locustTreeSong.add("Целую ночь соловей нам насвистывал,1"); //1
        locustTreeSong.add("Город молчал, и молчали дома.11"); //2
        locustTreeSong.add("Белой акации гроздья душистые11"); //3
        locustTreeSong.add("Ночь напролет нас сводили с ума.111"); //4

        locustTreeSong.add("Сад весь умыт был весенними ливнями,"); //5
        locustTreeSong.add("В темных оврагах стояла вода."); //6
        locustTreeSong.add("Боже, какими мы были наивными!"); //7
        locustTreeSong.add("Как же мы молоды были тогда!"); //8

        locustTreeSong.add("Годы промчались, седыми нас делая."); //9
        locustTreeSong.add("Где чистота этих веток живых?"); //10
        locustTreeSong.add("Только зима, да метель эта белая,"); //11
        locustTreeSong.add("Напоминают сегодня о них."); //12

        locustTreeSong.add("В час, когда ветер бушует неистово,"); //13
        locustTreeSong.add("С новою силою чувствую я:"); //14
        locustTreeSong.add("Белой акации гроздья душистые"); //15
        locustTreeSong.add("Невозвратимы, как юность моя."); //16

        System.out.println("");

        for (int i = 0; i < locustTreeSong.size(); i++) {
            String temp = locustTreeSong.get(i);
            System.out.println(temp);
        }
        System.out.println("");


        System.out.println("Вывод массива строк по алфавиту______________________");
        //ArrayList<String> locustTreeSong1=locustTreeSong;
        String[] locustTreeSong1 = locustTreeSong.stream().toArray(String[]::new);
        Arrays.sort(locustTreeSong1, new ComparatorAlthabet());
        printArrayString(locustTreeSong1);
        System.out.println("");



        System.out.println("Вывод массива строк по возрастанию длины_________________________");
        //Array<String> locustTreeSong2 = new Array<String>(locustTreeSong);
        String[] locustTreeSong2 = locustTreeSong.stream().toArray(String[]::new);
        Arrays.sort(locustTreeSong2, new ComparatorLength());

        printArrayString(locustTreeSong2);
        System.out.println("");



        System.out.println("Вывод массива строк по количеству вхождений цифр в строку_______________");
        String[] locustTreeSong3 = locustTreeSong.stream().toArray(String[]::new);

        Arrays.sort(locustTreeSong3, new ComparatorNumbers());
        printArrayString(locustTreeSong3);


    }
}
