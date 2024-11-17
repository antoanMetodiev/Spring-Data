package softuni.exam.models.dto.jsons;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class BookDto {

    @NotNull
    @Size(min = 3, max = 40)
    @Expose
    private String author;

    @NotNull
    @Expose
    private boolean available;

    @NotNull
    @Size(min = 5)
    @Expose
    private String description;

    @NotNull
    @Expose
    private String genre;

    @NotNull
    @Expose
    @Size(min = 3, max = 40)
    private String title;

    @NotNull
    @Positive
    @Expose
    private double rating;

    public BookDto(String author, boolean available, String description, String genre, String title, double rating) {
        this.author = author;
        this.available = available;
        this.description = description;
        this.genre = genre;
        this.title = title;
        this.rating = rating;
    }

    public BookDto() {}

    public @NotNull @Size(min = 3, max = 40) String getAuthor() {
        return author;
    }

    public void setAuthor(@NotNull @Size(min = 3, max = 40) String author) {
        this.author = author;
    }

    @NotNull
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(@NotNull boolean available) {
        this.available = available;
    }

    public @NotNull @Size(min = 5) String getDescription() {
        return description;
    }

    public void setDescription(@NotNull @Size(min = 5) String description) {
        this.description = description;
    }

    public @NotNull String getGenre() {
        return genre;
    }

    public void setGenre(@NotNull String genre) {
        this.genre = genre;
    }

    public @NotNull @Size(min = 3, max = 40) String getTitle() {
        return title;
    }

    public void setTitle(@NotNull @Size(min = 3, max = 40) String title) {
        this.title = title;
    }

    @NotNull
    @Positive
    public double getRating() {
        return rating;
    }

    public void setRating(@NotNull @Positive double rating) {
        this.rating = rating;
    }
}
