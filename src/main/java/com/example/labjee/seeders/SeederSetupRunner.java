package com.example.labjee.seeders;

import com.example.labjee.repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

// Tydzień 6 - wzorzec State - zastosowanie 1
@Component
public class SeederSetupRunner implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    SeederSetup seederSetup;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        seederSetup.seed();
    }
}
// Tydzień 6 - wzorzec State - zastosowanie 1 - koniec