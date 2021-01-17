package ITMO.Filters;

import java.util.Arrays;

public class filterOnlyPositive extends filter {
    @Override
    public Object apply(Object o) {
        double[] d = (double[]) o;
        d = Arrays.stream(d).filter(x -> x > 0).toArray();
        return d;
    }

    public filterOnlyPositive() {
    }

    /*@Override
    public static double[] apply(double[] doubles) {
        double[] d = doubles;
        d = Arrays.stream(d).filter(x -> x > 0).toArray();
        return d;
    }*/

}
