package ITMO.FormsClass;

public class Cylinder extends SolidOfRevolution {
    protected double height;

    public Cylinder(double radius, double height) {
        super(radius);
        this.height=height;
        super.volume =  Math.PI * Math.pow(radius, 2) * height;
    }

    @Override
    public double getRadius() {
        return super.getRadius();
    }

    @Override
    protected double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "radius=" + radius +
                ", height=" + height +
                ", volume=" + volume +
                '}';
    }
}
