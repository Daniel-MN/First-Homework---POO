package com.tema1;

public class Author {
    int ID;
    String firstName;
    String lastName;

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return (firstName + " " + lastName);
    }
}
