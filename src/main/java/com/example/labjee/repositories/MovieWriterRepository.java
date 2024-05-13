package com.example.labjee.repositories;

import com.example.labjee.models.MovieWriter;
import com.example.labjee.primarykeys.MovieWriterPrimaryKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieWriterRepository extends JpaRepository<MovieWriter, MovieWriterPrimaryKey> {
    MovieWriter findByIdMovieAndIdWriter(int movie, int writer);
    
    List<MovieWriter> findByIdMovie(int movie);
    
    List<MovieWriter> findByIdWriter(int writer);
}
