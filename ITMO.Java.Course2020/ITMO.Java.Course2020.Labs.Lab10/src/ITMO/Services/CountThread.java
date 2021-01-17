package ITMO.Services;

public class CountThread  implements Runnable{

    CommonResource res;
    public CountThread(CommonResource res){
        this.res=res;
    }
    public void run() {
        res.x = 1;
        for (int i = 1; i < 5; i++) {
            //System.out.printf("%s %d \n", Thread.currentThread().getName(), res.x);
            System.out.printf(""+i+"\n");
            //System.out.printf(""+i+"\n");
            res.x++;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
    }
}
