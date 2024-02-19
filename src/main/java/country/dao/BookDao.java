package country.dao;

import country.model.Book;
import country.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> index() {
        return jdbcTemplate.query("select * from book", new BeanPropertyRowMapper<>(Book.class));
    }
    public void save(Book book) {
        jdbcTemplate.update("insert into book (name_book, author_book, year_book) values (?, ?, ?)",
                book.getName_book(),
                book.getAuthor_book(),
                book.getYear_book());
    }
    public Book showOne(int book_id) {
        return jdbcTemplate.query("select * from book where id_book = ?", new Object[]{book_id},
                        new BeanPropertyRowMapper<>(Book.class))
                .stream().findAny().orElse(null);
    }
    public void update(int book_id, Book book) {
        jdbcTemplate.update("update book set name_book = ?, author_book = ?, year_book = ? where id_book = ?",
                book.getName_book(),
                book.getAuthor_book(),
                book.getYear_book(),
                 book_id);
    }

    public void delete(int book_id) {
        jdbcTemplate.update("delete from book where id_book = ?", book_id);

    }
    public Optional<Person> getBookOwner(int id) {
        return jdbcTemplate.query("select person.* from book join person on book.person_id = person.person_id " +
                        "where book.id_book = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny();
    }

    public void setFree(int id) {
        jdbcTemplate.update("update book set person_id = null where id_book=?", id);
    }

    public void assign(int id, Person selectedPerson) {
        jdbcTemplate.update("update book set person_id = ? where id_book=?", selectedPerson.getPerson_id(), id);
    }
}
