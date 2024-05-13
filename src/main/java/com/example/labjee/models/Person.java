package com.example.labjee.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import static com.example.labjee.config.Constants.MAX_SIZE_LIMIT;

@Entity
@Table(name = "persons")
@NoArgsConstructor
public class Person {  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private int id;
    
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
    @Size(max = 200, message = "Nazwisko musi mieć od 2 do 200 znaków")
    private String surname;
    
    @Column
    @Getter
    @Setter
    @NotNull(message = "Data urodzenia jest obowiązkowa")
    @Past(message = "Data urodzenia nie może być w przyszłości")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;
    
    @Column
    @Getter
    @Setter
    @Past(message = "Data śmierci nie może być w przyszłości")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deathDate;
    
    @ManyToOne
    @JoinColumn(nullable = false)
    @Getter
    @Setter
    private Country originCountry;
    
    @Column
    @Getter
    @Setter
    @NotNull(message = "Biografia jest obowiązkowa")
    @Size(min = 10, max = 1000, message = "Biografia musi mieć od 10 do 1000 znaków")
    private String biography;
    
    @Column(columnDefinition = "MEDIUMBLOB", length = MAX_SIZE_LIMIT)
    @Getter
    @Setter
    @Lob
    private byte[] picture;
    
    @Column
    @Getter
    @Setter
    @DateTimeFormat(pattern = "dd.MM.yyyy hh:mm:ss")
    private Date creationDate;
    
    @OneToMany(mappedBy = "director")
    @Getter
    List<MovieDirector> moviesAsDirector = new ArrayList<>();
    
    public void addMovieDirector(MovieDirector movieDirector) {
        this.moviesAsDirector.add(movieDirector);
    }

    public void deleteMovieDirector(MovieDirector movieDirector) {
        this.moviesAsDirector.remove(movieDirector);
    }
    
    @OneToMany(mappedBy = "writer")
    @Getter
    List<MovieWriter> moviesAsWriter = new ArrayList<>();
    
    public void addMovieWriter(MovieWriter movieWriter) {
        this.moviesAsWriter.add(movieWriter);
    }

    public void deleteMovieWriter(MovieWriter movieWriter) {
        this.moviesAsWriter.remove(movieWriter);
    }
    
    @OneToMany(mappedBy = "actor")
    @Getter
    List<MovieActor> moviesAsActor = new ArrayList<>();
    
    public void addMovieActor(MovieActor movieActor) {
        this.moviesAsActor.add(movieActor);
    }

    public void deleteMovieActor(MovieActor movieActor) {
        this.moviesAsActor.remove(movieActor);
    }
    
    @ManyToOne
    @JoinColumn
    @Getter
    @Setter
    private User user;
}
