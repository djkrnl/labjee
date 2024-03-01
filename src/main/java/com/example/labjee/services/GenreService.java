package com.example.labjee.services;

import com.example.labjee.models.Genre;
import com.example.labjee.repositories.GenreRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreService {
    @Autowired
    private GenreRepository genreRepository;
    
    public Genre createOrUpdate(Genre genre) {
        return genreRepository.save(genre);
    }
    
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }
    
    public Genre getByName(String name) {
        if (genreRepository.existsById(name)) {
            return genreRepository.findById(name).get();
        }
        
        return null;
    }
}
