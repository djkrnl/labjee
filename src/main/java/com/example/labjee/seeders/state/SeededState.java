package com.example.labjee.seeders.state;

import com.example.labjee.seeders.*;
import org.springframework.stereotype.Component;

// Tydzień 6 - wzorzec State - klasa stanu wypełnionego dla SeederSetup, metoda seed() informuje o tym, że tabele są już wypełnione
@Component
public class SeededState implements SeederSetupState {
    SeederSetup seederSetup;

    public SeededState(SeederSetup seederSetup) {
        this.seederSetup = seederSetup;
    }

    @Override
    public void seed() {
        System.out.println("Seeding already completed");
    }
}
// Tydzień 6 - wzorzec State - koniec
