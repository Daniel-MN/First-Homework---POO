package com.tema1;

import java.util.ArrayList;
import java.util.HashMap;

public class Administration {
    public static Database database = new Database();

    static ArrayList<Book> getBooksForPublishingRetailerID(int publishingRetailerID) {
        PublishingRetailer retailer = database.getPublishingRetailers().get(publishingRetailerID);
        if (retailer == null) {
            System.out.println("There is no retailer with ID = " + publishingRetailerID);
            return null;
        }

        ArrayList<Book> books = new ArrayList<>();
        for (IPublishingArtifact artifact : retailer.getPublishingArtifacts()) {

            if (artifact instanceof Book book) {
                books.add(book);
                //All books from array artifacts are unique
            }

            if (artifact instanceof EditorialGroup group) {
                for (Book book : group.getBooks()) {
                    if (!(books.contains(book))) {
                        books.add(book);
                    }
                }
            }

            if (artifact instanceof PublishingBrand brand) {
                for (Book book : brand.getBooks()) {
                    if (!(books.contains(book))) {
                        books.add(book);
                    }
                }
            }
        }
        return books;
    }

    static ArrayList<Language> getLanguagesForPublishingRetailerID(int publishingRetailerID) {
        ArrayList<Language> languages = new ArrayList<>();
        PublishingRetailer retailer = database.getPublishingRetailers().get(publishingRetailerID);
        if (retailer == null) {
            System.out.println("There is no retailer with ID = " + publishingRetailerID);
            return null;
        }

        Language language;
        HashMap<Integer, Language> languagesMap = database.getLanguages();
        for (IPublishingArtifact artifact : retailer.getPublishingArtifacts()) {

            if (artifact instanceof Book book) {
                language = languagesMap.get(book.getLanguageID());
                if (language == null) {
                    System.out.println("No language with ID = " + book.getLanguageID());
                    continue;
                }

                if (!(languages.contains(language))) {
                    languages.add(language);
                }
            }

            if (artifact instanceof EditorialGroup group) {

                for (Book book : group.getBooks()) {

                    language = languagesMap.get(book.getLanguageID());
                    if (language == null) {
                        System.out.println("No language with ID = " + book.getLanguageID());
                        continue;
                    }

                    if (!(languages.contains(language))) {
                        languages.add(language);
                    }
                }
            }

            if (artifact instanceof PublishingBrand brand) {

                for (Book book : brand.getBooks()) {

                    language = languagesMap.get(book.getLanguageID());
                    if (language == null) {
                        System.out.println("No language with ID = " + book.getLanguageID());
                        continue;
                    }

                    if (!(languages.contains(language))) {
                        languages.add(language);
                    }
                }
            }
        }

        return languages;
    }

    static ArrayList<Country> getCountriesForBookID(int bookID) {
        Book book = database.getBooks().get(bookID);
        if (book == null) {
            System.out.println("There is no book with ID = " + bookID);
            return null;
        }

        ArrayList<Country> countries = new ArrayList<>();

        HashMap<Integer, PublishingRetailer> retailers = database.getPublishingRetailers();
        for (Integer ID : retailers.keySet()) {

            ArrayList<Book> books = Administration.getBooksForPublishingRetailerID(ID);
            if (books == null) {
                return null;
            }

            if (books.contains(book)) {
                for (Country country : retailers.get(ID).getCountries()) {
                    if (!(countries.contains(country))) {
                        countries.add(country);
                    }
                }
            }
        }

        return countries;
    }

    //adds the books from a retailer to a HashMap
    static void addBooksRetailer(PublishingRetailer retailer, HashMap<Book, Integer> map) {
        for (IPublishingArtifact artifact : retailer.getPublishingArtifacts()) {
            if (artifact instanceof Book book) {
                map.put(book, 1);
            }

            if (artifact instanceof EditorialGroup group) {
                for (Book book : group.getBooks()) {
                    map.put(book, 1);
                }
            }

            if (artifact instanceof PublishingBrand brand) {
                for (Book book : brand.getBooks()) {
                    map.put(book,1);
                }
            }
        }
    }

    static ArrayList<Book> getCommonBooksForRetailerIDs(int retailerID1, int retailerID2) {
        PublishingRetailer retailer1 = database.getPublishingRetailers().get(retailerID1);
        if (retailer1 == null) {
            System.out.println("There is no retailer with ID = " + retailerID1);
            return null;
        }

        HashMap<Book, Integer> map = new HashMap<>();
        addBooksRetailer(retailer1, map);

        PublishingRetailer retailer2 = database.getPublishingRetailers().get(retailerID2);
        if (retailer2 == null) {
            System.out.println("There is no retailer with ID = " + retailerID2);
            return null;
        }


        ArrayList<Book> commonBooks = new ArrayList<>();
        for (IPublishingArtifact artifact : retailer2.getPublishingArtifacts()) {
            if (artifact instanceof Book book) {
                if (map.containsKey(book) && !commonBooks.contains(book)) {
                    commonBooks.add(book);
                }
            }

            if (artifact instanceof EditorialGroup group) {
                for (Book book : group.getBooks()) {
                    if (map.containsKey(book) && !commonBooks.contains(book)) {
                        commonBooks.add(book);
                    }
                }
            }

            if (artifact instanceof PublishingBrand brand) {
                for (Book book : brand.getBooks()) {
                    if (map.containsKey(book) && !commonBooks.contains(book)) {
                        commonBooks.add(book);
                    }
                }
            }
        }
        return commonBooks;
    }

    static ArrayList<Book> getAllBooksForRetailerIDs(int retailerID1, int retailerID2) {
        PublishingRetailer retailer1 = database.getPublishingRetailers().get(retailerID1);
        if (retailer1 == null) {
            System.out.println("There is no retailer with ID = " + retailerID1);
            return null;
        }

        PublishingRetailer retailer2 = database.getPublishingRetailers().get(retailerID2);
        if (retailer2 == null) {
            System.out.println("There is no retailer with ID = " + retailerID2);
            return null;
        }

        HashMap<Book, Integer> map = new HashMap<>();
        addBooksRetailer(retailer1, map);
        addBooksRetailer(retailer2, map);

        return new ArrayList<>(map.keySet());
    }

    static void printBooksRetailer(int retailerID) {
        System.out.println("The books of retailer with ID = " + retailerID + " are:");

        ArrayList<Book> books = Administration.getBooksForPublishingRetailerID(retailerID);
        if (books == null || books.isEmpty()) {
            System.out.println("This retailer has no book");
            System.out.println("\n");
            return;
        }

        for (Book book : books) {
            System.out.println(book.getName());
        }

        System.out.println("\n");
    }

    //prints the languages for the books of a retailer
    static void printLanguagesRetailer(int retailerID) {
        System.out.println("The books of retailer with ID = " + retailerID + " are published in languages:");

        ArrayList<Language> languages = Administration.getLanguagesForPublishingRetailerID(retailerID);
        if (languages == null || languages.isEmpty()) {
            System.out.println("No language can be found for retailer with ID = " + retailerID);
            System.out.println("\n");
            return;
        }

        for (Language language : languages) {
            System.out.println(language.getName());
        }

        System.out.println("\n");
    }

    //print the countries in which a book was published
    static void printCountriesBook(int BookID) {
        System.out.println("The countries in which the book with ID = " + BookID + " was published are:");

        ArrayList<Country> countries = Administration.getCountriesForBookID(BookID);
        if (countries == null || countries.isEmpty()) {
            System.out.println("Book with ID = " + BookID + " has not been published in any country");
            System.out.println("\n");
            return;
        }
        for (Country country : countries) {
            System.out.println(country.getCountryCode());
        }

        System.out.println("\n");
    }

    static void printCommonBooks(int retailerID1, int retailerID2) {
        System.out.println("The common books between retailer with ID = " + retailerID1 +
                        " and retailer with ID = " + retailerID2 + " are:");

        ArrayList<Book> commonBooks = Administration.getCommonBooksForRetailerIDs(retailerID1, retailerID2);
        if (commonBooks == null || commonBooks.isEmpty()) {
            System.out.println("These two retailers have no book in common");
            System.out.println("\n");
            return;
        }

        for (Book book : commonBooks) {
            System.out.println(book.getName());
        }

        System.out.println("\n");
    }

    static void printAllBooks(int retailerID1, int retailerID2) {
        System.out.println("All books published by retailer with ID = " + retailerID1 +
                " or by retailer with ID = " + retailerID2 + " are:");

        ArrayList<Book> allBooks = Administration.getAllBooksForRetailerIDs(retailerID1, retailerID2);
        if (allBooks == null || allBooks.isEmpty()) {
            System.out.println("These retailers have no book");
            System.out.println("\n");
            return;
        }

        for (Book book : allBooks) {
            System.out.println(book.getName());
        }

        System.out.println("\n");
    }

    public static void main(String[] args) {

        for (int i = 1; i <= 5; i++) {
            printBooksRetailer(i);
        }

        for (int i = 19; i <= 24; i++) {
            printLanguagesRetailer(i);
        }

        printCountriesBook(224);
        printCountriesBook(488);
        printCountriesBook(13827);
        printCountriesBook(12444);
        printCountriesBook(14958);

        printCommonBooks(1, 2);
        printCommonBooks(7, 14);
        printCommonBooks(20, 13);
        printCommonBooks(23, 24);
        printCommonBooks(10, 29);

        printAllBooks(1, 2);
        printAllBooks(7, 14);
        printAllBooks(20, 13);
        printAllBooks(23, 24);
        printAllBooks(10, 29);
    }
}
