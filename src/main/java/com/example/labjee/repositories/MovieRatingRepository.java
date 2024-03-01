package com.example.labjee.repositories;

import com.example.labjee.models.MovieRating;
import com.example.labjee.primarykeys.MovieRatingPK;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface MovieRatingRepository extends JpaRepository<MovieRating, MovieRatingPK> {
    MovieRating findByIdMovieAndIdUser(int movie, String user);
    
    List<MovieRating> findByIdMovie(int movie);
    
    List<MovieRating> findByIdUser(String user);
    
    /*
    @Query("SELECT AVG(rating) FROM movie_ratings WHERE movie = :movieId")
    Double average(@Param("movieId") int movieId);
    */
}
