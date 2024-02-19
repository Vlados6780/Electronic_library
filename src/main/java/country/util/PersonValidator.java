package country.util;


import country.dao.PersonDAO;
import country.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }


    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if (personDAO.getPersonByName(person.getName()).isPresent()) {
            errors.rejectValue("name", "", "A person with that name already exists");
        }

        if (!Character.isUpperCase(person.getName().codePointAt(0)))
            errors.rejectValue("name", "", "The name must begin with a capital letter");
    }

}
