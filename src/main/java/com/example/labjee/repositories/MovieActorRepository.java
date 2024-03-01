package com.example.labjee.repositories;

import com.example.labjee.models.MovieActor;
import com.example.labjee.primarykeys.MovieActorPK;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieActorRepository extends JpaRepository<MovieActor, MovieActorPK> {
    MovieActor findByIdMovieAndIdActor(int movie, int actor);
    
    List<MovieActor> findByIdMovie(int movie);
    
    List<MovieActor> findByIdActor(int actor);
}
