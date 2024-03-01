package com.example.labjee.services;

import com.example.labjee.models.Country;
import com.example.labjee.repositories.CountryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;
    
    public Country createOrUpdate(Country country) {
        return countryRepository.save(country);
    }
    
    public List<Country> getAll() {
        return countryRepository.findAllByOrderByNameAsc();
    }
    
    public Country getByCode(String code) {
        if (countryRepository.existsById(code)) {
            return countryRepository.findById(code).get();
        }
        
        return null;
    }
}
