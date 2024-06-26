package com.example.labjee.models;

import com.example.labjee.interfaces.MovieInterface;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import static com.example.labjee.config.Constants.MAX_SIZE_LIMIT;

@Entity
@Table(name = "movies")
@NoArgsConstructor
public class Movie implements MovieInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;
    
    @Column
    @Getter
    @Setter
    @NotBlank(message = "Tytuł nie może być pusty")
    @NotNull(message = "Tytuł jest obowiązkowy")
    @Size(min = 1, max = 500, message = "Tytuł musi mieć od 1 do 500 znaków")
    private String title;
    
    @Column
    @Getter
    @Setter
    @NotBlank(message = "Tytuł oryginalny nie może być pusty")
    @NotNull(message = "Tytuł oryginalny jest obowiązkowy")
    @Size(min = 1, max = 500, message = "Tytuł oryginalny musi mieć od 1 do 500 znaków")
    private String originalTitle;
    
    @OneToMany(mappedBy = "movie")
    @Getter
    // @OnDelete(action = OnDeleteAction.CASCADE)
    List<MovieDirector> directors = new ArrayList<>();

    public void addDirector(MovieDirector movieDirector) {
        this.directors.add(movieDirector);
    }

    public void deleteDirector(MovieDirector movieDirector) {
        this.directors.remove(movieDirector);
    }
    
    @OneToMany(mappedBy = "movie")
    @Getter
    // @OnDelete(action = OnDeleteAction.CASCADE)
    List<MovieWriter> writers = new ArrayList<>();

    public void addWriter(MovieWriter movieWriter) {
        this.writers.add(movieWriter);
    }

    public void deleteWriter(MovieWriter movieWriter) {
        this.writers.remove(movieWriter);
    }
    
    @OneToMany(mappedBy = "movie")
    @Getter
    // @OnDelete(action = OnDeleteAction.CASCADE)
    List<MovieActor> actors = new ArrayList<>();

    public void addActor(MovieActor movieActor) {
        this.actors.add(movieActor);
    }

    public void deleteActor(MovieActor movieActor) {
        this.actors.remove(movieActor);
    }
    
    @OneToMany(mappedBy = "movie")
    @Getter
    // @OnDelete(action = OnDeleteAction.CASCADE)
    List<MovieGenre> genres = new ArrayList<>();

    public List<Genre> getRawGenres() {
        return this.genres.stream().map(MovieGenre::getGenre).collect(Collectors.toList());
    }

    public void addGenre(MovieGenre movieGenre) {
        this.genres.add(movieGenre);
    }

    public void deleteGenre(MovieGenre movieGenre) {
        this.genres.remove(movieGenre);
    }
    
    @OneToMany(mappedBy = "movie")
    @Getter
    // @OnDelete(action = OnDeleteAction.CASCADE)
    List<MovieCountry> countries = new ArrayList<>();

    public List<Country> getRawCountries() {
        return this.countries.stream().map(MovieCountry::getCountry).collect(Collectors.toList());
    }

    public void addCountry(MovieCountry movieCountry) {
        this.countries.add(movieCountry);
    }

    public void deleteCountry(MovieCountry movieCountry) {
        this.countries.remove(movieCountry);
    }
    
    @Column
    @Getter
    @Setter
    @NotNull(message = "Data premiery jest obowiązkowa")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    
    @Column
    @Getter
    @Setter
    @NotNull(message = "Czas trwania jest obowiązkowy")
    @Min(value = 0, message = "Czas trwania nie może być ujemny")
    private int runtime;

    @Getter
    @Setter
    @NotNull(message = "Czas trwania jest obowiązkowy")
    @Transient
    private String runtimeStr;
    
    @Column(columnDefinition = "MEDIUMBLOB", length = MAX_SIZE_LIMIT)
    @Getter
    @Setter
    @Lob
    private byte[] poster;
    
    @Column
    @Getter
    @Setter
    @NotNull(message = "Opis jest obowiązkowy")
    @Size(min = 10, max = 1000, message = "Opis musi mieć od 10 do 1000 znaków")
    private String description;
    
    @Column
    @Getter
    @Setter
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    private Date creationDate;
    
    @ManyToOne
    @JoinColumn
    @Getter
    @Setter
    private User user;
}
