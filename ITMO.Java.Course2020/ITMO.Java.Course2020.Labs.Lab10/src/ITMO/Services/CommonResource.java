package ITMO.Services;

public class CommonResource{

    int x=0;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void increment() {
        x = x + 1;
    }
}
