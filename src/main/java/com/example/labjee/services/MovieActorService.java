package com.example.labjee.services;

import com.example.labjee.helpers.visitor.ServiceElement;
import com.example.labjee.helpers.visitor.Visitor;
import com.example.labjee.interfaces.MovieRelationship;
import com.example.labjee.models.Movie;
import com.example.labjee.models.MovieActor;
import com.example.labjee.models.Person;
import com.example.labjee.repositories.MovieActorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieActorService implements ServiceElement {
    @Autowired
    private MovieActorRepository movieActorRepository;
    
    @Autowired
    private MovieService movieService;
    
    @Autowired
    private PersonService personService;
    
    public MovieActor linkActorToMovie(Movie movie, Person actor, String role) {
        if (movieService.getById(movie.getId()) != null && personService.getById(actor.getId()) != null) {
            MovieActor movieActor = new MovieActor(movie, actor, role);
            
            return movieActorRepository.save(movieActor);
        }
        
        return null;
    }
    
    public MovieActor getLink(int movieId, int actorId) {
        if (movieService.getById(movieId) != null && personService.getById(actorId) != null) {
            return movieActorRepository.findByIdMovieAndIdActor(movieId, actorId);
        }
        
        return null;
    }
    
    public List<MovieActor> getLinksByMovie(int movieId) {
        return movieActorRepository.findByIdMovie(movieId);
    }
    
    public List<MovieActor> getLinksByActor(int actorId) {
        return movieActorRepository.findByIdActor(actorId);
    }
    
    public MovieActor updateLink(MovieActor movieActor) {
        if (this.getLink(movieActor.getMovie().getId(), movieActor.getActor().getId()) != null) {
            return movieActorRepository.save(movieActor);
        }
        
        return null;
    }
    
    public int deleteLink(int movieId, int actorId) {
        if (movieService.getById(movieId) != null && personService.getById(actorId) != null) {
            MovieActor movieActor = this.getLink(movieId, actorId);
            
            if (movieActor != null) {
                movieActorRepository.delete(movieActor);
        
                return 0;
            }
            
            return 1;
        }
        
        return 2;
    }
    
    public int deleteLink(MovieRelationship movieRelationship) {
        if (movieRelationship instanceof MovieActor movieActor) {
            if (this.getLink(movieActor.getMovie().getId(), movieActor.getActor().getId()) != null) {
                if (movieService.getById(movieActor.getMovie().getId()) != null && personService.getById(movieActor.getActor().getId()) != null) {
                    Person person = personService.getById(movieActor.getActor().getId());
                    person.deleteMovieActor(movieActor);
                    personService.createOrUpdate(person);

                    movieActorRepository.delete(movieActor);

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
