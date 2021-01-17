package ITMO;

public class CodeResourses {

    public static boolean polindrom(StringBuilder stringBuilder1) {
        for (int i = 0; i < (stringBuilder1.length()) / 2; i++) {
            if ((stringBuilder1.substring(i,i+1)).equals(stringBuilder1.substring(stringBuilder1.length()- 1 - i,stringBuilder1.length()- 1 - i+1)))  {} else {
                return false;//break;
            }
        }
        return true;
    }


    public static void printArrayDouble(double [] doubles) {

        for (int i = 0; i < doubles.length; i++) {
            System.out.print(doubles[i]);
            System.out.print(" ");
        }
        System.out.println("");
    }



}
