package com.newyeti.springboot.multitenancy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.newyeti.springboot.multitenancy.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
