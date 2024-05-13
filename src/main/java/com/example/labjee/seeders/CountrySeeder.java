package com.example.labjee.seeders;

import com.example.labjee.models.Country;
import com.example.labjee.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// Tydzień 3 - wzorzec Composite - klasa będąca liściem interfejsu Seeder, odpowiedzialnym za wypełnianie tablicy krajów
// Tydzień 7 - zasada otwarte-zamknięte
@Component
public class CountrySeeder implements Seeder {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public void seed() {
        List<Country> countries = new ArrayList<>();

        countries.addAll(List.of(new Country("ARG", "Argentyna"), new Country("AUS", "Australia"), new Country("BEL", "Belgia"), new Country("BRA", "Brazylia"), new Country("CAN", "Kanada"),
                new Country("CHI", "Chile"), new Country("CHN", "Chiny"), new Country("CZE", "Czechy"), new Country("DEN", "Dania"), new Country("ESP", "Hiszpania"),
                new Country("FIN", "Finlandia"), new Country("FRA", "Francja"), new Country("GBR", "Wielka Brytania"), new Country("GER", "Niemcy"), new Country("GRE", "Grecja"),
                new Country("HUN", "Węgry"), new Country("IND", "Indie"), new Country("IRL", "Irlandia"), new Country("ISL", "Islandia"), new Country("ITA", "Włochy"),
                new Country("JPN", "Japonia"), new Country("KOR", "Korea Południowa"), new Country("KSA", "Arabia Saudyjska"), new Country("MEX", "Meksyk"), new Country("NED", "Holandia"),
                new Country("NOR", "Norwegia"), new Country("POL", "Polska"), new Country("POR", "Portugalia"), new Country("ROU", "Rumunia"), new Country("RSA", "Republika Południowej Afryki"),
                new Country("SWE", "Szwecja"), new Country("TUR", "Turcja"), new Country("USA", "Stany Zjednoczone")));

        this.countryRepository.saveAll(countries);
    }
}
// Tydzień 3 - wzorzec Composite - koniec