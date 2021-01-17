package ITMO;

import java.util.List;

public class CodeResorses {

    //task1
    public static int listArray(List<String> locustTreeSong) {
        int lengthStringMaxLength = 0;//
        int lengthStringCurLength = 0;
        int iStringMaxLength = 0;
        String temp;
        for (int i = 0; i < locustTreeSong.size(); i++) {
            lengthStringCurLength = locustTreeSong.get(i).length();
            temp = locustTreeSong.get(i);
            System.out.println(temp);
            if(lengthStringCurLength>lengthStringMaxLength) {
                iStringMaxLength = i;
                lengthStringMaxLength = lengthStringCurLength;
            }
        }
        return iStringMaxLength;
    }

    //Task4
    public static int count(String str, String target) {
        return (str.length() - str.replace(target, "").length()) / target.length();
    }

    //Task3
    public static String stringCenzor(String str, String str1, String str2) {
        return (str.replace(str1, str2));
    }


    //Task5
    public static String stringReverse(String stringA) {

        String stringResult = "";

        for (String retval : stringA.split(" ")) {
            //System.out.println(retval);
            char[] charA = new char[retval.length()];
            for (int i = 0; i < retval.length(); i++) {
                charA[i] = retval.charAt(i);
            }
            //String temp ="";
            char temp1;
            for (int i = 0; i < charA.length; i++) {
                temp1 = charA[charA.length - 1 - i];
                //System.out.print(temp1);
                stringResult = stringResult + temp1;
            }
            //System.out.print(" ");
            stringResult = stringResult + " ";
            //System.out.println(temp);


        }
        return stringResult;

    }


    public static boolean polindrom (char[] charA) {
        boolean stringAResult=true;
        for (int i = 0; i < (charA.length) / 2; i++) {
            char a = charA[i];
            char b = charA[charA.length - 1 - i];
            //System.out.println(""+a+" "+b);
            if (a != b) {
                stringAResult = false;
                break;
            }
        }
        return stringAResult;
    }



}
