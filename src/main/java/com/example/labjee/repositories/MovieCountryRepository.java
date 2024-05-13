package com.example.labjee.repositories;

import com.example.labjee.models.MovieCountry;
import com.example.labjee.primarykeys.MovieCountryPrimaryKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCountryRepository extends JpaRepository<MovieCountry, MovieCountryPrimaryKey> {
    MovieCountry findByIdMovieAndIdCountry(int movie, String country);
    
    List<MovieCountry> findByIdMovie(int movie);
    
    List<MovieCountry> findByIdCountry(String country);
}
