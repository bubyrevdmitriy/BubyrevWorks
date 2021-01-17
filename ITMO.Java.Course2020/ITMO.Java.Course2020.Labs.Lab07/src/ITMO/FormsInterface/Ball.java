package ITMO.FormsInterface;

public class Ball extends SolidOfRevolution {

    public Ball(double radius) {
        super(radius);
        super.volume = (Math.pow(radius, 3)*4*Math.PI)/3;

    }

    @Override
    public double getRadius() {

        return super.getRadius();
    }

    @Override
    public double getVolume() {

        return volume;
    }

    @Override
    public String toString() {
        return "Ball{" +
                "radius=" + radius +
                ", volume=" + volume +
                '}';
    }

}
