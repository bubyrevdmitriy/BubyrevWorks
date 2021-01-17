package ITMO.Services;

public class CountThreadTask3 implements Runnable{

    CommonResource res;
    public CountThreadTask3(CommonResource res){
        this.res=res;
    }
    public void run() {
        synchronized(res){
        //res.x = 1;
        for (int i = 0; i < 1000; i++) {
            //System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
            //res.x++;
            res.increment();
            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
            }
        }
    }
    }
}
