package ITMO;

public class Bail extends SolidOfRevolution {

    public Bail(double volume, double radius) {
        super(volume, radius);
    }

    @Override
    public String toString() {
        return "Bail{" +
                "radius=" + radius +
                ", volume=" + volume +
                '}';
    }
}
