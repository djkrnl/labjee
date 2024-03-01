package com.example.labjee.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "countries")
@NoArgsConstructor
public class Country {
    public Country(String code, String name) {
        this.code = code;
        this.name = name;
    }
    
    @Id
    @Getter
    @NotBlank(message = "Kod kraju nie może być pusty")
    @NotNull(message = "Kod kraju jest obowiązkowy")
    @Pattern(regexp = "^[A-Z]{3}$", message = "Kod kraju musi składać się z dokładnie 3 dużych liter")
    private String code;
    
    @Column
    @Getter
    @NotBlank(message = "Nazwa kraju nie może być pusta")
    @NotNull(message = "Nazwa kraju jest obowiązkowa")
    @Size(min = 3, max = 200, message = "Nazwa kraju musi mieć od 3 do 200 znaków")
    private String name;
    
    @OneToMany(mappedBy = "country")
    @Getter
    private List<MovieCountry> movies = new ArrayList<>();
    
    public void addMovie(MovieCountry movieCountry) {
        this.movies.add(movieCountry);
    }
    
    public void deleteMovie(MovieCountry movieCountry) {
        this.movies.remove(movieCountry);
    }
    
    @OneToMany(mappedBy = "originCountry")
    @Getter
    private List<Person> persons;
    
    public void addPerson(Person person) {
        this.persons.add(person);
    }
    
    public void deletePerson(Person person) {
        this.persons.remove(person);
    }
}
