package org.example.controllers;

import org.example.models.Person;
import org.example.models.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/users")
    public List<Person> list() {

        Iterable<Person> personIterable = personRepository.findAll();
        ArrayList<Person> people = new ArrayList<>();
        for(Person person : personIterable) {
            people.add(person);
        }

        return people;
    }

    @PostMapping( "/users")
    public int add (Person person) {

        Person newPerson = personRepository.save(person);
        return newPerson.getId();
    }


    @GetMapping("/users/{id}")
    public ResponseEntity get(@PathVariable int id) {

        Optional<Person> optionalPerson = personRepository.findById(id);
        if (!optionalPerson.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalPerson.get(), HttpStatus.OK);

    }

    @PutMapping("/users/{id}")
    public ResponseEntity put(@PathVariable int id, Person person) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (!optionalPerson.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Person updatePerson = personRepository.save(person);
        return new ResponseEntity(updatePerson, HttpStatus.OK);

    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity delete(@PathVariable int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (!optionalPerson.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        personRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Success delete");

    }
}
