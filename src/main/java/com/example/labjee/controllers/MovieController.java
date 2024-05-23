package com.example.labjee.controllers;

import com.example.labjee.helpers.FileValidator;
import com.example.labjee.helpers.articleSaver.abstraction.MovieArticle;
import com.example.labjee.helpers.articleSaver.implementation.MovieArticleSaver;
import com.example.labjee.helpers.BlankPictureFactory;
import com.example.labjee.helpers.interpreter.RuntimeExpressionParser;
import com.example.labjee.helpers.strategy.MovieTitleModifier;
import com.example.labjee.helpers.strategy.UppercaseMovieTitleStrategy;
import com.example.labjee.helpers.substitution.ForeignMovieString;
import com.example.labjee.helpers.substitution.MovieString;
import com.example.labjee.helpers.visitor.MovieServiceHolder;
import com.example.labjee.helpers.visitor.ServiceVisitor;
import com.example.labjee.helpers.visitor.Visitor;
import com.example.labjee.interfaces.ServiceRelationshipPair;
import com.example.labjee.models.Country;
import com.example.labjee.models.Genre;
import com.example.labjee.models.Movie;
import com.example.labjee.models.MovieActor;
import com.example.labjee.models.MovieCountry;
import com.example.labjee.models.MovieDirector;
import com.example.labjee.models.MovieGenre;
import com.example.labjee.models.MovieWriter;
import com.example.labjee.models.Person;
import com.example.labjee.models.User;
import com.example.labjee.services.CountryService;
import com.example.labjee.services.GenreService;
import com.example.labjee.services.MovieActorService;
import com.example.labjee.services.MovieCountryService;
import com.example.labjee.services.MovieDirectorService;
import com.example.labjee.services.MovieGenreService;
import com.example.labjee.services.MovieService;
import com.example.labjee.services.MovieWriterService;
import com.example.labjee.services.PersonService;
import com.example.labjee.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import static com.example.labjee.config.Constants.MAX_SIZE_LIMIT;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @Autowired
    private PersonService personService;

    @Autowired
    private GenreService genreService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private MovieDirectorService movieDirectorService;

    @Autowired
    private MovieWriterService movieWriterService;

    @Autowired
    private MovieActorService movieActorService;

    @Autowired
    private MovieGenreService movieGenreService;

    @Autowired
    private MovieCountryService movieCountryService;

    @GetMapping("/createMovie")
    public String createPage(Model m, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            m.addAttribute("movie", new Movie());

            setMovieAttributes(m);

            return "createMovie";
        }

        redirectAttributes.addFlashAttribute("failure", "Zaloguj się, aby wykonać tą operację.");

        return "redirect:/login";
    }
    //Tydzień 9 - 9.2
    @PostMapping("/createMovie")
    public String createPost(Model m, 
            @Valid Movie movie, 
            BindingResult binding, 
            @RequestParam(value = "genres", required = false) List<String> genres, 
            @RequestParam(value = "countries", required = false) List<String> countries, 
            @RequestParam(value = "directors", required = false) List<String> directors, 
            @RequestParam(value = "writers", required = false) List<String> writers, 
            @RequestParam(value = "actors", required = false) List<String> actors, 
            @RequestParam(value = "actors_roles", required = false) List<String> actors_roles,
            @RequestParam MultipartFile file, 
            RedirectAttributes redirectAttributes) 
            throws IOException {
        boolean validated = true;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());
        if (binding.hasErrors()) {
            validated = false;
        }
        if (validated && validateData(m, directors, writers, actors, actors_roles, file)) {
            Movie movieOut = generateMovie(movie, file, user);
            linkObjectsToMovie(genres, countries, directors, writers, actors, actors_roles, movieOut);
            Movie movieFinal = movieService.createOrUpdate(movieOut);
            user.addMovie(movieFinal);
            userService.createOrUpdate(user, false);
            redirectAttributes.addFlashAttribute("success", "Film utworzony pomyślnie.");
            return "redirect:/movie/" + movieFinal.getId();
        }
        setMovieAttributes(m);
        return "createMovie";
    }

    private Movie generateMovie(Movie movie, MultipartFile file, User user) throws IOException {
        RuntimeExpressionParser runtimeParser = new RuntimeExpressionParser();
        movie.setRuntime(runtimeParser.parse(movie.getRuntimeStr()));
        if (!file.isEmpty()) {
            movie.setPoster(file.getBytes());
        }
        movie.setCreationDate(new Date());
        movie.setUser(user);
        Movie movieOut = movieService.createOrUpdate(movie);
        return movieOut;
    }

    private void linkObjectsToMovie(List<String> genres, List<String> countries, List<String> directors, List<String> writers, List<String> actors, List<String> actors_roles, Movie movieOut) {
        linkDirectors(directors, movieOut);
        linkWriters(writers, movieOut);
        linkActors(actors, actors_roles, movieOut);
        linkGenres(genres, movieOut);
        linkCountries(countries, movieOut);
    }

    private void setMovieAttributes(Model m) {
        m.addAttribute("persons", personService.getAll());
        m.addAttribute("genres", genreService.getAll());
        m.addAttribute("countries", countryService.getAll());
    }

    private void linkCountries(List<String> countries, Movie movieOut) {
        if (countries != null) {
            for (String countryCode : countries) {
                Country country = countryService.getByCode(countryCode);

                if (country != null) {
                    MovieCountry movieCountry = movieCountryService.linkCountryToMovie(movieOut, country);
                    movieOut.addCountry(movieCountry);
                }
            }
        }
    }

    private void linkGenres(List<String> genres, Movie movieOut) {
        if (genres != null) {
            for (String genreName : genres) {
                Genre genre = genreService.getByName(genreName);

                if (genre != null) {
                    MovieGenre movieGenre = movieGenreService.linkGenreToMovie(movieOut, genre);
                    movieOut.addGenre(movieGenre);
                }
            }
        }
    }

    private void linkActors(List<String> actors, List<String> actors_roles, Movie movieOut) {
        if (actors != null && actors_roles != null) {
            if (actors.size() > 1 || (actors.size() == 1 && !actors.get(0).equals("-1"))) {
                int i = 0;

                for (String actorId : actors) {
                    Person actor = personService.getById(Integer.parseInt(actorId));

                    if (actor != null) {
                        MovieActor movieActor = movieActorService.linkActorToMovie(movieOut, actor, actors_roles.get(i));
                        movieOut.addActor(movieActor);
                    }

                    i++;
                }
            }
        }
    }

    private void linkWriters(List<String> writers, Movie movieOut) {
        if (writers != null) {
            if (writers.size() > 1 || (writers.size() == 1 && !writers.get(0).equals("-1"))) {
                for (String writerId : writers) {
                    Person writer = personService.getById(Integer.parseInt(writerId));

                    if (writer != null) {
                        MovieWriter movieWriter = movieWriterService.linkWriterToMovie(movieOut, writer);
                        movieOut.addWriter(movieWriter);
                    }
                }
            }
        }
    }

    private void linkDirectors(List<String> directors, Movie movieOut) {
        if (directors != null) {
            if (directors.size() > 1 || (directors.size() == 1 && !directors.get(0).equals("-1"))) {
                for (String directorId : directors) {
                    Person director = personService.getById(Integer.parseInt(directorId));

                    if (director != null) {
                        MovieDirector movieDirector = movieDirectorService.linkDirectorToMovie(movieOut, director);
                        movieOut.addDirector(movieDirector);
                    }
                }
            }
        }
    }

    // Tydzień 9 - 9.4, 9.3
    private static boolean validateData(Model m, List<String> directors, List<String> writers, List<String> actors, List<String> actors_roles, MultipartFile file) {
        return FileValidator.isFileValidated(m, file) && arePeopleValidated(m, directors, writers, actors, actors_roles);
    }

    private static boolean arePeopleValidated(Model m, List<String> directors, List<String> writers, List<String> actors, List<String> actors_roles) {
        boolean directorsValidated = areNonActorsValidated(directors, m, "directorsNull", "directorsDuplicates");

        boolean writersValidated = areNonActorsValidated(writers, m, "writersNull", "writersDuplicates");

        boolean actorsValidated = areActorsValidated(m, actors, actors_roles);

        return directorsValidated && writersValidated && actorsValidated;
    }

    private static boolean areActorsValidated(Model m, List<String> actors, List<String> actors_roles) {
        boolean actorsValidated = true;

        if (actors != null && actors_roles != null) {
            List<String> actorsNoDuplicates = new ArrayList<>(actors);

            if ((actors.size() == 1 && !actors.get(0).equals("-1") && actors_roles.isEmpty()) || (actors.size() > 1 && (actors.contains("-1") || actors_roles.contains(""))) || actorsNoDuplicates.size() < actors.size()) {
                if (actors.contains("-1")) {
                    m.addAttribute("actorsNull", "");
                }

                if (actors_roles.contains("") || actors_roles.isEmpty()) {
                    m.addAttribute("rolesNull", "");
                }

                if (actorsNoDuplicates.size() < actors.size()) {
                    m.addAttribute("actorsDuplicates", "");
                }

                actorsValidated = false;
            }
        }
        return actorsValidated;
    }

    private static boolean areNonActorsValidated(List<String> people, Model m, String nullAttribute, String duplicatesAttribute) {
        boolean validated = true;

        if (people != null) {
            List<String> peopleNoDuplicates = new ArrayList<>(people);

            if ((people.size() > 1 && people.contains("-1")) || people.isEmpty() || peopleNoDuplicates.size() < people.size()) {
                if (people.contains("-1")) {
                    m.addAttribute(nullAttribute, "");
                }

                if (peopleNoDuplicates.size() < people.size()) {
                    m.addAttribute(duplicatesAttribute, "");
                }

                validated = false;
            }
        }
        return validated;
    }

    @GetMapping(
            value = "/saveMovieArticle/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody byte[] downloadMovieArticle(Model m, @PathVariable int id) {
        Movie movie = movieService.getById(id);

        if (movie != null) {
            // Tydzień 3 - wzorzec Bridge - zastosowanie 1
            MovieArticle movieArticle = new MovieArticle(new MovieArticleSaver(), movie);
            // Tydzień 3 - wzorzec Bridge - koniec

            return movieArticle.save();
        }

        return new byte[0];
    }

    @GetMapping("/movie/{id}")
    public String viewMoviePage(Model m, @PathVariable int id) {
        Movie movie = movieService.getById(id);

        // Tydzień 6 - wzorzec Strategy - zastosowanie 1
        if (movie != null) {
            // Tydzień 8 - podstawienie Liskov - przykład 1 - zastosowanie
            MovieString movieString = new MovieString(movie);
            System.out.println(movieString.makeString());
            movieString = new ForeignMovieString(movie, "Poland");
            System.out.println(movieString.makeString());
            // Tydzień 8 - podstawienie Liskov - przykład 1 - zastosowanie - koniec

            MovieTitleModifier movieTitleModifier = new MovieTitleModifier(new UppercaseMovieTitleStrategy());
            movieTitleModifier.setMovie(movie);

            m.addAttribute("movie", movieTitleModifier.modifyAndGet(movie));

            return "movie";
        }
        // Tydzień 6 - wzorzec Strategy - zastosowanie 1 - koniec

        return "notFound";
    }

    @GetMapping("/moviePoster/{id}")
    public void posterPage(@PathVariable int id, HttpServletResponse response) throws IOException {
        Movie movie = movieService.getById(id);
        InputStream inputStream;

        // Tydzień 2 - wzorzec Factory - zastosowanie 3
        if (movie != null) {
            if (movie.getPoster() != null) {
                inputStream = new ByteArrayInputStream(movie.getPoster());
            } else {
                inputStream = new ByteArrayInputStream(BlankPictureFactory.getBlankPicture("movie"));
            }
        } else {
            inputStream = new ByteArrayInputStream(BlankPictureFactory.getBlankPicture("movie"));
        }
        // Tydzień 2 - wzorzec Factory - zastosowanie 3 - koniec

        response.setContentType(URLConnection.guessContentTypeFromStream(inputStream));
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping("/editMovie/{id}")
    public String editPage(Model m, @PathVariable int id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            Movie movie = movieService.getById(id);

            if (movie != null) {
                m.addAttribute("movie", movie);

                setMovieAttributes(m);

                m.addAttribute("directors", movie.getDirectors());
                m.addAttribute("writers", movie.getWriters());
                m.addAttribute("actors", movie.getActors());
                
                m.addAttribute("rawGenres", movie.getRawGenres());
                m.addAttribute("rawCountries", movie.getRawCountries());

                return "editMovie";
            }

            return "notFound";
        }

        redirectAttributes.addFlashAttribute("failure", "Zaloguj się, aby wykonać tą operację.");

        return "redirect:/login";
    }

    @PostMapping("/editMovie/{id}")
    public String editPost(Model m,
            @PathVariable int id,
            @Valid Movie newMovieData,
            BindingResult binding,
            @RequestParam(value = "genres", required = false) List<String> genres, 
            @RequestParam(value = "countries", required = false) List<String> countries, 
            @RequestParam(value = "directors", required = false) List<String> directors, 
            @RequestParam(value = "writers", required = false) List<String> writers, 
            @RequestParam(value = "actors", required = false) List<String> actors, 
            @RequestParam(value = "actors_roles", required = false) List<String> actors_roles,
            @RequestParam MultipartFile file,
            @RequestParam(defaultValue = "false") boolean fileDelete)
            throws IOException {
        boolean validated = true;
        
        Movie currentMovieData = movieService.getById(id);

        if (binding.hasErrors()) {
            validated = false;
        }

        RuntimeExpressionParser runtimeParser = new RuntimeExpressionParser();
        newMovieData.setRuntime(runtimeParser.parse(newMovieData.getRuntimeStr()));
        
        if (!fileDelete) {
            if (!file.isEmpty()) {
                if (file.getSize() > MAX_SIZE_LIMIT) {
                    m.addAttribute("imageSize", "");

                    if (validated) {
                        validated = false;
                    }
                }

                if (!(file.getContentType().equals("image/png") || file.getContentType().equals("image/jpeg"))) {
                    m.addAttribute("imageExtension", "");

                    if (validated) {
                        validated = false;
                    }
                }

                currentMovieData.setPoster(file.getBytes());
            }
        } else {
            currentMovieData.setPoster(null);
        }

        validated = arePeopleValidated(m, directors, writers, actors, actors_roles);

        if (validated) {
            currentMovieData.setTitle(newMovieData.getTitle());
            currentMovieData.setOriginalTitle(newMovieData.getOriginalTitle());
            currentMovieData.setReleaseDate(newMovieData.getReleaseDate());
            currentMovieData.setRuntime(newMovieData.getRuntime());
            currentMovieData.setDescription(newMovieData.getDescription());
            
            if (fileDelete) {
                currentMovieData.setPoster(null);
            } else {
                if (!file.isEmpty()) {
                    currentMovieData.setPoster(file.getBytes());
                }
            }
            
            if (directors != null) {
                for (ListIterator<MovieDirector> it = currentMovieData.getDirectors().listIterator(); it.hasNext();){
                    MovieDirector movieDirector = it.next();

                    if (!directors.contains(String.valueOf(movieDirector.getDirector().getId()))) {
                        movieDirectorService.deleteLink(movieDirector);
                        it.remove();
                        currentMovieData.deleteDirector(movieDirector);
                    }
                }
                
                if (directors.size() > 1 || (directors.size() == 1 && !directors.get(0).equals("-1"))) {
                    for (String directorId : directors) {
                        Person director = personService.getById(Integer.parseInt(directorId));

                        if (director != null) {
                            MovieDirector movieDirector = movieDirectorService.getLink(currentMovieData.getId(), director.getId());
                            
                            if (movieDirector == null) {
                                MovieDirector newMovieDirector = movieDirectorService.linkDirectorToMovie(currentMovieData, director);
                                currentMovieData.addDirector(newMovieDirector);
                            }
                        }
                    }
                }
            }
            
            if (writers != null) {
                for (ListIterator<MovieWriter> it = currentMovieData.getWriters().listIterator(); it.hasNext();){
                    MovieWriter movieWriter = it.next();

                    if (!writers.contains(String.valueOf(movieWriter.getWriter().getId()))) {
                        movieWriterService.deleteLink(movieWriter);
                        it.remove();
                        currentMovieData.deleteWriter(movieWriter);
                    }
                }

                if (writers.size() > 1 || (writers.size() == 1 && !writers.get(0).equals("-1"))) {
                    for (String writerId : writers) {
                        Person writer = personService.getById(Integer.parseInt(writerId));

                        if (writer != null) {
                            MovieWriter movieWriter = movieWriterService.getLink(currentMovieData.getId(), writer.getId());
                            
                            if (movieWriter == null) {
                                MovieWriter newMovieWriter = movieWriterService.linkWriterToMovie(currentMovieData, writer);
                                currentMovieData.addWriter(newMovieWriter);
                            }
                        }
                    }
                }
            }
            
            if (actors != null && actors_roles != null) {
                for (ListIterator<MovieActor> it = currentMovieData.getActors().listIterator(); it.hasNext();){
                    MovieActor movieActor = it.next();

                    if (!actors.contains(String.valueOf(movieActor.getActor().getId()))) {
                        movieActorService.deleteLink(movieActor);
                        it.remove();
                        currentMovieData.deleteActor(movieActor);
                    }
                }
                
                if (actors.size() > 1 || (actors.size() == 1 && !actors.get(0).equals("-1"))) {
                    int i = 0;

                    for (String actorId : actors) {
                        Person actor = personService.getById(Integer.parseInt(actorId));

                        if (actor != null) {
                            MovieActor movieActor = movieActorService.getLink(currentMovieData.getId(), actor.getId());
                            
                            if (movieActor == null) {
                                MovieActor newMovieActor = movieActorService.linkActorToMovie(currentMovieData, actor, actors_roles.get(i));
                                currentMovieData.addActor(newMovieActor);
                            } else if (!movieActor.getRole().equals(actors_roles.get(i))) {
                                currentMovieData.deleteActor(movieActor);
                                
                                movieActor.setRole(actors_roles.get(i));
                                
                                MovieActor newMovieActor = movieActorService.updateLink(movieActor);
                                currentMovieData.addActor(newMovieActor);
                            }
                        }

                        i++;
                    }
                }
            }

            if (genres != null) {
                for (ListIterator<MovieGenre> it = currentMovieData.getGenres().listIterator(); it.hasNext();){
                    MovieGenre movieGenre = it.next();

                    if (!genres.contains(movieGenre.getGenre().getName())) {
                        movieGenreService.deleteLink(movieGenre);
                        it.remove();
                        currentMovieData.deleteGenre(movieGenre);
                    }
                }

                for (String genreName : genres) {
                    Genre genre = genreService.getByName(genreName);

                    if (genre != null) {
                        MovieGenre movieGenre = movieGenreService.getLink(currentMovieData.getId(), genre.getName());

                        if (movieGenre == null) {
                            MovieGenre newMovieGenre = movieGenreService.linkGenreToMovie(currentMovieData, genre);
                            currentMovieData.addGenre(newMovieGenre);
                        }
                    }
                }
            }

            if (countries != null) {
                for (ListIterator<MovieCountry> it = currentMovieData.getCountries().listIterator(); it.hasNext();){
                    MovieCountry movieCountry = it.next();

                    if (!countries.contains(movieCountry.getCountry().getName())) {
                        movieCountryService.deleteLink(movieCountry);
                        it.remove();
                        currentMovieData.deleteCountry(movieCountry);
                    }
                }

                for (String countryCode : countries) {
                    Country country = countryService.getByCode(countryCode);

                    if (country != null) {
                        MovieCountry movieCountry = movieCountryService.getLink(currentMovieData.getId(), country.getCode());

                        if (movieCountry == null) {
                            MovieCountry newMovieCountry = movieCountryService.linkCountryToMovie(currentMovieData, country);
                            currentMovieData.addCountry(newMovieCountry);
                        }
                    }
                }
            }

            movieService.createOrUpdate(currentMovieData);

            return "redirect:/movie/" + currentMovieData.getId();
        }
        
        m.addAttribute("movie", currentMovieData);

        setMovieAttributes(m);

        m.addAttribute("directors", currentMovieData.getDirectors());
        m.addAttribute("writers", currentMovieData.getWriters());
        m.addAttribute("actors", currentMovieData.getActors());
                
        m.addAttribute("rawGenres", currentMovieData.getRawGenres());
        m.addAttribute("rawCountries", currentMovieData.getRawCountries());
        
        return "editMovie";
    }

    @PostMapping("/deleteMovie/{id}")
    public String deletePage(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            Movie movie = movieService.getById(id);
            // Tydzień 6 - Visitor - użycie
            Visitor visitor = new ServiceVisitor();
            MovieServiceHolder serviceHolder = new MovieServiceHolder();

            if (movie != null) {
                List<MovieDirector> directors = movie.getDirectors();
                for (MovieDirector movieDirector : directors) {
                    serviceHolder.serviceRelationshipPairs.add(new ServiceRelationshipPair(movieDirectorService, movieDirector));
                }

                List<MovieWriter> writers = movie.getWriters();
                for (MovieWriter movieWriter : writers) {
                    serviceHolder.serviceRelationshipPairs.add(new ServiceRelationshipPair(movieWriterService, movieWriter));
                }

                List<MovieActor> actors = movie.getActors();
                for (MovieActor movieActor : actors) {
                    serviceHolder.serviceRelationshipPairs.add(new ServiceRelationshipPair(movieActorService, movieActor));
                }

                List<MovieGenre> genres = movie.getGenres();
                for (MovieGenre movieGenre : genres) {
                    serviceHolder.serviceRelationshipPairs.add(new ServiceRelationshipPair(movieGenreService, movieGenre));
                }

                List<MovieCountry> countries = movie.getCountries();
                for (MovieCountry movieCountry : countries) {
                    serviceHolder.serviceRelationshipPairs.add(new ServiceRelationshipPair(movieCountryService, movieCountry));
                }

                serviceHolder.accept(visitor);

                // Tydzień 6 - Visitor - użycie - koniec

                User userCreator = userService.getByUsername(movie.getUser().getUsername());

                if (userCreator != null) {
                    user.deleteMovie(movie);
                    userService.createOrUpdate(user, false);
                }

                movieService.delete(movie.getId());

                redirectAttributes.addFlashAttribute("success", "Film usunięty pomyślnie.");

                return "redirect:/addedMovies";
            }

            redirectAttributes.addFlashAttribute("failure", "Film o podanym identyfikatorze nie istnieje.");

            return "redirect:/addedMovies";
        }

        redirectAttributes.addFlashAttribute("failure", "Zaloguj się, aby wykonać tą operację.");

        return "redirect:/login";
    }
}
