package ITMO.Forms;

public abstract class Shape {
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
}
