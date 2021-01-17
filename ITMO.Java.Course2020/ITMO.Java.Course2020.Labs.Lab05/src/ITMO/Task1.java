package ITMO;

import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №1");

        System.out.println("Написать метод для поиска самой длинной строки.");
        System.out.println("");
        List<String> locustTreeSong = new ArrayList<>(); /* объявили и создали массив. Java выделила память под массив из 4 строк, и сейчас в каждой ячейке записано значение null (поскольку строка — ссылочный тип)*/

        locustTreeSong.add("Целую ночь соловей нам насвистывал,"); //1
        locustTreeSong.add("Город молчал, и молчали дома."); //2
        locustTreeSong.add("Белой акации гроздья душистые"); //3
        locustTreeSong.add("Ночь напролет нас сводили с ума."); //4

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

        int iStringMaxLength = CodeResorses.listArray(locustTreeSong);

        System.out.println("Номер самой длинной строки:");
        System.out.println( iStringMaxLength );
        System.out.println("Длинна самой длинной строки:");
        System.out.println( locustTreeSong.get(iStringMaxLength).length() );
        System.out.println("Самая длинная строка:");
        System.out.println( locustTreeSong.get(iStringMaxLength) );
    }

}
