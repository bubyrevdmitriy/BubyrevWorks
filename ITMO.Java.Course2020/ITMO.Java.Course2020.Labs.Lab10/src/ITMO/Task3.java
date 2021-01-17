package ITMO;

import ITMO.Services.CommonResource;
import ITMO.Services.CountThread;
import ITMO.Services.CountThreadTask3;
import ITMO.Services.CounterThread;
import org.w3c.dom.css.Counter;

public class Task3 {
    public static void main(String[] args) {

        System.out.println("Задание №3");
        System.out.println("Дан класс:" +
                "Напишите программу, в которой запускается 100 потоков, каждый из которых\n" +
                "вызывает метод increment() 1000 раз.\n" +
                "После того, как потоки завершат работу, проверьте, чему равен count .\n" +
                "Если обнаружилась проблема, предложите ее решение.");
        try {
            CommonResource commonResource= new CommonResource();
            //CounterThread counterThread=  new CounterThread(0);
            int arrayLong = 100;
            Thread[] threadArray = new Thread[arrayLong];
            for (int i = 0; i < threadArray.length; i++) {
                threadArray[i]= new Thread(new CountThreadTask3(commonResource));
            }

            for (int i = 0; i < threadArray.length; i++) {
                threadArray[i].run();
            }

            System.out.println("Потоки завершены");
            System.out.println(commonResource.getX());

        }
        catch(Exception e) {
            System.out.println("Ошибка ввода данных");
        }
    }
}
