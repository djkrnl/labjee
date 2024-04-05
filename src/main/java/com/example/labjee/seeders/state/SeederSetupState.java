package com.example.labjee.seeders.state;

import org.springframework.stereotype.Component;

// Tydzień 6 - wzorzec State - interfejs dla klas stanów SeederSetup, zawierający definicję metody odpowiedzialnej za wypełnianie tabel
@Component
public interface SeederSetupState {
    void seed();
}
// Tydzień 6 - wzorzec State - koniec