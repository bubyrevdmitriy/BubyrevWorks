package ITMO;

import java.io.File;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

class Serv {

    public Serv() {
        //System.out.println("класс создан");
    }

    public void list(String str, String maska) {
        try {
            System.setOut(new PrintStream(System.out, true, "Cp866"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        File f = new File(str);
        String[] DirList = f.list();
        String[] FileMask = f.list(new MaskFiltr(maska));

        if (FileMask.length > 0)
            for (int j = 0; j < FileMask.length; j++)
                System.out.println(FileMask[j]);

        for (int i = 0; i < DirList.length; i++) {
            File f1 = new File(str + "/" + DirList[i]);
            if (!f1.isFile()) {
                list(str + "/" + DirList[i], maska);
            }
        }
    }
}