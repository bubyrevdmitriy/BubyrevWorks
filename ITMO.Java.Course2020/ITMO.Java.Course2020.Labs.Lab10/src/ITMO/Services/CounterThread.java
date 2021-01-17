package ITMO.Services;

public class CounterThread {
    int count = 0;
    public void increment() {
        count = count + 1;
    }
    public int getCount() {
        return count;
    }

    public CounterThread(int count) {
        this.count = count;
    }
}
