package org.example.controllers;

import org.example.models.Person;
import org.example.models.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class PersonControllerUnitTest {

    private PersonController controller;
    private PersonRepository mockPersonRepository;

    @BeforeEach
    void setUp() {
        mockPersonRepository = mock(PersonRepository.class);
        controller = new PersonController(mockPersonRepository);
    }

    @Test
    void list_callPersonRepositoryFindAll() {
        // setup

        // act
        controller.list();

        // verify
        verify(mockPersonRepository).findAll();
    }

    @Test
    void list_checkResult(){
        // setup
        List<Person> list = new ArrayList<>();
        Person mockPerson1 = mock(Person.class);
        Person mockPerson2 = mock(Person.class);
        Person mockPerson3 = mock(Person.class);
        list.add(mockPerson1);
        list.add(mockPerson2);
        list.add(mockPerson3);
        when(mockPersonRepository.findAll()).thenReturn(list);

        // act
        List<Person> result = controller.list();

        // verify
        assertEquals(result.get(0), mockPerson1);
        assertEquals(result.get(1), mockPerson2);
        assertEquals(result.get(2), mockPerson3);
    }
}