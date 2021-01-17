package ITMO.Forms;

public class Pyramid extends Shape {
    protected double square;
    protected double height;

    public Pyramid(double square, double height) {
        this.square=square;
        this.height=height;
        super.volume =  square * height / 3;
    }


    protected double getSquare() {
        return square;
    }

    @Override
    protected double getVolume() {
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
