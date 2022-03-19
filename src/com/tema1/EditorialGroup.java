package com.tema1;

import java.util.ArrayList;

public class EditorialGroup implements IPublishingArtifact {
    int ID;
    String name;
    ArrayList<Book> books;

    EditorialGroup() {
        books = new ArrayList<>();
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    @Override
    public String Publish() {
        StringBuilder editorialGroupPublish = new StringBuilder("<xml>\n" +
                "\t<editorialGroup>\n" +
                "\t\t<ID>" + this.ID + "</ID>\n" +
                "\t\t<Name>" + this.name + "</Name>\n" +
                "\t</editorialGroup>\n" +
                "\t<books>\n");

        for (Book book : books) {
            editorialGroupPublish.append(book.PublishInBlock(2));
        }

        editorialGroupPublish.append("\n");

        editorialGroupPublish.append("\t</books>\n</xml>\n");

        return editorialGroupPublish.toString();
    }
}
