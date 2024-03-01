package com.example.labjee.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {
    @Id
    @Getter
    @Setter
    @NotBlank(message = "Nazwa użytkownika nie może być pusta")
    @NotNull(message = "Nazwa użytkownika jest obowiązkowa")
    @Size(min = 3, max = 50, message = "Nazwa użytkownika musi mieć od 3 do 50 znaków")
    @Pattern(regexp = "^[a-zA-Z0-9_-]{3,50}$", message = "Nazwa użytkownika nie może zawierać spacji oraz znaków specjalnych poza myślnikiem i podkreślnikiem")
    private String username;

    @Column
    @Getter
    @Setter
    @NotBlank(message = "Imię nie może być puste")
    @NotNull(message = "Imię jest obowiązkowe")
    @Size(min = 2, max = 100, message = "Imię musi mieć od 2 do 100 znaków")
    private String name;

    @Column
    @Getter
    @Setter
    @NotBlank(message = "Nazwisko nie może być puste")
    @NotNull(message = "Nazwisko jest obowiązkowe")
    @Size(min = 2, max = 200, message = "Nazwisko musi mieć od 2 do 200 znaków")
    private String surname;

    @Column(unique = true)
    @Getter
    @Setter
    @NotBlank(message = "Adres e-mail nie może być pusty")
    @NotNull(message = "Adres e-mail jest obowiązkowy")
    @Size(min = 6, max = 100, message = "Adres e-mail musi mieć od 6 do 100 znaków")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "Adres e-mail musi spełniać wytyczne dotyczące jego formatu, m.in. zawierać znak małpy i domenę adresu")
    private String email;

    @Column
    @Getter
    @Setter
    @NotBlank(message = "Hasło nie może być puste")
    @NotNull(message = "Hasło jest obowiązkowe")
    @Size(min = 8, message = "Hasło musi mieć co najmniej 8 znaków")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$", message = "Hasło musi zawierać co najmniej 1 małą literę, 1 dużą literę i 1 cyfrę i nie może zawierać spacji")
    private String password;
    
    @Column(columnDefinition = "MEDIUMBLOB", length = 1048576)
    @Getter
    @Setter
    @Lob
    private byte[] profilePicture;
    
    @Column
    @Getter
    @Setter
    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date creationDate;
    
    @OneToMany(mappedBy = "user")
    @Getter
    private List<Movie> addedMovies = new ArrayList<>();
    
    public void addMovie(Movie movie) {
        this.addedMovies.add(movie);
    }
    
    public void deleteMovie(Movie movie) {
        this.addedMovies.remove(movie);
    }
    
    @OneToMany(mappedBy = "user")
    @Getter
    private List<Person> addedPersons = new ArrayList<>();
    
    public void addPerson(Person person) {
        this.addedPersons.add(person);
    }
    
    public void deletePerson(Person person) {
        this.addedPersons.remove(person);
    }
    
    @OneToMany(mappedBy = "user")
    @Getter
    List<MovieRating> ratings = new ArrayList<>();
    
    public void addRating(MovieRating rating) {
        this.ratings.add(rating);
    }

    public void deleteRating(MovieRating rating) {
        this.ratings.remove(rating);
    }
}
