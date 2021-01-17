package ITMO.Services;

public class NameThreadMinus implements Runnable{
    CommonResource res;

    public NameThreadMinus(CommonResource res){
        this.res=res;
    }
    public void run(){
        //res.x=1;
        while (true){

            if(res.x==1) {
                notify();
                System.out.printf("%s  \n", Thread.currentThread().getName());
                res.x--;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else{
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
