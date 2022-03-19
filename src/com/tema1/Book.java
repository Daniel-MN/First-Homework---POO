package com.tema1;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Book implements IPublishingArtifact {
    int ID;
    String name;
    String subtitle;
    BigInteger ISBN;
    int pageCount;
    String keywords;
    int languageID;
    Date createdOn;
    ArrayList<Author> authors;

    Book() {
        authors = new ArrayList<>();
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public void setISBN(BigInteger ISBN) {
        this.ISBN = ISBN;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public int getLanguageID() {
        return languageID;
    }

    public void setLanguageID(int languageID) {
        this.languageID = languageID;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public ArrayList<Author> getAuthors() {
        return authors;
    }

    public String bookAuthorsToString() {

        StringBuilder rez = new StringBuilder();
        for (Author author : this.authors) {
            rez.append(author.toString());
            rez.append("; ");
        }
        return rez.toString();
    }

    public String PublishInBlock(int nrTabs) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        String tabs = "\t";
        tabs = tabs.repeat(nrTabs);

        String bookPublish = tabs;

        bookPublish += "<book>\n" +
                "\t<title>" + this.name + "</title>\n" +
                "\t<subtitle>" + this.subtitle + "</subtitle>\n" +
                "\t<isbn>" + this.ISBN + "</isbn>\n" +
                "\t<pageCount>" + this.pageCount + "</pageCount>\n" +
                "\t<keywords>" + this.keywords + "</keywords>\n" +
                "\t<languageID>" + this.languageID + "</languageID>\n" +
                "\t<createdOn>" + formatter.format(this.createdOn) + "</createdOn>\n" +
                "\t<authors>" + bookAuthorsToString() + "</authors>\n" +
                "</book>";
        tabs = "\n" + tabs;
        bookPublish = bookPublish.replace("\n", tabs);
        return bookPublish;
    }


    @Override
    public String Publish() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        return "<xml>\n" +
                "\t<title>" + this.name + "</title>\n" +
                "\t<subtitle>" + this.subtitle + "</subtitle>\n" +
                "\t<isbn>" + this.ISBN + "</isbn>\n" +
                "\t<pageCount>" + this.pageCount + "</pageCount>\n" +
                "\t<keywords>" + this.keywords + "</keywords>\n" +
                "\t<languageID>" + this.languageID + "</languageID>\n" +
                "\t<createdOn>" + formatter.format(this.createdOn) + "</createdOn>\n" +
                "\t<authors>" + bookAuthorsToString() + "</authors>\n" +
                "</xml>";
    }
}
