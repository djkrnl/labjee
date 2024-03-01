package com.example.labjee.services;

import com.example.labjee.models.Movie;
import com.example.labjee.repositories.MovieRepository;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;
    
    public byte[] getBlankPoster() throws IOException {
        File file = new File("src/main/resources/images/blankMoviePoster.png");
        return Files.readAllBytes(file.toPath());
    }
    
    public Movie createOrUpdate(Movie movie) {
        return movieRepository.save(movie);
    }
    
    public List<Movie> getAll() {
        return movieRepository.findAllByOrderByTitleAsc();
    }
    
    public List<Movie> getAllNewest() {
        return movieRepository.findAllByOrderByCreationDateDesc();
    }
    
    public List<Movie> getNewest() {
        return movieRepository.findTop6ByOrderByCreationDateDesc();
    }
    
    public Movie getById(int id) {
        if (movieRepository.existsById(id)) {
            return movieRepository.findById(id).get();
        }
        
        return null;
    }
    
    public List<Movie> getNewestByUser(String username) {
        return movieRepository.findTop6ByUserUsernameOrderByCreationDateDesc(username);
    }
    
    public int delete(int id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            
            return 0;
        }
        
        return 1;
    }
}
