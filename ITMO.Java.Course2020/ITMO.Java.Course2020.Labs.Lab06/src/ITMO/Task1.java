package ITMO;

import ITMO.Forms.Ball;
import ITMO.Forms.Box;
import ITMO.Forms.Cylinder;
import ITMO.Forms.Pyramid;

public class Task1 {
    public static void main(String[] args) {

        System.out.println("Задание №1");

        System.out.println("Изменить иерархию shapes (использовать абстрактные классы, где это возможно)");

        System.out.println("Часть 1. Работа с классами");

        Ball ball1 = new Ball(1);//1
        System.out.println(ball1.toString());

        Cylinder cylinder1 = new Cylinder(1,3);//2
        System.out.println(cylinder1.toString());

        Pyramid pyramid1 = new Pyramid(1,3);//3
        System.out.println(pyramid1.toString());

        Box box1 = new Box(32);
        System.out.println(box1.toString());

        boolean addResult1 = box1.addShapeToBox(ball1);
        System.out.println("addResult1" + " " + addResult1);

        boolean addResult2 = box1.addShapeToBox(cylinder1);
        System.out.println("addResult2" + " " + addResult2);

        boolean addResult4 = box1.addShapeToBox(pyramid1);
        System.out.println("addResult4" + " " + addResult4);

        boolean addResult5 = box1.addShapeToBox(cylinder1);
        System.out.println("addResult5" + " " + addResult5);

        boolean addResult6 = box1.addShapeToBox(cylinder1);
        System.out.println("addResult6" + " " + addResult6);



        System.out.println(box1.toString());
    }
}
