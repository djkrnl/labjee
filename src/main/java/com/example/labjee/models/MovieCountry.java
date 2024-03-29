package com.example.labjee.models;

import com.example.labjee.primarykeys.MovieCountryPK;
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
public class MovieCountry {
    public MovieCountry(Movie movie, Country country) {
        this.movie = movie;
        this.country = country;
    }
    
    @EmbeddedId
    private MovieCountryPK id = new MovieCountryPK();
    
    @ManyToOne
    @MapsId("movie")
    @Getter
    private Movie movie;
    
    @ManyToOne
    @MapsId("country")
    @Getter
    private Country country;
}
