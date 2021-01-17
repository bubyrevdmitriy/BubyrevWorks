package ITMO.CompratorClasses;

import java.util.Comparator;

public class ComparatorAlthabet implements Comparator<String> {

    //char ch = str.charAt(last)

    @Override
    public int compare(String o1, String o2) {

        if(o1.charAt(0)==o2.charAt(0)) {return 0;}
        else {
            if(o1.charAt(0)>o2.charAt(0))
            {return +1;}
            else{return -1;}
        }
        //return o1.compareTo(o2);
    }

    public ComparatorAlthabet() {
    }
}
