package ITMO.Filters;

import java.util.Arrays;

public class filterOnlyNegative extends filter {


    @Override
    public Object apply(Object o) {
        double[] d = (double[]) o;
        d = Arrays.stream(d).filter(x -> x < 0).toArray();
        return d;
    }

    public filterOnlyNegative() {
    }

    /*@Override
    public static double[] apply(double[] doubles) {
        double[] d = o;
        d = Arrays.stream(d).filter(x -> x < 0).toArray();
        return d;
    }*/

}
