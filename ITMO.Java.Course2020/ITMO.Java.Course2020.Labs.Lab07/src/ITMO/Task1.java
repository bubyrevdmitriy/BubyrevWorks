package ITMO;

import ITMO.FormsInterface.Ball;
import ITMO.FormsInterface.Box;
import ITMO.FormsInterface.Cylinder;
import ITMO.FormsInterface.Pyramid;

public class Task1 {
    public static void main(String[] args) {
        // write your code here
        System.out.println("Задание №1");

        System.out.println("Заменить класс Shape на интерфейс Shape.");
        System.out.println("");

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
