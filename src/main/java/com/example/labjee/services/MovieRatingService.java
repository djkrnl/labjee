package com.example.labjee.services;

import com.example.labjee.models.Movie;
import com.example.labjee.models.MovieRating;
import com.example.labjee.models.User;
import com.example.labjee.repositories.MovieRatingRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieRatingService {
    @Autowired
    private MovieRatingRepository movieRatingRepository;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    public MovieRating rateMovie(Movie movie, User user, int rating) {
        if (movieService.getById(movie.getId()) != null && user != null) {
            MovieRating movieRating = new MovieRating(user, movie, rating);
            
            return movieRatingRepository.save(movieRating);
        }

        return null;
    }

    public MovieRating getRating(int movieId, String username) {
        if (movieService.getById(movieId) != null && userService.getByUsername(username) != null) {
            return movieRatingRepository.findByIdMovieAndIdUser(movieId, username);
        }

        return null;
    }

    /*
    public Double getAverageRating(int movieId) {
        if (movieService.getById(movieId) != null) {
            return movieRatingRepository.average(movieId);
        }
        
        return null;
    }
     */
    public List<MovieRating> getRatingsByMovie(int movieId) {
        Movie movie = movieService.getById(movieId);

        if (movie != null) {
            return movieRatingRepository.findByIdMovie(movieId);
        }

        return null;
    }

    public List<MovieRating> getRatingsByUser(String username) {
        User user = userService.getByUsername(username);

        if (user != null) {
            return movieRatingRepository.findByIdUser(user.getName());
        }

        return null;
    }

    public int unrateMovie(int movieId, String username) {
        User user = userService.getByUsername(username);

        if (movieService.getById(movieId) != null && user != null) {
            MovieRating movieRating = this.getRating(movieId, user.getName());

            if (movieRating != null) {
                movieRatingRepository.delete(movieRating);

                return 0;
            }

            return 1;
        }

        return 2;
    }

    public int unrateMovie(MovieRating movieRating) {
        if (this.getRating(movieRating.getMovie().getId(), movieRating.getUser().getUsername()) != null) {
            if (movieService.getById(movieRating.getMovie().getId()) != null && userService.getByUsername(movieRating.getUser().getUsername()) != null) {
                User user = userService.getByUsername(movieRating.getUser().getUsername());
                user.deleteRating(movieRating);
                userService.createOrUpdate(user, false);

                movieRatingRepository.delete(movieRating);

                return 0;
            }

            return 1;
        }

        return 2;
    }
}
