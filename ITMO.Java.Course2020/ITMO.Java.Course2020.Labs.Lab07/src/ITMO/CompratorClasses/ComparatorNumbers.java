package ITMO.CompratorClasses;

import java.util.Comparator;

public class ComparatorNumbers implements Comparator<String> {

    //long count = text.codePoints().filter(Character::isDigit).count();
    @Override
    public int compare(String o1, String o2) {
        long count1 = o1.codePoints().filter(Character::isDigit).count();
        long count2 = o2.codePoints().filter(Character::isDigit).count();

        if(count1==count2) {return 0;}
        else {
            if(count1>count2)
            {return -1;}
            else{return +1;}
        }
        //return o1.compareTo(o2);
    }

    public ComparatorNumbers() {
    }
}
