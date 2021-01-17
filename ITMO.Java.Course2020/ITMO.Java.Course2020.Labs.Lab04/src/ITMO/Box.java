package ITMO;

import java.util.ArrayList;

public class Box extends  Shape {
    private int MaxBoxCapacity;
    private int CurBoxCapacity;

    private ArrayList<Shape> shapes;

    public Box(double volume, int maxBoxCapacity) {
        super(volume);
        MaxBoxCapacity = maxBoxCapacity;
        CurBoxCapacity = 0;
        shapes = new ArrayList<Shape>();
    }

    public int getMaxBoxCapacity() {
        return MaxBoxCapacity;
    }

    public int getCurBoxCapacity() {
        return CurBoxCapacity;
    }

    public void setCurBoxCapacity(int curBoxCapacity) {
        CurBoxCapacity = curBoxCapacity;
    }

    public boolean addShapeToBox(Shape shape) {
        int a =getCurBoxCapacity();
        int b = getMaxBoxCapacity();
        //System.out.println(a);
        //System.out.println(b);

        if(a <= (b-1)){
            shapes.add(shape);
            //CurBoxCapacity++;
            int CurBoxCapacityNew = getCurBoxCapacity()+1;
            setCurBoxCapacity(CurBoxCapacityNew);
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
                "MaxBoxCapacity=" + MaxBoxCapacity +
                ", CurBoxCapacity=" + CurBoxCapacity +
                ", shapes=" + shapes +
                ", volume=" + volume +
                '}';
    }
}
