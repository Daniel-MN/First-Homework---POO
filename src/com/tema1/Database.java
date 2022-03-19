package com.tema1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class Database {
    HashMap<Integer, Book> books;
    HashMap<Integer, Language> languages;
    HashMap<Integer, Author> authors;
    HashMap<Integer, EditorialGroup> editorialGroups;
    HashMap<Integer, PublishingBrand> publishingBrands;
    HashMap<Integer, PublishingRetailer> publishingRetailers;
    HashMap<Integer, Country> countries;

    Database() {
        initBooks();
        initLanguages();
        initAuthors();
        initBooksAuthors();
        initEditorialGroups();
        initPublishingBrands();
        initEditorialGroupsBooks();
        initPublishingBrandsBooks();
        initPublishingRetailer();
        initCountries();
        initPublishingRetailersCountries();
        initPublishingRetailersBooks();
        initPublishingRetailersEditorialGroups();
        initPublishingRetailersPublishingBrands();
    }

    public HashMap<Integer, PublishingBrand> getPublishingBrands() {
        return publishingBrands;
    }

    public HashMap<Integer, Book> getBooks() {
        return books;
    }

    public HashMap<Integer, Language> getLanguages() {
        return languages;
    }

    public HashMap<Integer, Author> getAuthors() {
        return authors;
    }

    public HashMap<Integer, EditorialGroup> getEditorialGroups() {
        return editorialGroups;
    }

    public HashMap<Integer, PublishingRetailer> getPublishingRetailers() {
        return publishingRetailers;
    }

    public HashMap<Integer, Country> getCountries() {
        return countries;
    }

    void initBooks() {
        this.books = new HashMap<>();
        File file = new File("init/books.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                Book newBook = new Book();
                String[] attributes = line.split("###");

                int i = 0;
                newBook.setID(Integer.parseInt(attributes[i++]));
                newBook.setName(attributes[i++]);
                newBook.setSubtitle(attributes[i++]);
                newBook.setISBN(new BigInteger(attributes[i++]));
                newBook.setPageCount(Integer.parseInt(attributes[i++]));
                newBook.setKeywords(attributes[i++]);
                newBook.setLanguageID(Integer.parseInt(attributes[i++]));
                newBook.setCreatedOn((new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")).parse(attributes[i]));

                books.put(newBook.getID(), newBook);

            }
        } catch (IOException | ParseException ex){
            System.out.println("Path not found or failed to read from file or failed to parse the date: " + ex);
        }
    }

    void initLanguages() {
        this.languages = new HashMap<>();
        File file = new File("init/languages.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                Language newLanguage = new Language();
                String[] attributes = line.split("###");

                int i = 0;
                newLanguage.setID(Integer.parseInt(attributes[i++]));
                newLanguage.setCode(attributes[i++]);
                newLanguage.setName(attributes[i]);

                languages.put(newLanguage.getID(), newLanguage);

            }
        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initAuthors() {
        this.authors = new HashMap<>();
        File file = new File("init/authors.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                Author newAuthor = new Author();
                String[] attributes = line.split("###");

                int i = 0;
                newAuthor.setID(Integer.parseInt(attributes[i++]));
                newAuthor.setFirstName(attributes[i++]);
                newAuthor.setLastName(attributes[i]);

                authors.put(newAuthor.getID(), newAuthor);

            }
        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initBooksAuthors() {
        File file = new File("init/books-authors.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split("###");

                int i = 0;
                Book book = books.get(Integer.parseInt(attributes[i++]));
                Author author = authors.get(Integer.parseInt(attributes[i]));

                if (!book.getAuthors().add(author)) {
                    System.out.println("Failed to add an author to the authors list of a book!");
                }
            }

        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initEditorialGroups() {
        this.editorialGroups = new HashMap<>();
        File file = new File("init/editorial-groups.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                EditorialGroup newEditorialGroup = new EditorialGroup();
                String[] attributes = line.split("###");

                int i = 0;
                newEditorialGroup.setID(Integer.parseInt(attributes[i++]));
                newEditorialGroup.setName(attributes[i]);

                editorialGroups.put(newEditorialGroup.getID(), newEditorialGroup);
            }
        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initPublishingBrands() {
        this.publishingBrands = new HashMap<>();
        File file = new File("init/publishing-brands.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                PublishingBrand newPublishingBrand = new PublishingBrand();
                String[] attributes = line.split("###");

                int i = 0;
                newPublishingBrand.setID(Integer.parseInt(attributes[i++]));
                newPublishingBrand.setName(attributes[i]);

                publishingBrands.put(newPublishingBrand.getID(), newPublishingBrand);
            }
        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initEditorialGroupsBooks() {
        File file = new File("init/editorial-groups-books.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split("###");

                int i = 0;
                EditorialGroup editorialGroup = editorialGroups.get(Integer.parseInt(attributes[i++]));
                Book book = books.get(Integer.parseInt(attributes[i]));

                if (!editorialGroup.getBooks().add(book)) {
                    System.out.println("Failed to add a book to the books list of a editorialGroup!");
                }
            }

        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initPublishingBrandsBooks() {
        File file = new File("init/publishing-brands-books.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split("###");

                int i = 0;
                PublishingBrand brand = publishingBrands.get(Integer.parseInt(attributes[i++]));
                Book book = books.get(Integer.parseInt(attributes[i]));

                if (!brand.getBooks().add(book)) {
                    System.out.println("Failed to add a book to the books list of a publishBrand!");
                }
            }

        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initPublishingRetailer() {
        this.publishingRetailers = new HashMap<>();
        File file = new File("init/publishing-retailers.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                PublishingRetailer newRetailer = new PublishingRetailer();
                String[] attributes = line.split("###");

                int i = 0;
                newRetailer.setID(Integer.parseInt(attributes[i++]));
                newRetailer.setName(attributes[i]);

                publishingRetailers.put(newRetailer.getID(), newRetailer);
            }
        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initCountries() {
        this.countries = new HashMap<>();
        File file = new File("init/countries.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                Country newCountry = new Country();
                String[] attributes = line.split("###");

                int i = 0;
                newCountry.setID(Integer.parseInt(attributes[i++]));
                newCountry.setCountryCode(attributes[i]);

                countries.put(newCountry.getID(), newCountry);
            }
        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initPublishingRetailersCountries() {
        File file = new File("init/publishing-retailers-countries.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split("###");

                int i = 0;
                PublishingRetailer retailer = publishingRetailers.get(Integer.parseInt(attributes[i++]));
                Country country = countries.get(Integer.parseInt(attributes[i]));

                if (!retailer.getCountries().add(country)) {
                    System.out.println("Failed to add a country to the countries list of a retailer!");
                }
            }

        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initPublishingRetailersBooks() {
        File file = new File("init/publishing-retailers-books.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split("###");

                int i = 0;
                PublishingRetailer retailer = publishingRetailers.get(Integer.parseInt(attributes[i++]));
                Book book = books.get(Integer.parseInt(attributes[i]));

                if (!retailer.getPublishingArtifacts().add(book)) {
                    System.out.println("Failed to add a book to the publishingArtifacts list of a retailer!");
                }
            }

        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initPublishingRetailersEditorialGroups() {
        File file = new File("init/publishing-retailers-editorial-groups.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split("###");

                int i = 0;
                PublishingRetailer retailer = publishingRetailers.get(Integer.parseInt(attributes[i++]));
                EditorialGroup group = editorialGroups.get(Integer.parseInt(attributes[i]));

                if (!retailer.getPublishingArtifacts().add(group)) {
                    System.out.println("Failed to add an editorialGroup to the publishingArtifacts list of a retailer!");
                }
            }

        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }

    void initPublishingRetailersPublishingBrands() {
        File file = new File("init/publishing-retailers-publishing-brands.in");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); //read the first line (header)

            String line;
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split("###");

                int i = 0;
                PublishingRetailer retailer = publishingRetailers.get(Integer.parseInt(attributes[i++]));
                PublishingBrand brand = publishingBrands.get(Integer.parseInt(attributes[i]));

                if (!retailer.getPublishingArtifacts().add(brand)) {
                    System.out.println("Failed to add a publishingBrand to the publishingArtifacts list of a retailer!");
                }
            }

        } catch (IOException ex){
            System.out.println("Path not found or failed to read from file: " + ex);
        }
    }
}
