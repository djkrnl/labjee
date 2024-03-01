package com.example.labjee.models;

import com.example.labjee.primarykeys.MovieDirectorPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie_directors")
@NoArgsConstructor
public class MovieDirector {
    public MovieDirector(Movie movie, Person director) {
        this.movie = movie;
        this.director = director;
    }
    
    @EmbeddedId
    private MovieDirectorPK id = new MovieDirectorPK();
    
    @ManyToOne
    @MapsId("movie")
    @Getter
    private Movie movie;
    
    @ManyToOne
    @MapsId("director")
    @Getter
    private Person director;
}
