package ITMO.Services;

public class CommonResource {

    int x=-1;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if(x>this.x){
            this.x = x;
        }

    }

    public CommonResource() {}
}
