package com.example.labjee.repositories;

import com.example.labjee.models.Movie;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    List<Movie> findByUserUsername(String username);
    
    List<Movie> findTop6ByUserUsernameOrderByCreationDateDesc(String username);
    
    List<Movie> findTop6ByOrderByCreationDateDesc();
    
    List<Movie> findAllByOrderByTitleAsc();
    
    List<Movie> findAllByOrderByCreationDateDesc();
}
