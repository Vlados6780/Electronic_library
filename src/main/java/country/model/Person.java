package country.model;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Person {

        @NotEmpty(message = "Name should not be empty")
        @Size(min=2, max=30, message = "Name should be between 2 and 30 characters")
        private String name;
        private int person_id;
        @Min(value=1900, message = "Year of birth should be greater than 1990")
        private int yearOfBirth;
        public Person() {

        }

        public Person(int yearOfBirth, String name) {
            this.yearOfBirth = yearOfBirth;
            this.name = name;
        }


}
