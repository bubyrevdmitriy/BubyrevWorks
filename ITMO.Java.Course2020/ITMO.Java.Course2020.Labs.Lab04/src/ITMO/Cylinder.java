package ITMO;

public class Cylinder extends SolidOfRevolution {

    private double height;

    public double getHeight() {
        return height;
    }

    public Cylinder(double volume, double radius, double height) {
        super(volume, radius);
        this.height = height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + height +
                ", radius=" + radius +
                ", volume=" + volume +
                '}';
    }
}
