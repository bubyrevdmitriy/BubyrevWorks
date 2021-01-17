package ITMO.Services;

public class BinarySearchMultiThread extends Thread{

    int arr[];
    int first;
    int last;
    int key;
    CommonResource res;

    public BinarySearchMultiThread(int[] arr, int first, int last, int key, CommonResource res) {
        this.arr = arr;
        this.first = first;
        this.last = last;
        this.key = key;
        this.res = res;
    }

    public void start() {
        //res.x = 1;

        if (last>=first){
            int mid = first + (last - first)/2;
            if (arr[mid] == key){
                //return mid;
                res.setX(mid);
            }
            if (arr[mid] > key){
                BinarySearchMultiThread binarySearchMultiThread = new BinarySearchMultiThread(arr, first, mid-1, key, res);//search in left subarray
                binarySearchMultiThread.start();
            }else{
                BinarySearchMultiThread binarySearchMultiThread = new BinarySearchMultiThread(arr, mid+1, last, key, res);//search in right subarray
                binarySearchMultiThread.start();
            }
        }
        res.setX(-1);
    }
}
