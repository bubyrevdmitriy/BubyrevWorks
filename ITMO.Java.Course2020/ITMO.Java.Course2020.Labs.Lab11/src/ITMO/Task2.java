package ITMO;

import ITMO.Services.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Map;

import static ITMO.Services.KFrequency.makeKFrequency;

public class Task2 {
    public static void main(String[] args) {

        System.out.println("Задание №2");
        System.out.println("Метод получает на вход массив элементов типа К.\n" +
                "Вернуть надо объект Map<K, Integer>, где K — Значение из массива, а Integer\n" +
                "количество вхождений в массив.\n" +
                "<K> Map<K, Integer> arrayToMap(K[] ks);");
        try {
            System.out.println("Заполняем одномерный массив");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Введите количество элементов в массиве");
            String nString = reader.readLine();
            int n = Integer.parseInt(nString);
            //---
            KContainer kContainer1= new KContainer(n);
            //kContainer1.printArray();
            Iterator iterator1 = kContainer1.getIterator();
            while (iterator1.hasNext()) {
                K temp = (K)iterator1.next();
                System.out.print(temp.getValue() + "\t");
            }
            System.out.println();

            //K[][] array1 = kContainer1.getArray();

            Map<K, Integer> kFrequency1 = makeKFrequency(kContainer1.getArray());

            for (K k : kFrequency1.keySet())
            {
                System.out.println(k.getValue() + " " + kFrequency1.get(k));
            }




            //-----------------------------------------------------------------------
            System.out.println("Заполняем двухмерный массив массив");
            System.out.println("Введите количество  строк в массиве");
            String iString = reader.readLine();
            int i = Integer.parseInt(iString);
            System.out.println("Введите количество столбцов массиве");
            String jString = reader.readLine();
            int j = Integer.parseInt(jString);
            KContainer kContainer2= new KContainer(i,j);
            //kContainer2.printArray();
            System.out.println();
            Iterator iterator2 = kContainer2.getIterator();
            while (iterator2.hasNext()) {
                K temp = (K)iterator2.next();
                System.out.print(temp.getValue() + "\t");
            }

            System.out.println();
            Map<K, Integer> kFrequency2 = makeKFrequency(kContainer2.getArray());

            for (K k : kFrequency2.keySet())
            {
                System.out.println(k.getValue() + " " + kFrequency2.get(k));
            }
            //-------------------------------------------------------------------------------------------------------------------
      }
        catch(Exception e) {
            System.out.println("Ошибка ввода данных," + e);
        }
    }
}
