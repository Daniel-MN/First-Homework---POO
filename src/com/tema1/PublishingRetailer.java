package com.tema1;

import java.util.ArrayList;

public class PublishingRetailer {
    int ID;
    String name;
    ArrayList<IPublishingArtifact> publishingArtifacts;
    ArrayList<Country> countries;

    PublishingRetailer() {
        publishingArtifacts = new ArrayList<>();
        countries = new ArrayList<>();
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

    public ArrayList<IPublishingArtifact> getPublishingArtifacts() {
        return publishingArtifacts;
    }

    public ArrayList<Country> getCountries() {
        return countries;
    }
}
