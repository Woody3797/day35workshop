package ibf2022.day35workshop.model;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;

public class Book {

    private Integer bookID;
    private String title;
    private String authors;
    private double averageRating;

    public Integer getBookID() {
        return bookID;
    }
    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthors() {
        return authors;
    }
    public void setAuthors(String authors) {
        this.authors = authors;
    }
    public double getAverageRating() {
        return averageRating;
    }
    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Book() {
    }

    public Book(Integer bookID, String title, String authors, double averageRating) {
        this.bookID = bookID;
        this.title = title;
        this.authors = authors;
        this.averageRating = averageRating;
    }

    
    public static Book create(Document d) {
        Book book = new Book();
        book.setBookID(d.getInteger("bookID"));
        book.setAuthors(d.getString("authors"));
        book.setAverageRating(d.getDouble("average_rating"));
        book.setTitle(d.getString("title"));
        return book;
    }

    public JsonObject toJson() {
        return Json.createObjectBuilder()
        .add("bookID", getBookID())
        .add("authors", getAuthors())
        .add("average_rating", getAverageRating())
        .add("title", getTitle())
        .build();
    }

}
