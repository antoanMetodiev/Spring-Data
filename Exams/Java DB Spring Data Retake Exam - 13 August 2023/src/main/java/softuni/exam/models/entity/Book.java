package softuni.exam.models.entity;

import softuni.exam.models.enums.GenreType;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "books")
public class Book extends BaseEntity{

    @Column(unique = true, nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private boolean available;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GenreType genre;

    @Column(nullable = false)
    @Positive
    private double rating;


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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public GenreType getGenre() {
        return genre;
    }

    public void setGenre(GenreType genre) {
        this.genre = genre;
    }

    @Positive
    public double getRating() {
        return rating;
    }

    public void setRating(@Positive double rating) {
        this.rating = rating;
    }
}
