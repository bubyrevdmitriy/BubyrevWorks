package ITMO;

public class Pyramid extends Shape{
    private double s;
    private double h;

    public Pyramid(double volume, double s, double h) {
        super(volume);
        this.s = s;
        this.h = h;
    }

    public double getS() {
        return s;
    }

    public double getH() {
        return h;
    }

    @Override
    public String toString() {
        return "Pyramid{" +
                "volume"+volume+
                ", s=" + s +
                ", h=" + h +
                '}';
    }
}
