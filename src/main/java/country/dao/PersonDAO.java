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
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public Optional<Person> getPersonByName(String name) {
        return jdbcTemplate.query("select * from person where name=?", new Object[]{name},
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Person> index() {
        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
    }
    public void save(Person person) {
        jdbcTemplate.update("insert into person (name, yearOfBirth) values (?, ?)", person.getName(),
                person.getYearOfBirth());
    }

    public Person showOne(int person_id){
        return jdbcTemplate.query("select * from person where person_id=?", new Object[]{person_id},
                        new BeanPropertyRowMapper<>(Person.class))
                .stream().findAny().orElse(null);
    }


    public void update(int person_id, Person updatedPerson) {
        jdbcTemplate.update("update person set name=? where person_id=?", updatedPerson.getName(),
                person_id);
    }

    public void delete(int person_id) {
        jdbcTemplate.update("delete from person where person_id=?", person_id);
    }


    public List<Book> getBooksByPersonId(int id) {
        return jdbcTemplate.query("select * from book where person_id=?", new Object[]{id},
                new BeanPropertyRowMapper<>(Book.class));
    }
}