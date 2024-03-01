package com.example.labjee.models;

import com.example.labjee.primarykeys.MovieRatingPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import java.util.Date;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movie_ratings")
@NoArgsConstructor
public class MovieRating {
    public MovieRating(User user, Movie movie, int rating) {
        this.user = user;
        this.movie = movie;
        this.rating = rating;
    }
    
    @EmbeddedId
    private MovieRatingPK id = new MovieRatingPK();
    
    @ManyToOne
    @MapsId("user")
    @Getter
    private User user;

    @ManyToOne
    @MapsId("movie")
    @Getter
    private Movie movie;
    
    @Column
    @Getter
    @Setter
    @Min(1)
    @Max(10)
    private int rating;

    @Column
    @Getter
    @Setter
    private Date creationDate;
}
