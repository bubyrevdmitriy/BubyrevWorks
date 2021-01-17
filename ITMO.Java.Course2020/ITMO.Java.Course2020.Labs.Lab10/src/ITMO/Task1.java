package ITMO;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Task1 {

    public static volatile List<Thread> list = new ArrayList<Thread>(5);

    public static void main(String[] args) {

        System.out.println("Задание №1");
        System.out.println("Напишите программу, в которой запускается 10 потоков и каждый из них выводит\n" +
                "числа от 0 до 100.");
        try {

            SpecialThread[] SpecialThreadArray = new SpecialThread[10];
            for (int i = 1; i < 10; i++) {
                SpecialThreadArray[i]= new SpecialThread();
            }

            for (int i = 1; i < 10; i++) {
                SpecialThreadArray[i].run();
            }

            //thread1.run();
            //thread2.run();

        }
        catch(Exception e) {
            System.out.println("Ошибка ввода данных");
        }
    }
    public static class SpecialThread implements Runnable {
        public void run() {
            for (int i = 0; i < 101; i++) {
                System.out.println(i);
            }

        }
    }
}
