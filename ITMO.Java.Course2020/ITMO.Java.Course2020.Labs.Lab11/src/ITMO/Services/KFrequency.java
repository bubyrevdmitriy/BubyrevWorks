package ITMO.Services;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class KFrequency
{

    public static Map<K, Integer> makeKFrequency(K[] array) {//одномерный массив

        Map<K, Integer> kFrequency = new TreeMap<>();
        int s;
        for (int i1 = 0; i1 < array.length; i1++) {
            K temp = array[i1];
            int tempInt = array[i1].getValue();
            boolean count=true;
            for (Map.Entry<K, Integer> entry : kFrequency.entrySet()) {
                //System.out.println(entry.getKey() + "/" + entry.getValue());
                //написать код здесь
                if(entry.getValue()==tempInt) {
                    kFrequency.put(array[i1], kFrequency.get(temp) + 1);
                    count=false;
                    break;
                }
            }
            //написать код здесь
            if(count) {
                kFrequency.put(array[i1], 1);
            }




           // System.out.print(array[i1].getValue() + "\t");
            //System.out.println();
        }
        return kFrequency;
    }

    public static Map<K, Integer> makeKFrequency(K[][] array) {//двухмерный массив

        Map<K, Integer> kFrequency = new HashMap<>();
        int s;
        for (int i1 = 0; i1 < array.length; i1++) {
            boolean count=true;
            //System.out.println(i1+"yyy");
            for (int j1 = 0; j1 < array[i1].length; j1++) {
                K temp = array[i1][j1];
                int tempInt = array[i1][j1].getValue();
                //System.out.println(tempInt);
                //kFrequency.put(array[i1][j1], 1);

                for (Map.Entry<K, Integer> entry : kFrequency.entrySet()) {
                    //System.out.println(entry.getKey() + "/" + entry.getValue());
                    //написать код здесь
                    Integer countMap = kFrequency.get(entry.getKey());

                    //System.out.println(tempInt+"сравнение"+entry.getKey().getValue()+" повторяемость "+countMap);
                    if(entry.getKey().getValue()==tempInt) {
                        kFrequency.put(entry.getKey(), countMap + 1);
                        count=false;
                        break;
                    }
                }
                //написать код здесь
                if(count) {
                    kFrequency.put(array[i1][j1], new Integer(1));
                }
                count=true;
                //System.out.print(array[i1][j1].getValue() + "\t");
            }
            //System.out.println();
        }
        return kFrequency;
    }



}


