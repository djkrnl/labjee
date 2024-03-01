package com.example.labjee.seeders;

import com.example.labjee.models.Country;
import com.example.labjee.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CountrySeeder implements CommandLineRunner {
    @Autowired
    CountryRepository countryRepository;

    @Override
    public void run(String... args) throws Exception {
        seed();
    }

    private void seed() {
        if (countryRepository.count() == 0) {
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
            
            countryRepository.save(country_1);
            countryRepository.save(country_2);
            countryRepository.save(country_3);
            countryRepository.save(country_4);
            countryRepository.save(country_5);
            countryRepository.save(country_6);
            countryRepository.save(country_7);
            countryRepository.save(country_8);
            countryRepository.save(country_9);
            countryRepository.save(country_10);
            countryRepository.save(country_11);
            countryRepository.save(country_12);
            countryRepository.save(country_13);
            countryRepository.save(country_14);
            countryRepository.save(country_15);
            countryRepository.save(country_16);
            countryRepository.save(country_17);
            countryRepository.save(country_18);
            countryRepository.save(country_19);
            countryRepository.save(country_20);
            countryRepository.save(country_21);
            countryRepository.save(country_22);
            countryRepository.save(country_23);
            countryRepository.save(country_24);
            countryRepository.save(country_25);
            countryRepository.save(country_26);
            countryRepository.save(country_27);
            countryRepository.save(country_28);
            countryRepository.save(country_29);
            countryRepository.save(country_30);
            countryRepository.save(country_31);
            countryRepository.save(country_32);
            countryRepository.save(country_33);
        }
    }
}
