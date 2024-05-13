package com.example.labjee.repositories;

import com.example.labjee.models.MovieGenre;
import com.example.labjee.primarykeys.MovieGenrePrimaryKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieGenreRepository extends JpaRepository<MovieGenre, MovieGenrePrimaryKey> {
    MovieGenre findByIdMovieAndIdGenre(int movie, String genre);
    
    List<MovieGenre> findByIdMovie(int movie);
    
    List<MovieGenre> findByIdGenre(String genre);
}
