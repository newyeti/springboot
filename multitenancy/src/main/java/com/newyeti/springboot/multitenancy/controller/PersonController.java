package com.newyeti.springboot.multitenancy.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.newyeti.springboot.multitenancy.dto.PersonDto;
import com.newyeti.springboot.multitenancy.entity.Person;
import com.newyeti.springboot.multitenancy.service.PersonService;

@RestController
public class PersonController {
  
  PersonService personService;

  public PersonController(PersonService personService) {
    this.personService = personService;
  }

  @GetMapping("/person/{id}")
  public Person get(@PathVariable Long id) {
    return personService.findById(id);
  }

  @GetMapping("/person/all")
  public List<Person> getAll() {
    return personService.findAll();
  }

  @PostMapping("/person")
  public Person create(@RequestBody PersonDto personDto){
    return personService.create(personDto);
  }

}
