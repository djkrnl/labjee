package com.example.labjee.seeders;

import com.example.labjee.repositories.CountryRepository;
import com.example.labjee.repositories.GenreRepository;
import com.example.labjee.seeders.state.SeededState;
import com.example.labjee.seeders.state.SeederSetupState;
import com.example.labjee.seeders.state.UnseededState;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Tydzień 6 - wzorzec State - klasa SeederSetup została zmodyfikowana i teraz opiera się o utworzoną maszynę stanową w celu zdeterminowania, czy potrzebne jest wypełnienie tabel
@Component
public class SeederSetup {
    @Autowired
    GenreRepository genreRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    @Getter
    SeederSetupState unseededState;

    @Autowired
    @Getter
    SeederSetupState seededState;

    @Getter
    @Setter
    SeederSetupState state;

    @PostConstruct
    void init() {
        this.state = this.unseededState;
    }

    public void seed() {
        if (this.genreRepository.count() == 0 && this.countryRepository.count() == 0) {
            System.out.println("Database unseeded");
            this.state.seed();
        } else {
            System.out.println("Database already seeded");
            this.state = seededState;
        }
    }
}
// Tydzień 6 - wzorzec State - koniec