package ITMO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Task4 {
    public static void main(String[] args) {

        System.out.println("Задание №4");
        System.out.println("Написать метод для копирования файла (побайтно, или массивами байт).");

        try {
            //чтение из файла
            int myDesiredCapacity = 100000; // 100KB
            //Path fileInputFilePath = Paths.get("C:\\Users\\Username\\Desktop\\testFile.txt");
            File fileInput = new File("Aviator.txt");
            File targetFile = new File("Task4File.txt");
            FileInputStream is = new FileInputStream(fileInput);
            FileOutputStream fileOutputStream = new FileOutputStream(targetFile);

            FileChannel fileChannel = is.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(myDesiredCapacity);

            byte[] bytes;

            int byteCount = fileChannel.read(byteBuffer);
            if(byteCount >= 0)
            {
                byteBuffer.flip();
                bytes = byteBuffer.array();

                fileOutputStream.write(bytes);
                fileOutputStream.close();
                byteBuffer.clear();
            }

            //запись в файл

        }
        catch(Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка ввода данных");
        }
    }
}
