package org.example;

import java.util.Arrays;

public class WikiArticle {

    String pageid;
    String ns;
    String title;
    String [] extract;

    public WikiArticle() {
    }

    public WikiArticle(String pageid, String ns, String title, String extract) {
        this.pageid = pageid;
        this.ns = ns;
        this.title = title;

        String stringLongExtract = extract.toString();
        String[] stringArrayExtract = stringLongExtract.split("\\. ");

        for(int i=0; i<stringArrayExtract.length; i++) {
            String temp = stringArrayExtract[i]+". ";
            stringArrayExtract[i]=temp;
        }

        this.extract = stringArrayExtract;
    }

    public String getPageid() {
        return pageid;
    }

    public void setPageid(String pageid) {
        this.pageid = pageid;
    }

    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getExtract() {
        return extract;
    }

    public void setExtract(String extract) {
        String stringLongExtract = extract.toString();
        String[] stringArrayExtract = stringLongExtract.split("\\. ");

        for(int i=0; i<stringArrayExtract.length; i++) {
            String temp = stringArrayExtract[i]+". ";
            stringArrayExtract[i]=temp;
        }

        this.extract = stringArrayExtract;
    }

    @Override
    public String toString() {
        return "WikiArticle{" +
                "pageid='" + pageid + '\'' +
                ", ns='" + ns + '\'' +
                ", title='" + title + '\'' +
                ", extract=" + Arrays.toString(extract) +
                '}';
    }
}
