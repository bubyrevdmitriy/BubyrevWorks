package ITMO;

public class CodeResourses {

    public static void printArrayDouble(double [] doubles) {

        for (int i = 0; i < doubles.length; i++) {
            System.out.print(doubles[i]);
            System.out.print(" ");
        }
        System.out.println("");
    }

    public static void printArrayString(String [] strings) {

        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
            //System.out.print(" ");
        }
        System.out.println("");
    }

}
