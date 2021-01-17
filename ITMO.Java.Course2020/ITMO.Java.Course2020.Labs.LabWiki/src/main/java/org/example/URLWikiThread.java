package org.example;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Map;
import java.util.Set;

public class URLWikiThread implements Runnable{

    private static HttpURLConnection connection;
    private BufferedReader reader;
    private String line;
    private StringBuffer responseContent = new StringBuffer();

    private String completeUrl;
    WikiArticle wikiArticle;

    public String getCompleteUrl() {
        return completeUrl;
    }

    public void setCompleteUrl(String completeUrl) {
        this.completeUrl = completeUrl;
    }

    public URLWikiThread(String completeUrl, WikiArticle wikiArticle) {
        this.completeUrl = completeUrl;
        this.wikiArticle = wikiArticle;
    }

    public URLWikiThread() {
    }

    public void run() {

        //request
        try {
            URL url =  new URL(completeUrl);

            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            //response
            int status = connection.getResponseCode();
            System.out.println("Connection status " + status );

            if(status>299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }

                reader.close();
            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while ((line = reader.readLine()) != null){
                    responseContent.append(line);
                }


                reader.close();
            }

            String result = String.valueOf(responseContent);

            JsonElement jsonElement = new JsonParser().parse(result);
            JsonElement pages = jsonElement.getAsJsonObject().get("query").getAsJsonObject().get("pages");

            Set<Map.Entry<String, JsonElement>> entrySet = pages.getAsJsonObject().entrySet();
            //-----------------------------
            JsonElement title1 = null;
            for(Map.Entry<String,JsonElement> entry : entrySet){
                title1 = entry.getValue();
            }
            //------------------------------
            Set<Map.Entry<String, JsonElement>> entrySet1 = title1.getAsJsonObject().entrySet();

            JsonElement pageid = null;
            JsonElement ns = null;
            JsonElement title = null;
            JsonElement extract = null;

            for(Map.Entry<String,JsonElement> entry : entrySet1){
                String temp = entry.getKey();
                JsonElement valueTemp = entry.getValue();
                if(temp.equals("pageid")) {pageid = valueTemp;}
                if(temp.equals("ns")) {ns = valueTemp;}
                if(temp.equals("title")) {title = valueTemp;}
                if(temp.equals("extract")) {extract = valueTemp;}
            }
            ///////////////////////////////////////////////////////
            wikiArticle.setPageid(pageid.toString());
            wikiArticle.setNs(ns.toString());
            wikiArticle.setTitle(title.toString());
            wikiArticle.setExtract(extract.toString());


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
