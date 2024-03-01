package com.example.labjee.services;

import com.example.labjee.models.Movie;
import com.example.labjee.models.MovieWriter;
import com.example.labjee.models.Person;
import com.example.labjee.repositories.MovieWriterRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieWriterService {
    @Autowired
    private MovieWriterRepository movieWriterRepository;
    
    @Autowired
    private MovieService movieService;
    
    @Autowired
    private PersonService personService;
    
    public MovieWriter linkWriterToMovie(Movie movie, Person writer) {
        if (movieService.getById(movie.getId()) != null && personService.getById(writer.getId()) != null) {
            MovieWriter movieWriter = new MovieWriter(movie, writer);
            
            return movieWriterRepository.save(movieWriter);
        }
        
        return null;
    }
    
    public MovieWriter getLink(int movieId, int writerId) {
        if (movieService.getById(movieId) != null && personService.getById(writerId) != null) {
            return movieWriterRepository.findByIdMovieAndIdWriter(movieId, writerId);
        }
        
        return null;
    }
    
    public List<MovieWriter> getLinksByMovie(int movieId) {
        return movieWriterRepository.findByIdMovie(movieId);
    }
    
    public List<MovieWriter> getLinksByWriter(int writerId) {
        return movieWriterRepository.findByIdWriter(writerId);
    }
    
    public int deleteLink(int movieId, int writerId) {
        if (movieService.getById(movieId) != null && personService.getById(writerId) != null) {
            MovieWriter movieWriter = this.getLink(movieId, writerId);
            
            if (movieWriter != null) {
                movieWriterRepository.delete(movieWriter);
        
                return 0;
            }
            
            return 1;
        }
        
        return 2;
    }
    
    public int deleteLink(MovieWriter movieWriter) {
        if (this.getLink(movieWriter.getMovie().getId(), movieWriter.getWriter().getId()) != null) {
            if (movieService.getById(movieWriter.getMovie().getId()) != null && personService.getById(movieWriter.getWriter().getId()) != null) {
                Person person = personService.getById(movieWriter.getWriter().getId());
                person.deleteMovieWriter(movieWriter);
                personService.createOrUpdate(person);

                movieWriterRepository.delete(movieWriter);

                return 0;
            }

            return 1;
        }

        return 2;
    }
}
