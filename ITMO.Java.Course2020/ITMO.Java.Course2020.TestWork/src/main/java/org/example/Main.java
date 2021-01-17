package org.example;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Program start");
            String nString;

            System.out.println("It is program for downloading files.\n" +
                    "Type URL adress of file, that you want to download, dirrectory and file name\n" +
                    "Example:\n" +
                    "https://files.stroyinf.ru/Data2/1/4293769/4293769496.pdf C:\\Users\\bubyr\\IdeaProjects\\ITMO.Java.Course2020.TestWork\\downloads\\ pdfExampleDownload31.pdf\n" +
                    "Type: e   to end the program");
            //https://files.stroyinf.ru/Data2/1/4293769/4293769496.pdf C:\Users\bubyr\IdeaProjects\ITMO.Java.Course2020.TestWork\downloads\ pdfExampleDownload11.pdf
            //https://files.stroyinf.ru/Data2/1/4293769/4293769441.pdf C:\Users\bubyr\IdeaProjects\ITMO.Java.Course2020.TestWork\downloads\ pdfExampleDownload21.pdf
            //https://files.stroyinf.ru/Data2/1/4293854/4293854546.pdf C:\Users\bubyr\IdeaProjects\ITMO.Java.Course2020.TestWork\downloads\ pdfExampleDownload31.pdf
            //https://files.stroyinf.ru/Data2/1/4293769/4293769491.pdf C:\Users\bubyr\IdeaProjects\ITMO.Java.Course2020.TestWork\downloads\ pdfExampleDownload41.pdf
            ArrayList<String> links = new ArrayList<String>();
            ArrayList<Thread> threads = new ArrayList<Thread>();

            int totalDownloadFilesCount = 0;
            double totalDownloadFilesSize = 0;
            TimeDownload timeDownload = new TimeDownload();

            while (!(nString = reader.readLine()).equals("e")) {

                try {
                    if (totalDownloadFilesCount == 0) {
                        timeDownload.setStartTime();
                    }


                    String[] stringArray = nString.split(" ");
                    String link = stringArray[0];
                    String dir = stringArray[1] + " " + stringArray[2];

                    if (!links.contains(link)) {
                        links.add(link);


                        //String link = "https://files.stroyinf.ru/Data2/1/4293769/4293769496.pdf";
                        //String dir = "C:\\Users\\bubyr\\IdeaProjects\\ITMO.Java.Course2020.TestWork\\downloads\\ pdfExampleDownload21.pdf";
                        URL url = new URL(link);
                        HttpURLConnection http = (HttpURLConnection) url.openConnection();
                        double fileSize = (double) http.getContentLengthLong();

                        totalDownloadFilesCount = totalDownloadFilesCount + 1;
                        totalDownloadFilesSize = totalDownloadFilesSize + fileSize;

                        File out = new File(dir);

                        //System.out.println("link " + link);
                        //System.out.println("dir " + dir);

                        Thread thread = new Thread(new Download(link, out));
                        threads.add(thread);
                        threads.get(totalDownloadFilesCount - 1).start();

                    } else {
                        System.out.println("document" + link + "has been alredy ownload in this session");
                    }

                } catch (Exception e) {
                    System.out.println("Mistake in data url" + e);
                }
            }


            if(nString.equals("e")) {
                for (int i = 0; i < threads.size(); i++) {
                    threads.get(i).join();
                }

                timeDownload.setFinishTime();
                long programTime = ((timeDownload.getFinishTime()) - (timeDownload.getStartTime())) / 1000;

                System.out.println("Program finish");
                System.out.println("Files download: " + totalDownloadFilesCount);
                System.out.println("Total sise: " + totalDownloadFilesSize / (1024) + " Kbyte");
                System.out.println("Total time: " + programTime + "sec");
                System.out.println("Average speed: " + totalDownloadFilesSize / (1024) / programTime + "Kbyte/sec");
            }

        } catch (Exception e) {
            //  Block of code to handle errors
            System.out.println("Program mistake" + e);

        }

    }
}
