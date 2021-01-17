package ITMO.Services;

public class CodeResourses {



    public static int binarySearchIndex(int arr[], int first, int last, int key){
        if (last>=first){
            int mid = first + (last - first)/2;
            if (arr[mid] == key){
                return mid;
            }
            if (arr[mid] > key){
                return binarySearchIndex(arr, first, mid-1, key);//search in left subarray
            }else{
                return binarySearchIndex(arr, mid+1, last, key);//search in right subarray
            }
        }
        return -1;
    }

    public static int binarySearchIndexMultiThread(int arr[], int first, int last, int key){
        if (last>=first){
            int mid = first + (last - first)/2;
            if (arr[mid] == key){
                return mid;
            }
            if (arr[mid] > key){
                return binarySearchIndexMultiThread(arr, first, mid-1, key);//search in left subarray
            }else{
                return binarySearchIndexMultiThread(arr, mid+1, last, key);//search in right subarray
            }
        }
        return -1;
    }





}
