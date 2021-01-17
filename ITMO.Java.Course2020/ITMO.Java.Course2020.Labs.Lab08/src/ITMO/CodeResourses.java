package ITMO;

import java.util.TreeSet;

public class CodeResourses {

    public static int maxElement(int[][] array){
        int maxElement = 0;
        int curElement = 0;
        for (int i1 = 0; i1 < array.length; i1++) {
            for (int j1 = 0; j1 < array[i1].length; j1++) {
                curElement=array[i1][j1];
                if(curElement>maxElement){maxElement=curElement;}
            }
        }
        return maxElement;
    }

    public static boolean arraySquare(int[][] array){
        boolean result = false;
        int arrayLength = array.length;
        int stringMaxLength = 0;
        int stringCurLength = 0;
        for (int i1 = 0; i1 < array.length; i1++) {
                stringCurLength =array[i1].length;
                if(stringCurLength>stringMaxLength )
                {
                    stringMaxLength =stringCurLength;
                }
        }
        if(stringMaxLength== arrayLength)
        {
            result =true;
        }

        return result;
    }

    public static int maxString(int[][] array){
        TreeSet<Integer> stringList = new TreeSet<>();
        //stringList.add(1);
        int curString=0;
        for (int i1 = 0; i1 < array.length; i1++) {
            for (int j1 = 0; j1 < array[i1].length; j1++) {
                curString=curString+array[i1][j1];
            }
            stringList.add(curString);
            curString=0;

        }
        return stringList.last();
    }

    public static int[] randomArray(int[] array1, int[] array2){

        int indexRandom =  (int)(Math.random() * array1.length);
        //System.out.println("индекс:");
        //System.out.println(indexRandom);
        int result1=array1[indexRandom];
        int result2=array2[indexRandom];

        int[] arrayResult = new int[2];
        arrayResult[0] = result1;
        arrayResult[1] = result2;
        // ("Число из первого массива: " + result1 + ", число из второго массива: " + result2)
        return arrayResult;
    }





}
