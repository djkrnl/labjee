package com.example.labjee.repositories;

import com.example.labjee.models.MovieActor;
import com.example.labjee.primarykeys.MovieActorPrimaryKey;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieActorRepository extends JpaRepository<MovieActor, MovieActorPrimaryKey> {
    MovieActor findByIdMovieAndIdActor(int movie, int actor);
    
    List<MovieActor> findByIdMovie(int movie);
    
    List<MovieActor> findByIdActor(int actor);
}
