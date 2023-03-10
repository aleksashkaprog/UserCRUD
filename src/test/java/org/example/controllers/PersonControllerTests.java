package org.example.controllers;

import org.assertj.core.api.Assertions;
import org.example.models.Person;
import org.example.models.PersonRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTests {

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void addPersonTest() {
        Person person = new Person();
        person.setName("Klava");
        person.setPassword("Koka228");

        personRepository.save(person);

        Assertions.assertThat(person.getId()).isGreaterThan(0);

    }

    @Test
    @Order(2)
    public void getUserTest() {
        Person person = personRepository.findById(1).get();

        Assertions.assertThat(person.getId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    public void listTest() {
        List<Person> people = (List<Person>) personRepository.findAll();

        Assertions.assertThat(people.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void putUserTest() {
        Person person = personRepository.findById(1).get();

        person.setName("Klaudia");

       Person personUpdated =  personRepository.save(person);

        Assertions.assertThat(personUpdated.getName()).isEqualTo("Klaudia");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteUserTest() {

        Person person = personRepository.findById(1).get();

        personRepository.delete(person);


        Person person1 = null;

        Optional<Person> optionalUser = personRepository.findById(1);

        if(optionalUser.isPresent()){
            person1 = optionalUser.get();
        }

        Assertions.assertThat(person1).isNull();
    }
}