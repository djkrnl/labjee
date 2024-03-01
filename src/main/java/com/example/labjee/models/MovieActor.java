package com.example.labjee.models;

import com.example.labjee.primarykeys.MovieActorPK;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Entity
@Table(name = "movie_actors")
@NoArgsConstructor
public class MovieActor {
    public MovieActor(Movie movie, Person actor, String role) {
        this.movie = movie;
        this.actor = actor;
        this.role = role;
    }
    
    @EmbeddedId
    private MovieActorPK id = new MovieActorPK();
    
    @ManyToOne
    @MapsId("movie")
    @Getter
    private Movie movie;
    
    @ManyToOne
    @MapsId("actor")
    @Getter
    private Person actor;
    
    @Column
    @NonNull
    @Getter
    @Setter
    @NotBlank(message = "Nazwa roli nie może być pusta")
    @NotNull(message = "Nazwa roli jest obowiązkowa")
    @Size(min = 1, max = 150, message = "Nazwa roli musi mieć od 1 do 150 znaków")
    private String role;
}
