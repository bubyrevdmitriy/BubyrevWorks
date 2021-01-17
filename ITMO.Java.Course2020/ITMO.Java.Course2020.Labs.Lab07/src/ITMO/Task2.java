package ITMO;

import ITMO.FormsClass.Ball;
import ITMO.FormsClass.Cylinder;
import ITMO.FormsClass.Pyramid;
import ITMO.FormsClass.Shape;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Task2 {

    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №2");

        System.out.println("Shape должен наследоваться от Comparable. То есть, shapes должно быть\n" +
                "сравнимы по объему.\n" +
                "Можно воспользоваться методом Arrays.sort(), чтобы убедится, что массив фигур\n" +
                "сортируется верно.");
        System.out.println("");

        TreeSet<Shape> shapeList = new TreeSet<>(); /* объявили и создали массив. Java выделила память под массив из 4 строк, и сейчас в каждой ячейке записано значение null (поскольку строка — ссылочный тип)*/

        Ball ball1 = new Ball(1);//1
        System.out.println(ball1.toString());

        Cylinder cylinder1 = new Cylinder(1,3);//2
        System.out.println(cylinder1.toString());

        Pyramid pyramid1 = new Pyramid(1,3);//3
        System.out.println(pyramid1.toString());

        Ball ball2 = new Ball(2);//1
        System.out.println(ball2.toString());

        Cylinder cylinder2 = new Cylinder(2,3);//2
        System.out.println(cylinder2.toString());

        Pyramid pyramid2 = new Pyramid(2,3);//3
        System.out.println(pyramid2.toString());


        shapeList.add(ball1); //1
        shapeList.add(cylinder1); //2
        shapeList.add(pyramid1); //3
        shapeList.add(ball2); //4
        shapeList.add(cylinder2); //5
        shapeList.add(pyramid2); //6

        System.out.println(shapeList.toString());



    }
}
