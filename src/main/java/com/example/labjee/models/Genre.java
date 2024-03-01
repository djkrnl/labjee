package com.example.labjee.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "genres")
@NoArgsConstructor
public class Genre {
    public Genre(String name) {
        this.name = name;
    }
    
    @Id
    @Getter
    @NotBlank(message = "Nazwa gatunku nie może być pusta")
    @NotNull(message = "Nazwa gatunku jest obowiązkowa")
    @Size(min = 3, max = 100, message = "Nazwa gatunku musi mieć od 3 do 100 znaków")
    private String name;
    
    @OneToMany(mappedBy = "genre")
    @Getter
    private List<MovieGenre> movies = new ArrayList<>();
    
    public void addMovie(MovieGenre movieGenre) {
        this.movies.add(movieGenre);
    }
    
    public void deleteMovie(MovieGenre movieGenre) {
        this.movies.remove(movieGenre);
    }
}
