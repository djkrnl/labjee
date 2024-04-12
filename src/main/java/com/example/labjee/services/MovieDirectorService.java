package com.example.labjee.services;

import com.example.labjee.helpers.visitor.ServiceElement;
import com.example.labjee.helpers.visitor.Visitor;
import com.example.labjee.interfaces.MovieRelationship;
import com.example.labjee.models.Movie;
import com.example.labjee.models.MovieActor;
import com.example.labjee.models.MovieDirector;
import com.example.labjee.models.Person;
import com.example.labjee.repositories.MovieDirectorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieDirectorService implements ServiceElement {

    @Autowired
    private MovieDirectorRepository movieDirectorRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private PersonService personService;

    public MovieDirector linkDirectorToMovie(Movie movie, Person director) {
        if (movieService.getById(movie.getId()) != null && personService.getById(director.getId()) != null) {
            MovieDirector movieDirector = new MovieDirector(movie, director);
            
            return movieDirectorRepository.save(movieDirector);
        }

        return null;
    }

    public MovieDirector getLink(int movieId, int directorId) {
        if (movieService.getById(movieId) != null && personService.getById(directorId) != null) {
            return movieDirectorRepository.findByIdMovieAndIdDirector(movieId, directorId);
        }

        return null;
    }

    public List<MovieDirector> getLinksByMovie(int movieId) {
        return movieDirectorRepository.findByIdMovie(movieId);
    }

    public List<MovieDirector> getLinksByDirector(int directorId) {
        return movieDirectorRepository.findByIdDirector(directorId);
    }

    public int deleteLink(int movieId, int directorId) {
        if (movieService.getById(movieId) != null && personService.getById(directorId) != null) {
            MovieDirector movieDirector = this.getLink(movieId, directorId);

            if (movieDirector != null) {
                movieDirectorRepository.delete(movieDirector);

                return 0;
            }

            return 1;
        }

        return 2;
    }

    public int deleteLink(MovieRelationship movieRelationship) {
        if (movieRelationship instanceof MovieDirector movieDirector) {
            if (this.getLink(movieDirector.getMovie().getId(), movieDirector.getDirector().getId()) != null) {
                if (movieService.getById(movieDirector.getMovie().getId()) != null && personService.getById(movieDirector.getDirector().getId()) != null) {
                    Person person = personService.getById(movieDirector.getDirector().getId());
                    person.deleteMovieDirector(movieDirector);
                    personService.createOrUpdate(person);

                    movieDirectorRepository.delete(movieDirector);

                    return 0;
                }

                return 1;
            }

            return 2;
        }
        return 3;
    }

    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    public void accept(Visitor visitor, MovieRelationship relationship) {
        visitor.visit(this, relationship);
    }
}
