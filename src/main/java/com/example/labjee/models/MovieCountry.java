package com.example.labjee.models;

import com.example.labjee.interfaces.MovieRelationship;
import com.example.labjee.primarykeys.MovieCountryPrimaryKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie_countries")
@NoArgsConstructor
public class MovieCountry extends MovieRelationship {
    public MovieCountry(Movie movie, Country country) {
        this.movie = movie;
        this.country = country;
    }
    
    @EmbeddedId
    private MovieCountryPrimaryKey id = new MovieCountryPrimaryKey();
    
    @ManyToOne
    @MapsId("movie")
    @Getter
    private Movie movie;
    
    @ManyToOne
    @MapsId("country")
    @Getter
    private Country country;
}
