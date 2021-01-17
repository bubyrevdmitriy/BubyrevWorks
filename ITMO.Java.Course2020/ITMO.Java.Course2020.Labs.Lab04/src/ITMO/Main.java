package ITMO;

public class Main {

    public static void main(String[] args) {
	// write your code here
        System.out.println("Задание №4");

        System.out.println("Часть 1. Работа с классами");

        Bail bail1 = new Bail(1,3);//1
        System.out.println(bail1.toString());

        Cylinder cylinder1 = new Cylinder(4,5,6);//2
        System.out.println(cylinder1.toString());

        SolidOfRevolution solidOfRevolution1 = new SolidOfRevolution(7,8);//3
        System.out.println(solidOfRevolution1.toString());

        Pyramid pyramid1 = new Pyramid(9,10,11);//4
        System.out.println(pyramid1.toString());

        Shape shape1 = new Shape(12);//5
        System.out.println(shape1.toString());

        Box box1 = new Box(4,4);
        System.out.println(box1.toString());

        boolean addResult1 = box1.addShapeToBox(bail1);
        boolean addResult2 = box1.addShapeToBox(cylinder1);
        boolean addResult3 = box1.addShapeToBox(solidOfRevolution1);
        boolean addResult4 = box1.addShapeToBox(pyramid1);
        boolean addResult5 = box1.addShapeToBox(shape1);

        System.out.println("addResult1" + " " + addResult1);
        System.out.println("addResult2" + " " + addResult2);
        System.out.println("addResult3" + " " + addResult3);
        System.out.println("addResult4" + " " + addResult4);
        System.out.println("addResult5" + " " + addResult5);

        System.out.println(box1.toString());
        //box1.printBox();

        System.out.println("Часть 2. Температура");

        //("Kelvin")   //("Celsius")   //("Fahrenheit")
        DegreeAbsolute degreeAbsolute1 = new DegreeAbsolute(0, "Kelvin");
        System.out.println(degreeAbsolute1.toString());
        System.out.println(degreeAbsolute1.getTemperatureCelsius());
        System.out.println(degreeAbsolute1.getTemperatureKelvin());
        System.out.println(degreeAbsolute1.getTemperatureFahrenheit());

        degreeAbsolute1.setTemperature(0, "Celsius");
        System.out.println(degreeAbsolute1.toString());
        System.out.println(degreeAbsolute1.getTemperatureCelsius());
        System.out.println(degreeAbsolute1.getTemperatureKelvin());
        System.out.println(degreeAbsolute1.getTemperatureFahrenheit());

        /*DegreeAbsolute degreeAbsolute2 = new DegreeAbsolute(0, "Celsius");
        System.out.println(degreeAbsolute2.toString());
        System.out.println(degreeAbsolute2.getTemperatureCelsius());
        System.out.println(degreeAbsolute2.getTemperatureKelvin());
        System.out.println(degreeAbsolute2.getTemperatureFahrenheit());

        DegreeAbsolute degreeAbsolute3 = new DegreeAbsolute(0, "Fahrenheit");
        System.out.println(degreeAbsolute3.toString());
        System.out.println(degreeAbsolute3.getTemperatureCelsius());
        System.out.println(degreeAbsolute3.getTemperatureKelvin());
        System.out.println(degreeAbsolute3.getTemperatureFahrenheit());*/


    }
}
