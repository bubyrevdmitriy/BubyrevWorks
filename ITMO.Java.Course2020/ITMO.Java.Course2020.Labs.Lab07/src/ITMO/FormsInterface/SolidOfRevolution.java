package ITMO.FormsInterface;

public class SolidOfRevolution  implements Shape{

    protected double radius;
    protected double volume;

    public double getRadius() {
        return radius;
    }

    public SolidOfRevolution(double radius) {
        super();
        this.volume=getVolume();
        this.radius = radius;
    }


    @Override
    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "SolidOfRevolution{" +
                "radius=" + radius +
                ", volume=" + volume +
                '}';
    }

}
