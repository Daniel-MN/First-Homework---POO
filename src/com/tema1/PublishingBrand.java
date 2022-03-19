package com.tema1;

import java.util.ArrayList;

public class PublishingBrand implements IPublishingArtifact {
    int ID;
    String name;
    ArrayList<Book> books;

    PublishingBrand() {
        books = new ArrayList<>();
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

    public ArrayList<Book> getBooks() {
        return books;
    }

    @Override
    public String Publish() {
        StringBuilder publishingBrandPublish = new StringBuilder("<xml>\n" +
                "\t<publishingBrand>\n" +
                "\t\t<ID>" + this.ID + "</ID>\n" +
                "\t\t<Name>" + this.name + "</Name>\n" +
                "\t</ publishingBrand>\n" +
                "\t<books>\n");

        for (Book book : books) {
            publishingBrandPublish.append(book.PublishInBlock(2));
        }

        publishingBrandPublish.append("\t</books>\n</xml>\n");

        return publishingBrandPublish.toString();
    }
}
