package com.example.labjee.services;

import com.example.labjee.helpers.visitor.ServiceElement;
import com.example.labjee.helpers.visitor.Visitor;
import com.example.labjee.interfaces.MovieRelationship;
import com.example.labjee.models.Genre;
import com.example.labjee.models.Movie;
import com.example.labjee.models.MovieCountry;
import com.example.labjee.models.MovieGenre;
import com.example.labjee.repositories.MovieGenreRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieGenreService implements ServiceElement {
    @Autowired
    private MovieGenreRepository movieGenreRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private GenreService genreService;

    public MovieGenre linkGenreToMovie(Movie movie, Genre genre) {
        if (movieService.getById(movie.getId()) != null && genreService.getByName(genre.getName()) != null) {
            MovieGenre movieGenre = new MovieGenre(movie, genre);
            
            return movieGenreRepository.save(movieGenre);
        }

        return null;
    }

    public MovieGenre getLink(int movieId, String genreId) {
        if (movieService.getById(movieId) != null && genreService.getByName(genreId) != null) {
            return movieGenreRepository.findByIdMovieAndIdGenre(movieId, genreId);
        }

        return null;
    }

    public List<MovieGenre> getLinksByMovie(int movieId) {
        return movieGenreRepository.findByIdMovie(movieId);
    }

    public List<MovieGenre> getLinksByGenre(String genreId) {
        return movieGenreRepository.findByIdGenre(genreId);
    }

    public int deleteLink(int movieId, String genreId) {
        if (movieService.getById(movieId) != null && genreService.getByName(genreId) != null) {
            MovieGenre movieGenre = this.getLink(movieId, genreId);

            if (movieGenre != null) {
                movieGenreRepository.delete(movieGenre);

                return 0;
            }

            return 1;
        }

        return 2;
    }

    public int deleteLink(MovieRelationship movieRelationship) {
        if (movieRelationship instanceof MovieGenre movieGenre) {

            if (this.getLink(movieGenre.getMovie().getId(), movieGenre.getGenre().getName()) != null) {
                if (movieService.getById(movieGenre.getMovie().getId()) != null && genreService.getByName(movieGenre.getGenre().getName()) != null) {
                    Genre genre = genreService.getByName(movieGenre.getGenre().getName());
                    genre.deleteMovie(movieGenre);
                    genreService.createOrUpdate(genre);

                    movieGenreRepository.delete(movieGenre);

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
