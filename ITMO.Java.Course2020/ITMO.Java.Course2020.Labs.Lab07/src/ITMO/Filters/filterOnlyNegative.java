package ITMO.Filters;

import java.util.Arrays;

public class filterOnlyNegative implements filter {
    @Override
    public Object apply(Object o) {
        double[] d = (double[]) o;
        d = Arrays.stream(d).filter(x -> x < 0).toArray();
        return d;
    }

    public filterOnlyNegative() {
    }
}
