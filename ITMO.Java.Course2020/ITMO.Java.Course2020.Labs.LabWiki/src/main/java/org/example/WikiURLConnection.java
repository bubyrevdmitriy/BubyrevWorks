package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class WikiURLConnection {

    public static void main( String[] args ) {

        try {
            System.out.println("Type Wiki search request:");
            BufferedReader readerType = new BufferedReader(new InputStreamReader(System.in));
            String nString = readerType.readLine();

            String baseUrl =     "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";
            //String baseUrlITMO = "https://ru.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=%22Java%22";

            String query = nString;
            String encodedQuery = encodeValue(query); // Encoding a query string

            String completeUrl = baseUrl + encodedQuery;
            System.out.println(completeUrl);

            //String completeUrlITMO = baseUrlITMO + encodedQuery;
            //System.out.println(completeUrlITMO);

            WikiArticle wikiArticle = new WikiArticle();

            Thread t = new Thread (new URLWikiThread(completeUrl, wikiArticle));

            t.start();
            t.join();

            System.out.println("pageid: " +  wikiArticle.getPageid());
            System.out.println("ns: " + wikiArticle.getNs());
            System.out.println("title: " + wikiArticle.getTitle());
            System.out.println("extract: ");

            Arrays.stream(wikiArticle.getExtract()).forEach(System.out::println);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex.getCause());
        }
    }
}
