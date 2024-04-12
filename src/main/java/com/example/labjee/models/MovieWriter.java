package com.example.labjee.models;

import com.example.labjee.interfaces.MovieRelationship;
import com.example.labjee.primarykeys.MovieWriterPK;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie_writers")
@NoArgsConstructor
public class MovieWriter extends MovieRelationship {
    public MovieWriter(Movie movie, Person writer) {
        this.movie = movie;
        this.writer = writer;
    }
    
    @EmbeddedId
    private MovieWriterPK id = new MovieWriterPK();
    
    @ManyToOne
    @MapsId("movie")
    @Getter
    private Movie movie;
    
    @ManyToOne
    @MapsId("writer")
    @Getter
    private Person writer;
}
