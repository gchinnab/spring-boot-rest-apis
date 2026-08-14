package com.chinna.learn.books_rest_api.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

public class BookRequest {

    @Size(min=1, max = 30, message = "title field must be between 1 and 30 characters")
    private String title;

    @Size(min=1, max = 40, message = "author field must be between 1 and 40 characters")
    private String author;

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Size(min=1, max = 30, message = "category field must be between 1 and 30 characters")
    private String category;

    @Min(value = 1, message = "Rating must be atleast 1")
    @Max(value = 5, message = "Rating cann't exceed 5")
    private int rating;

    public BookRequest(String title, String author, String category, int rating) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.rating = rating;
    }
}
