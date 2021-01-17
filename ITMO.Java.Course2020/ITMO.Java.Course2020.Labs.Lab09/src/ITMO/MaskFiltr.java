package ITMO;

import java.io.File;
import java.io.FilenameFilter;

public class MaskFiltr implements FilenameFilter {
    String mask;

    MaskFiltr(String Mask) {
        mask = Mask;
    }

    public boolean accept(File f, String name) {
        if (name.indexOf(mask) > 0)
            return true;
        else
            return false;
    }
}