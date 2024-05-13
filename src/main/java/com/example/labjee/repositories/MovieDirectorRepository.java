package com.example.labjee.repositories;

import com.example.labjee.models.MovieDirector;
import com.example.labjee.primarykeys.MovieDirectorPrimaryKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieDirectorRepository extends JpaRepository<MovieDirector, MovieDirectorPrimaryKey> {
    MovieDirector findByIdMovieAndIdDirector(int movie, int director);
    
    List<MovieDirector> findByIdMovie(int movie);
    
    List<MovieDirector> findByIdDirector(int director);
}
