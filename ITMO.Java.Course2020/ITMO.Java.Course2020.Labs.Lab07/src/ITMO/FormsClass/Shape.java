package ITMO.FormsClass;

public  abstract class Shape implements Comparable<Shape> {
    protected double volume;

    public Shape(double volume) {
        this.volume = volume;
    }

    public Shape() {
        this.volume = getVolume();
    }

    protected double getVolume(){
        return  volume;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "volume=" + volume +
                '}';
    }

    @Override
    public int compareTo(Shape shape) {
        int result=0;
        if((volume-shape.getVolume())==0) {

        } else{
          if((volume-shape.getVolume())>0) {
              result=1;
          } else {
              result=-1;
          }
        }
        return result;
    }


}
