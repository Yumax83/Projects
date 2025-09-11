package com.example.productsapp;

public class Product {

    private String title, poster, description;

    public Product(String title, String poster, String description) {
        this.title = title;
        this.poster = poster;
        this.description = description;
    }
    public String getTitle() {
        return title;
    }
    public String getPoster() {
        return poster;
    }
    public String getDescription() {
        return description;
    }
}
