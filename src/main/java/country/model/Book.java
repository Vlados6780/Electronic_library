package country.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {
    @NotEmpty(message = "Name should not be empty")
    @Size(min=2, max=150, message = "Name of book should be between 2 and 150 characters")
    private String name_book;
    @Size(min=2, max=100, message = "Name should be between 2 and 100 characters")
    private String author_book;
    @Min(value = 1500, message = "The year of publication must be greater than 1500")
    private int year_book;
    private int id_book;

    public Book() {

    }

    public Book(String name_book, String author_book, int year_book) {
        this.name_book = name_book;
        this.author_book = author_book;
        this.year_book = year_book;
    }
}
