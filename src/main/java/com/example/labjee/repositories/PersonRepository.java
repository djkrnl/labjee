package com.example.labjee.repositories;

import com.example.labjee.models.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    List<Person> findByUserUsername(String username);
    
    List<Person> findTop6ByUserUsernameOrderByCreationDateDesc(String username);
    
    List<Person> findTop6ByOrderByCreationDateDesc();
    
    List<Person> findAllByOrderByNameAscSurnameAsc();
    
    List<Person> findAllByOrderByCreationDateDesc();
}
