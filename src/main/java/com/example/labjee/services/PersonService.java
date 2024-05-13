package com.example.labjee.services;

import com.example.labjee.models.Person;
import com.example.labjee.repositories.PersonRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;
    
    public Person createOrUpdate(Person person) {
        return personRepository.save(person);
    }
    
    public List<Person> getAll() {
        return personRepository.findAllByOrderByNameAscSurnameAsc();
    }
    
    public List<Person> getAllNewest() {
        return personRepository.findAllByOrderByCreationDateDesc();
    }
    
    public List<Person> getNewest() {
        return personRepository.findTop6ByOrderByCreationDateDesc();
    }
    
    public Person getById(int id) {
        if (personRepository.existsById(id)) {
            return personRepository.findById(id).get();
        }
        
        return null;
    }
    
    public List<Person> getNewestByUser(String username) {
        return personRepository.findTop6ByUserUsernameOrderByCreationDateDesc(username);
    }
    
    public void delete(int id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        }
    }
}
