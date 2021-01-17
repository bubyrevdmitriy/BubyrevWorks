package ITMO;

import ITMO.Services.CommonResource;
import ITMO.Services.CountThread;
import ITMO.Services.CountThreadState;

public class Task2 {
    public static void main(String[] args) {

        System.out.println("Задание №2");
        System.out.println("Выведете состояние потока перед его запуском, после запуска и во время\n" +
                "выполнения.");
        CommonResource commonResource = new CommonResource();

        Thread t = new Thread (new CountThread(commonResource));
        t.setName("Thread "+ 0);
        System.out.println(t.getState());
        t.start();

        CountThreadState countThreadState = new CountThreadState(commonResource);
        //countThreadState.setName("listener "+ 0);
        countThreadState.start(t);


        //System.out.println(t.getState());
        /*Thread t1 = new Thread (new CountThread(commonResource));
        t1.setName("Thread "+ 1);
        t1.start();*/

    }
}



