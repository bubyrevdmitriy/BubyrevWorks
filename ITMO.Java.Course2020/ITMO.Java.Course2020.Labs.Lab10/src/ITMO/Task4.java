package ITMO;

import ITMO.Services.CommonResource;
import ITMO.Services.NameThreadMinus;
import ITMO.Services.NameThreadPlus;

public class Task4 {
    public static void main(String[] args) {

        System.out.println("Задание №4");
        System.out.println("Напишите программу, в которой создаются два потока, каждый из которых выводит\n" +
                "по очереди на консоль своё имя.\n" +
                "Начать можно с написания своего класс-потока, который выводит в бесконечном\n" +
                "цикле свое имя. Потом придется добавить синхронизацию с помощью wait() и\n" +
                "notify().");

        Store store=new Store();
        Producer producer = new Producer(store);
        Consumer consumer = new Consumer(store);
        new Thread(producer).start();
        new Thread(consumer).start();
    }
}
// Класс Магазин, хранящий произведенные товары
class Store{
    private int product=0;
    public synchronized void get() {
        while (product<1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        product--;
        System.out.println("покупатель");
        //System.out.println("Покупатель купил 1 товар");
        //System.out.println("Товаров на складе: " + product);
        notify();
    }
    public synchronized void put() {
        while (product>=1) {
            try {
                wait();
            }
            catch (InterruptedException e) {
            }
        }
        product++;
        System.out.println("производитель");
        //System.out.println("Производитель добавил 1 товар");
        //System.out.println("Товаров на складе: " + product);
        notify();
    }
}
// класс Производитель
class Producer implements Runnable{

    Store store;
    Producer(Store store){
        this.store=store;
    }
    public void run(){
        while (true)  {
            store.put();
            //System.out.println("Производитель class");
        }
    }
}
// Класс Потребитель
class Consumer implements Runnable{

    Store store;
    Consumer(Store store){
        this.store=store;
    }
    public void run(){
        while (true) {
            store.get();
            //System.out.println("Покупатель class");
        }
    }
}