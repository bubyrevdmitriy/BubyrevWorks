package ITMO.Services;

public class CountThreadState extends Thread {

    CommonResource res;
    public CountThreadState(CommonResource res){
        this.res=res;
    }

    public void start(Thread thread) {
        res.x = 1;
        for (int i = 1; i < 8; i++) {
            System.out.printf(String.valueOf(thread.getState())+"\n");
            //System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
            res.x++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}
