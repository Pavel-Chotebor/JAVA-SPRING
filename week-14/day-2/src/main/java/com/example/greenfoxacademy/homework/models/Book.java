package com.example.greenfoxacademy.homework.models;

public class Book {

    protected int id;
    protected String title;
    protected String author;
    protected int releaseYear;

    public Book(int id, String title, String author, int releaseYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.releaseYear = releaseYear;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }
}
