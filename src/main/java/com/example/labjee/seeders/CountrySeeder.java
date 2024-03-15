package com.example.labjee.seeders;

import com.example.labjee.models.Country;
import com.example.labjee.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// Tydzień 3 - wzorzec Composite - klasa będąca liściem interfejsu Seeder, odpowiedzialnym za wypełnianie tablicy krajów
@Component
public class CountrySeeder implements Seeder {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public void seed() {
        if (this.countryRepository.count() == 0) {
            Country country_1 = new Country("ARG", "Argentyna");
            Country country_2 = new Country("AUS", "Australia");
            Country country_3 = new Country("BEL", "Belgia");
            Country country_4 = new Country("BRA", "Brazylia");
            Country country_5 = new Country("CAN", "Kanada");
            Country country_6 = new Country("CHI", "Chile");
            Country country_7 = new Country("CHN", "Chiny");
            Country country_8 = new Country("CZE", "Czechy");
            Country country_9 = new Country("DEN", "Dania");
            Country country_10 = new Country("ESP", "Hiszpania");
            Country country_11 = new Country("FIN", "Finlandia");
            Country country_12 = new Country("FRA", "Francja");
            Country country_13 = new Country("GBR", "Wielka Brytania");
            Country country_14 = new Country("GER", "Niemcy");
            Country country_15 = new Country("GRE", "Grecja");
            Country country_16 = new Country("HUN", "Węgry");
            Country country_17 = new Country("IND", "Indie");
            Country country_18 = new Country("IRL", "Irlandia");
            Country country_19 = new Country("ISL", "Islandia");
            Country country_20 = new Country("ITA", "Włochy");
            Country country_21 = new Country("JPN", "Japonia");
            Country country_22 = new Country("KOR", "Korea Południowa");
            Country country_23 = new Country("KSA", "Arabia Saudyjska");
            Country country_24 = new Country("MEX", "Meksyk");
            Country country_25 = new Country("NED", "Holandia");
            Country country_26 = new Country("NOR", "Norwegia");
            Country country_27 = new Country("POL", "Polska");
            Country country_28 = new Country("POR", "Portugalia");
            Country country_29 = new Country("ROU", "Rumunia");
            Country country_30 = new Country("RSA", "Republika Południowej Afryki");
            Country country_31 = new Country("SWE", "Szwecja");
            Country country_32 = new Country("TUR", "Turcja");
            Country country_33 = new Country("USA", "Stany Zjednoczone");
            
            this.countryRepository.save(country_1);
            this.countryRepository.save(country_2);
            this.countryRepository.save(country_3);
            this.countryRepository.save(country_4);
            this.countryRepository.save(country_5);
            this.countryRepository.save(country_6);
            this.countryRepository.save(country_7);
            this.countryRepository.save(country_8);
            this.countryRepository.save(country_9);
            this.countryRepository.save(country_10);
            this.countryRepository.save(country_11);
            this.countryRepository.save(country_12);
            this.countryRepository.save(country_13);
            this.countryRepository.save(country_14);
            this.countryRepository.save(country_15);
            this.countryRepository.save(country_16);
            this.countryRepository.save(country_17);
            this.countryRepository.save(country_18);
            this.countryRepository.save(country_19);
            this.countryRepository.save(country_20);
            this.countryRepository.save(country_21);
            this.countryRepository.save(country_22);
            this.countryRepository.save(country_23);
            this.countryRepository.save(country_24);
            this.countryRepository.save(country_25);
            this.countryRepository.save(country_26);
            this.countryRepository.save(country_27);
            this.countryRepository.save(country_28);
            this.countryRepository.save(country_29);
            this.countryRepository.save(country_30);
            this.countryRepository.save(country_31);
            this.countryRepository.save(country_32);
            this.countryRepository.save(country_33);
        }
    }
}
// Tydzień 3 - wzorzec Composite - koniec