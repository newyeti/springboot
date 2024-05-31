package com.newyeti.springboot.multitenancy.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.newyeti.springboot.multitenancy.dto.PersonDto;
import com.newyeti.springboot.multitenancy.entity.Person;
import com.newyeti.springboot.multitenancy.repository.PersonRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonService {
  
  private PersonRepository personRepository;

  public PersonService(PersonRepository personRepository) {
    this.personRepository = personRepository;
  }

  @Transactional
  public Person create(PersonDto personDto) {
    Person person = new Person();
    person.setFirstName(personDto.getFirstName());
    person.setLastName(personDto.getLastName());
    personRepository.save(person);
    return person;
  }

  public Person findById(Long Id) {
    Optional<Person> person = personRepository.findById(Id);

    if (person.isPresent()) {
      return person.get();
    } else {
      throw new RuntimeException("Person not found");
    }
  }

  public List<Person> findAll() {
    return personRepository.findAll();
  }

}
