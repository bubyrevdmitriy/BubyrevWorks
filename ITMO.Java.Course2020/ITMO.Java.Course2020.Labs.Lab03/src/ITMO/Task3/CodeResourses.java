package ITMO.Task3;

import java.util.ArrayList;
import java.util.List;

public class CodeResourses {


    //SimpleTask1//HardTask1
    public static int linearSearchIndex(int arr[], int elementToSearch) {

        for (int index = 0; index < arr.length; index++) {
            if (arr[index] == elementToSearch) {
                return index;
            }
        }
        return -1;
    }

    //SimpleTask1
    public static void printIndexSearch(int elementToSearch, int index) {
        if (index == -1) {
            System.out.println(elementToSearch + " not found.");
        } else {
            System.out.println(elementToSearch + " found at index: " + index);
        }
    }

    //SimpleTask2
    public static boolean progressiveArray(int arr[]) {
        boolean result = true;
        int arrayBeforeValue = arr[0];
        for (int index = 1; index < arr.length; index++) {
            if (arr[index] <= arrayBeforeValue) {
                result = false;
                return result;
            }
        }
        return result;
    }

    //SimpleTask3
    public static int[] ArrayOnlyEvens(int myArray[]) {

        List<Integer> myList = new ArrayList<Integer>();
        for (int temp : myArray) {
            if (temp % 2 == 0) {
                myList.add(temp);
            }
        }

        int[] myArrayResult = new int[myList.size()];
        for (int i = 0; i < myArrayResult.length; i++) {
            myArrayResult[i] = myList.get(i);
        }

        return myArrayResult;
    }

    public static int[] ReverseArray(int myArray[]) {

        int[] myArrayResult = new int[myArray.length];
        for (int i = 0; i < myArrayResult.length; i++) {
            myArrayResult[i] = myArray[myArray.length - 1 - i];
        }

        return myArrayResult;
    }

    public static int binarySearch(int arr[], int elementToSearch) {

        int firstIndex = 0;
        int lastIndex = arr.length - 1;

        // условие прекращения (элемент не представлен)
        while (firstIndex <= lastIndex) {
            int middleIndex = (firstIndex + lastIndex) / 2;
            // если средний элемент - целевой элемент, вернуть его индекс
            if (arr[middleIndex] == elementToSearch) {
                return middleIndex;
            }

            // если средний элемент меньше
            // направляем наш индекс в middle+1, убирая первую часть из рассмотрения
            else if (arr[middleIndex] < elementToSearch)
                firstIndex = middleIndex + 1;

                // если средний элемент больше
                // направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
            else if (arr[middleIndex] > elementToSearch)
                lastIndex = middleIndex - 1;

        }
        return -1;
    }

    public static int recursiveBinarySearch(int arr[], int firstElement, int lastElement, int elementToSearch) {

        // условие прекращения
        if (lastElement >= firstElement) {
            int mid = firstElement + (lastElement - firstElement) / 2;

            // если средний элемент - целевой элемент, вернуть его индекс
            if (arr[mid] == elementToSearch)
                return mid;

            // если средний элемент больше целевого
            // вызываем метод рекурсивно по суженным данным
            if (arr[mid] > elementToSearch)
                return recursiveBinarySearch(arr, firstElement, mid - 1, elementToSearch);

            // также, вызываем метод рекурсивно по суженным данным
            return recursiveBinarySearch(arr, mid + 1, lastElement, elementToSearch);
        }

        return -1;
    }

    public static void SelectionSort(int myArray[]) {
        //Каждый проход выбирать самый минимальный элемент и смещать его в начало.
        // При этом каждый новый проход начинать сдвигаясь вправо, то есть первый проход — с первого элемента, второй проход — со второго.
        for (int left = 0; left < myArray.length; left++) {
            int minInd = left;
            for (int i = left; i < myArray.length; i++) {
                if (myArray[i] < myArray[minInd]) {
                    minInd = i;
                }
            }
            //swap(myArray, left, minInd);
            if (myArray[minInd] < myArray[left]) {
                int tmp = myArray[left];
                myArray[left] = myArray[minInd];
                myArray[minInd] = tmp;
            }
        }
    }

    public static int Task3Search(double myArray[], int firstElement, int lastElement) {
        System.out.println("начало метода");
        /*for (int index = 0; index < myArray.length; index++) {
            double midValue =myArray[index];
            double result1 = Math.cos(Math.toRadians(Math.pow(midValue, 5))) + Math.pow(midValue, 4) - 345.3 * midValue - 23;
            //double midValueResult = Math.cos(Math.toRadians(Math.pow(midValue, 5))) + Math.pow(midValue, 4) - 345.3 * midValue - 23;
            if (result1 > -0.25 && result1 < 0.25) {
                return index;
            }
        }
        return -1;*/
        //int firstElement = 0;//id
        //int lastElement = myArray.length;//id
        if (lastElement >= firstElement) {
            int mid = firstElement + (lastElement - firstElement) / 2;

            double midValueMin = myArray[mid - 1];
            double midValue = myArray[mid];
            double midValueMax = myArray[mid + 1];

            //cos(x^5) + x^4 - 345.3 * x - 23 = 0
            double midValueMinResult = Math.cos(Math.pow(midValueMin, 5)) + Math.pow(midValueMin, 4) - 345.3 * midValueMin - 23;
            double midValueResult = Math.cos(Math.pow(midValue, 5)) + Math.pow(midValue, 4) - 345.3 * midValue - 23;
            double midValueMaxResult = Math.cos(Math.pow(midValueMax, 5)) + Math.pow(midValueMax, 4) - 345.3 * midValueMax - 23;


            // если средний элемент - целевой элемент, вернуть его индекс
            if (midValueResult > -0.25 && midValueResult < 0.25 ) {
                return mid;
            }

            if (Math.abs(midValueMinResult)<Math.abs(midValueMaxResult))
                {
                    return Task3Search(myArray, firstElement, mid - 1);
                }
                else
                    {
                return Task3Search(myArray, mid + 1, lastElement);
            }

        }
        return -1;
    }

    //Метод быстрой сортировки

    public static void quickSort(int[] array, int low, int high) {
        if (array.length == 0)
            return;//завершить выполнение, если длина массива равна 0

        if (low >= high)
            return;//завершить выполнение если уже нечего делить

        // выбрать опорный элемент
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        // разделить на подмассивы, который больше и меньше опорного элемента
        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }

            if (i <= j) {//меняем местами
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }

        // вызов рекурсии для сортировки левой и правой части
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
public static void quickSortMin(int[] array, int low, int high) {
    if (array.length == 0)
        return;//завершить выполнение, если длина массива равна 0

    if (low >= high)
        return;//завершить выполнение если уже нечего делить

    // выбрать опорный элемент
    int middle = low + (high - low) / 2;
    int oporaID= low + (middle - low) / 2;// изменение  изменение изменение изменение изменение изменение изменение изменение изменение
    int opora = array[oporaID];

    // разделить на подмассивы, который больше и меньше опорного элемента
    int i = low, j = high;
    while (i <= j) {
        while (array[i] < opora) {
            i++;
        }

        while (array[j] > opora) {
            j--;
        }

        if (i <= j) {//меняем местами
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }

    // вызов рекурсии для сортировки левой и правой части
    if (low < j)
        quickSortMin(array, low, j);

    if (high > i)
        quickSortMin(array, i, high);
}


//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
public static void quickSortMax(int[] array, int low, int high) {
    if (array.length == 0)
        return;//завершить выполнение, если длина массива равна 0

    if (low >= high)
        return;//завершить выполнение если уже нечего делить

    // выбрать опорный элемент
    int middle = low + (high - low) / 2;
    int oporaID= middle + (high - middle) / 2;// изменение  изменение изменение изменение изменение изменение изменение изменение изменение
    int opora = array[oporaID];

    // разделить на подмассивы, который больше и меньше опорного элемента
    int i = low, j = high;
    while (i <= j) {
        while (array[i] < opora) {
            i++;
        }

        while (array[j] > opora) {
            j--;
        }

        if (i <= j) {//меняем местами
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }

    // вызов рекурсии для сортировки левой и правой части
    if (low < j)
        quickSortMax(array, low, j);

    if (high > i)
        quickSortMax(array, i, high);
}


//----------------------------------------------------------------------------------------------------------------------------------------------------------------------





}

