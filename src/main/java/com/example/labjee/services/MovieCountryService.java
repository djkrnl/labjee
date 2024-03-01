package com.example.labjee.services;

import com.example.labjee.models.Country;
import com.example.labjee.models.Movie;
import com.example.labjee.models.MovieCountry;
import com.example.labjee.repositories.MovieCountryRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieCountryService {
    @Autowired
    private MovieCountryRepository movieCountryRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private CountryService countryService;

    public MovieCountry linkCountryToMovie(Movie movie, Country country) {
        if (movieService.getById(movie.getId()) != null && countryService.getByCode(country.getCode()) != null) {
            MovieCountry movieCountry = new MovieCountry(movie, country);
            
            return movieCountryRepository.save(movieCountry);
        }

        return null;
    }

    public MovieCountry getLink(int movieId, String countryId) {
        if (movieService.getById(movieId) != null && countryService.getByCode(countryId) != null) {
            return movieCountryRepository.findByIdMovieAndIdCountry(movieId, countryId);
        }

        return null;
    }

    public List<MovieCountry> getLinksByMovie(int movieId) {
        return movieCountryRepository.findByIdMovie(movieId);
    }

    public List<MovieCountry> getLinksByCountry(String countryId) {
        return movieCountryRepository.findByIdCountry(countryId);
    }

    public int deleteLink(int movieId, String countryId) {
        if (movieService.getById(movieId) != null && countryService.getByCode(countryId) != null) {
            MovieCountry movieCountry = this.getLink(movieId, countryId);

            if (movieCountry != null) {
                movieCountryRepository.delete(movieCountry);

                return 0;
            }

            return 1;
        }

        return 2;
    }

    public int deleteLink(MovieCountry movieCountry) {
        if (this.getLink(movieCountry.getMovie().getId(), movieCountry.getCountry().getCode()) != null) {
            if (movieService.getById(movieCountry.getMovie().getId()) != null && countryService.getByCode(movieCountry.getCountry().getCode()) != null) {
                Country country = countryService.getByCode(movieCountry.getCountry().getCode());
                country.deleteMovie(movieCountry);
                countryService.createOrUpdate(country);

                movieCountryRepository.delete(movieCountry);

                return 0;
            }

            return 1;
        }

        return 2;
    }
}
