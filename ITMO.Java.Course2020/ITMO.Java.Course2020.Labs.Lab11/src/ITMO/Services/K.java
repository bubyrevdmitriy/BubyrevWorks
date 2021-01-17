package ITMO.Services;

public class K implements Comparable<K>{

    private int value;

    public K(int value) {
        this.value = value;
    }

    public K() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(K k) {
        return 0;
    }
}
