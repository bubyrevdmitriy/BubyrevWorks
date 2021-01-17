package ITMO.FormsInterface;

public class Pyramid implements  Shape {
    protected double volume;

    protected double square;
    protected double height;

    public Pyramid(double square, double height) {
        this.square=square;
        this.height=height;
        this.volume =  square * height / 3;
    }


    protected double getSquare() {
        return square;
    }
    @Override
    public double getVolume() {
        return volume;
    }
    @Override
    public String toString() {
        return "Pyramid {" +
                "square=" + square +
                ", height=" + height +
                ", volume=" + volume +
                '}';
    }
}
