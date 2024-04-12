package com.example.labjee.models;

import com.example.labjee.interfaces.MovieRelationship;
import com.example.labjee.primarykeys.MovieGenrePK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie_genres")
@NoArgsConstructor
public class MovieGenre extends MovieRelationship {
    public MovieGenre(Movie movie, Genre genre) {
        this.movie = movie;
        this.genre = genre;
    }
    
    @EmbeddedId
    private MovieGenrePK id = new MovieGenrePK();
    
    @ManyToOne
    @MapsId("movie")
    @Getter
    private Movie movie;
    
    @ManyToOne
    @MapsId("genre")
    @Getter
    private Genre genre;
}
