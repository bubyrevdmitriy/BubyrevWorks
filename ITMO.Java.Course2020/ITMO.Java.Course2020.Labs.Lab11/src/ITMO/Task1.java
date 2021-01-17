package ITMO;

import ITMO.Services.IntContainer;
import ITMO.Services.Iterator;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Task1 {
    public static void main(String[] args) {

        System.out.println("Задание №1");
        System.out.println("Написать итератор по массиву (одномерному, потом обобщить до двумерного)");
        //-----------------------------------------------------------------------
        try {
            System.out.println("Заполняем одномерный массив");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите количество элементов в массиве");
            String nString = reader.readLine();
            int n = Integer.parseInt(nString);
            //---
            IntContainer intContainer1= new IntContainer(n);
            intContainer1.printArray();
            Iterator iterator1 = intContainer1.getIterator();
                while (iterator1.hasNext()) {
                    System.out.print(iterator1.next() + "\t");
                }
            System.out.println();
            //-----------------------------------------------------------------------
            System.out.println("Заполняем двухмерный массив массив");
            System.out.println("Введите количество  строк в массиве");
            String iString = reader.readLine();
            int i = Integer.parseInt(iString);
            System.out.println("Введите количество столбцов массиве");
            String jString = reader.readLine();
            int j = Integer.parseInt(jString);
            IntContainer intContainer2= new IntContainer(i,j);
            intContainer2.printArray();
            System.out.println();
            Iterator iterator2 = intContainer2.getIterator();
            while (iterator2.hasNext()) {
                System.out.print(iterator2.next() + "\t");
            }

            System.out.println();

            //-----------------------------------------------------------------------
        }
        catch(Exception e) {
            System.out.println("Ошибка ввода данных");
        }
    }
}
