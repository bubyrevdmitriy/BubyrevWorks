package ITMO;

import java.util.*;
import java.lang.*;
import java.text.*;
import java.io.*;
import java.util.regex.Pattern;

class Ideone
{
    //static HashMap<String, Integer> vocabularyRus = new HashMap<String, Integer>();
    //static Scanner sc = new Scanner(System.in);

    public static boolean isRusLetter(char c){
        return 'а' <= c && c <= 'я' || 'А' <= c && c <= 'Я';
    }


    public static String toAlpha(String s){
        return s.replaceAll("[^а-яА-Я]","");
    }




    public static void makeRusFrequency(List<String> listString)
    {

        Map<Character, Integer> vocabularyRus = new TreeMap<>();
        String s;
        for (int i = 0; i < listString.size(); i++) {
            s = listString.get(i);
            s = s.toLowerCase();
            s = toAlpha(s);
            System.out.println(s);
                if(!s.isEmpty()){

                    for(int j = 0; j < s.length(); j++){
                        char ch = s.charAt(j);

                        if(isRusLetter(ch)) {
                            if (!vocabularyRus.containsKey(ch)) {
                                vocabularyRus.put(ch, 1);
                            } else {
                                vocabularyRus.put(ch, vocabularyRus.get(ch) + 1);
                            }
                        }
                    }
                }
        }

        for (Character ch : vocabularyRus.keySet())
        {
            System.out.println(ch + " " + vocabularyRus.get(ch));
        }
    }

}