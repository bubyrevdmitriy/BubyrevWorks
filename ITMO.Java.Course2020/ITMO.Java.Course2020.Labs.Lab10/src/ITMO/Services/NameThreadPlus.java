package ITMO.Services;

public class NameThreadPlus implements Runnable{
    CommonResource res;

    public NameThreadPlus(CommonResource res){
        this.res=res;
    }
    public void run(){
        //res.x=1;
        while (true){

            if(res.x==0) {
                notify();
                System.out.printf("%s  \n", Thread.currentThread().getName());
                res.x++;
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
