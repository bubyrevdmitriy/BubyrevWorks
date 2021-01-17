package ITMO.Forms;

import java.util.ArrayList;

public class Box extends Shape{

    private ArrayList<Shape> shapes;

    public Box(double volume) {
        super(volume);
        shapes = new ArrayList<Shape>();
    }

    public ArrayList<Shape> getShapes() {
        return shapes;
    }

    public boolean addShapeToBox(Shape shape) {
        double mustBoxValue =shape.getVolume();
        ArrayList<Shape> shapes = this.getShapes();
        for (int i = 0; i < shapes.size(); i++) {
            mustBoxValue =mustBoxValue +(shapes.get(i).getVolume());
            //System.out.println(mustBoxValue);
        }
        if(mustBoxValue <= volume){
            shapes.add(shape);
            System.out.println("Current volume:" + mustBoxValue);
            return true;
        }
        else
        {
            return false;
        }
    }

    public void printBox() {
        System.out.println("элементы в коробке':");
        for (int i = 0; i < shapes.size(); i++) {
            System.out.print(shapes.get(i).toString());
            System.out.println(" ");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return "Box{" +
                ", shapes=" + shapes +
                ", volume=" + volume +
                '}';
    }

}
