package ITMO;

public class TimeDownload {
    private  long startTime;
    private  long finishTime;


    public TimeDownload() {

    }

    public void setStartTime() {
        this.startTime = System.currentTimeMillis();
    }

    public long getStartTime() {
        return startTime;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public void setFinishTime() {
        this.finishTime = System.currentTimeMillis();
    }
}
