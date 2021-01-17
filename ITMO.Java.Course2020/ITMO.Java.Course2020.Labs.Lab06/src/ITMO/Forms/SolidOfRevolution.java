package ITMO.Forms;

public  abstract class SolidOfRevolution extends Shape {
    protected double radius;

    public double getRadius() {
        return radius;
    }

    @Override
    protected double getVolume() {
        return super.getVolume();
    }

    public SolidOfRevolution(double radius) {
        super();
        this.volume=getVolume();
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "SolidOfRevolution{" +
                "radius=" + radius +
                ", volume=" + volume +
                '}';
    }
}
