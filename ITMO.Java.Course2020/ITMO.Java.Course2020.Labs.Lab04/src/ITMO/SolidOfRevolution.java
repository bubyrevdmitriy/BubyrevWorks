package ITMO;

public class SolidOfRevolution extends Shape {
    protected double radius;

    public double getRadius() {
        return radius;
    }

    public SolidOfRevolution(double volume, double radius) {
        super(volume);
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
