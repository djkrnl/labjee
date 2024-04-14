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
        List<Country> countryList = new ArrayList<>();

        countryList.add(new Country("ARG", "Argentyna"));
        countryList.add(new Country("AUS", "Australia"));
        countryList.add(new Country("BEL", "Belgia"));
        countryList.add(new Country("BRA", "Brazylia"));
        countryList.add(new Country("CAN", "Kanada"));
        countryList.add(new Country("CHI", "Chile"));
        countryList.add(new Country("CHN", "Chiny"));
        countryList.add(new Country("CZE", "Czechy"));
        countryList.add(new Country("DEN", "Dania"));
        countryList.add(new Country("ESP", "Hiszpania"));
        countryList.add(new Country("FIN", "Finlandia"));
        countryList.add(new Country("FRA", "Francja"));
        countryList.add(new Country("GBR", "Wielka Brytania"));
        countryList.add(new Country("GER", "Niemcy"));
        countryList.add(new Country("GRE", "Grecja"));
        countryList.add(new Country("HUN", "Węgry"));
        countryList.add(new Country("IND", "Indie"));
        countryList.add(new Country("IRL", "Irlandia"));
        countryList.add(new Country("ISL", "Islandia"));
        countryList.add(new Country("ITA", "Włochy"));
        countryList.add(new Country("JPN", "Japonia"));
        countryList.add(new Country("KOR", "Korea Południowa"));
        countryList.add(new Country("KSA", "Arabia Saudyjska"));
        countryList.add(new Country("MEX", "Meksyk"));
        countryList.add(new Country("NED", "Holandia"));
        countryList.add(new Country("NOR", "Norwegia"));
        countryList.add(new Country("POL", "Polska"));
        countryList.add(new Country("POR", "Portugalia"));
        countryList.add(new Country("ROU", "Rumunia"));
        countryList.add(new Country("RSA", "Republika Południowej Afryki"));
        countryList.add(new Country("SWE", "Szwecja"));
        countryList.add(new Country("TUR", "Turcja"));
        countryList.add(new Country("USA", "Stany Zjednoczone"));

        this.countryRepository.saveAll(countryList);
    }
}
// Tydzień 3 - wzorzec Composite - koniec