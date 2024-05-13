package com.example.labjee.models;

import com.example.labjee.interfaces.MovieRelationship;
import com.example.labjee.primarykeys.MovieDirectorPrimaryKey;
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
public class MovieDirector extends MovieRelationship {
    public MovieDirector(Movie movie, Person director) {
        this.movie = movie;
        this.director = director;
    }
    
    @EmbeddedId
    private MovieDirectorPrimaryKey id = new MovieDirectorPrimaryKey();
    
    @ManyToOne
    @MapsId("movie")
    @Getter
    private Movie movie;
    
    @ManyToOne
    @MapsId("director")
    @Getter
    private Person director;
}
