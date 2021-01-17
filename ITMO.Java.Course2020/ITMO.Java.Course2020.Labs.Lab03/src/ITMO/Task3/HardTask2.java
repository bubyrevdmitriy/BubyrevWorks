package ITMO.Task3;



public class HardTask2 {

    public static void main(String[] args) {
        System.out.println("Сложные задания. Задание №2");

        System.out.println("Реализовать сортировку выбором.\n" +
                "Шаги алгоритма:\n" +
                "1. находим номер минимального значения в текущем списке\n" +
                        "2. производим обмен этого значения со значением первой неотсортированной\n" +
                        "позиции (обмен не нужен, если минимальный элемент уже находится на данной\n" +
                "позиции)\n" +
                        "3. теперь сортируем хвост списка, исключив из рассмотрения уже\n" +
                        "отсортированные элементы. Подробнее см. тут -\n" +
                        "ru.wikipedia.org/wiki/Сортировка_выбором");

        // write your code here

        int[] myArray = new int[]{ 1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10,1,2,3,4,5,6,7,8,9,10 };
        //---
        System.out.println("наш массив чисел до сортировки:");
        for (int i = 0; i < myArray.length; i++) {
            System.out.print( myArray[i]);
            System.out.print(" ");
        }
        System.out.println();

        CodeResourses.SelectionSort(myArray);

        System.out.println("наш массив чисел после сортировки:");
        for (int i = 0; i < myArray.length; i++) {
            System.out.print( myArray[i]);
            System.out.print(" ");
        }




    }
}