package com.example.labjee.repositories;

import com.example.labjee.models.Country;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, String> {
    List<Country> findAllByOrderByNameAsc();
}
