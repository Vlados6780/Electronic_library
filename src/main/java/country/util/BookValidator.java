package country.util;

import country.dao.BookDao;
import country.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    private final BookDao bookDao;

    @Autowired
    public BookValidator(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book book = (Book) o;

        if (!Character.isUpperCase(book.getName_book().codePointAt(0)))
            errors.rejectValue("name_book", "",
                    "The title of the book must begin with a capital letter.");

        if (!Character.isUpperCase(book.getAuthor_book().codePointAt(0)))
            errors.rejectValue("author_book", "",
                    "The author's name must begin with a capital letter.");
    }
}
