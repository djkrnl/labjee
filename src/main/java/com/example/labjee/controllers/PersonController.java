package com.example.labjee.controllers;

import com.example.labjee.helpers.FileValidator;
import com.example.labjee.helpers.articleSaver.abstraction.Article;
import com.example.labjee.helpers.articleSaver.abstraction.Introduction;
import com.example.labjee.helpers.articleSaver.abstraction.PersonArticle;
import com.example.labjee.helpers.articleSaver.implementation.ArticleSaver;
import com.example.labjee.helpers.articleSaver.implementation.PersonArticleSaver;
import com.example.labjee.helpers.BlankPictureFactory;
import com.example.labjee.helpers.functionalInterface.MovieDisplayer;
import com.example.labjee.helpers.functionalInterface.PersonDisplayer;
import com.example.labjee.helpers.substitution.ActorString;
import com.example.labjee.helpers.substitution.PersonString;
import com.example.labjee.models.Country;
import com.example.labjee.models.Movie;
import com.example.labjee.models.MovieActor;
import com.example.labjee.models.MovieDirector;
import com.example.labjee.models.MovieWriter;
import com.example.labjee.models.Person;
import com.example.labjee.models.User;
import com.example.labjee.services.CountryService;
import com.example.labjee.services.MovieActorService;
import com.example.labjee.services.MovieDirectorService;
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
import java.time.Period;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
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
public class PersonController {
    @Autowired
    private PersonService personService;

    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieDirectorService movieDirectorService;

    @Autowired
    private MovieWriterService movieWriterService;

    @Autowired
    private MovieActorService movieActorService;
    
    @Autowired
    private CountryService countryService;

    @GetMapping("/createPerson")
    public String createPage(Model m, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            m.addAttribute("person", new Person());
            
            m.addAttribute("countries", countryService.getAll());

            return "createPerson";
        }

        redirectAttributes.addFlashAttribute("failure", "Zaloguj się, aby wykonać tą operację.");

        return "redirect:/login";
    }

    @PostMapping("/createPerson")
    public String createPost(Model m, @Valid Person person, BindingResult binding, @RequestParam(value = "originCountry") String originCountry, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        boolean validated = !binding.hasErrors();

        if (person.getDeathDate() != null) {
            if (person.getDeathDate().before(person.getBirthDate())) {
                redirectAttributes.addFlashAttribute("minusAge", "");

                if (validated) {
                    validated = false;
                }
            }
        }

        validated = FileValidator.isFileValidated(m, file);

        if (validated) {
            Country country = countryService.getByCode(originCountry);
            
            if (country != null) {
                person.setOriginCountry(country);
            }
            
            if (!file.isEmpty()) {
                person.setPicture(file.getBytes());
            }
            
            person.setCreationDate(new Date());

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.getByUsername(auth.getName());

            person.setUser(user);

            Person personOut = personService.createOrUpdate(person);
            
            if (country != null) {
                country.addPerson(personOut);
                countryService.createOrUpdate(country);
            }

            user.addPerson(personOut);
            userService.createOrUpdate(user, false);
            
            redirectAttributes.addFlashAttribute("success", "Osoba utworzona pomyślnie.");

            return "redirect:/person/" + personOut.getId();
        }
        
        m.addAttribute("countries", countryService.getAll());
        
        return "createPerson";
    }

    @GetMapping("/person/{id}")
    public String viewPersonPage(Model m, @PathVariable int id) {
        Person person = personService.getById(id);

        if (person != null) {
            // Tydzień 8 - podstawienie Liskov - przykład 2 - zastosowanie
            PersonString personString = new PersonString(person);
            System.out.println(personString.makeString());
            personString = new ActorString(person, "Actor 1");
            System.out.println(personString.makeString());
            // Tydzień 8 - podstawienie Liskov - przykład 2 - zastosowanie - koniec

            // Tydzień 10 - 10.1 - zastosowanie interfejsu 2
            PersonDisplayer personNameDisplayer = (personObject) -> System.out.println(personObject.getName() + " " + personObject.getSurname());
            personNameDisplayer.display(person);
            // Tydzień 10 - 10.1 - zastosowanie interfejsu 2 - koniec

            m.addAttribute("person", person);
            
            if (person.getDeathDate() != null) {
                m.addAttribute("age", Period.between(person.getBirthDate().toInstant().atOffset(ZoneOffset.UTC).toLocalDate(), person.getDeathDate().toInstant().atOffset(ZoneOffset.UTC).toLocalDate()).getYears());
            } else {
                m.addAttribute("age", Period.between(person.getBirthDate().toInstant().atOffset(ZoneOffset.UTC).toLocalDate(), new Date().toInstant().atOffset(ZoneOffset.UTC).toLocalDate()).getYears());
            }

            return "person";
        }

        return "notFound";
    }

    @GetMapping(
            value = "/savePersonArticle/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
    )
    public @ResponseBody byte[] downloadPersonArticle(Model m, @PathVariable int id) {
        Person person = personService.getById(id);

        if (person != null) {
            // Tydzień 3 - wzorzec Bridge - zastosowanie 1
            ArticleSaver articleSaver = new PersonArticleSaver();
            Article personArticle = new PersonArticle(articleSaver, person);
            // Tydzień 3 - wzorzec Bridge - koniec
            // Tydzień 3 - wzorzec Decorator - zastosowanie
            personArticle = new Introduction(articleSaver, personArticle);
            // Tydzień 3 - wzorzec Decorator - koniec

            return personArticle.save();
        }

        return new byte[0];
    }
    
    @GetMapping("/personPicture/{id}")
    public void picturePage(@PathVariable int id, HttpServletResponse response) throws IOException {
        Person person = personService.getById(id);
        InputStream inputStream;

        // Tydzień 2 - wzorzec Factory - zastosowanie 1
        if (person != null) {
            if (person.getPicture() != null) {
                inputStream = new ByteArrayInputStream(person.getPicture());
            } else {
                inputStream = new ByteArrayInputStream(BlankPictureFactory.getBlankPicture("person"));
            } 
        } else {
            inputStream = new ByteArrayInputStream(BlankPictureFactory.getBlankPicture("person"));
        }
        // Tydzień 2 - wzorzec Factory - zastosowanie 1 - koniec
        
        response.setContentType(URLConnection.guessContentTypeFromStream(inputStream));
        IOUtils.copy(inputStream, response.getOutputStream());
    }

    @GetMapping("/editPerson/{id}")
    public String editPage(Model m, @PathVariable int id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            Person person = personService.getById(id);

            if (person != null) {
                m.addAttribute("person", person);
                
                m.addAttribute("countries", countryService.getAll());

                return "editPerson";
            }

            return "notFound";
        }

        redirectAttributes.addFlashAttribute("failure", "Zaloguj się, aby wykonać tą operację.");

        return "redirect:/login";
    }

    @PostMapping("/editPerson/{id}")
    public String editPost(Model m, @PathVariable int id, @Valid Person newPersonData, BindingResult binding, @RequestParam(value = "originCountry") String originCountry, @RequestParam MultipartFile file, @RequestParam(defaultValue = "false") boolean fileDelete) throws IOException {
        boolean validated = true;
        
        Person currentPersonData = personService.getById(id);

        if (binding.hasErrors()) {
            validated = false;
        }

        if (!fileDelete) {
            validated = FileValidator.isFileValidated(m, file);
        }
        
        if (validated) {
            setPersonNewData(newPersonData, originCountry, file, fileDelete, currentPersonData);

            personService.createOrUpdate(currentPersonData);

            return "redirect:/person/" + currentPersonData.getId();
        }
        
        m.addAttribute("person", currentPersonData);
        
        m.addAttribute("countries", countryService.getAll());
        
        return "editPerson";
    }

    private void setPersonNewData(Person newPersonData, String originCountry, MultipartFile file, boolean fileDelete, Person currentPersonData) throws IOException {
        Country country = countryService.getByCode(originCountry);

        if (country != currentPersonData.getOriginCountry()) {
            country.deletePerson(currentPersonData);
            countryService.createOrUpdate(country);
        }

        if (country != null) {
            currentPersonData.setOriginCountry(country);
        }

        if (fileDelete) {
            currentPersonData.setPicture(null);
        } else {
            if (!file.isEmpty()) {
                currentPersonData.setPicture(file.getBytes());
            }
        }

        currentPersonData.setName(newPersonData.getName());
        currentPersonData.setSurname(newPersonData.getSurname());
        currentPersonData.setBirthDate(newPersonData.getBirthDate());
        currentPersonData.setDeathDate(newPersonData.getDeathDate());
        currentPersonData.setBiography(newPersonData.getBiography());
    }

    @PostMapping("/deletePerson/{id}")
    public String deletePost(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            Person person = personService.getById(id);

            if (person != null) {
                setDirectors(person);
                setWriters(person);
                setActors(person);
                User userCreator = userService.getByUsername(person.getUser().getUsername());
                
                if (userCreator != null) {
                    user.deletePerson(person);
                    userService.createOrUpdate(user, false);
                }
                personService.delete(person.getId());
                redirectAttributes.addFlashAttribute("success", "Osoba usunięta pomyślnie.");
                return "redirect:/addedPersons";
            }
            redirectAttributes.addFlashAttribute("failure", "Osoba o podanym identyfikatorze nie istnieje.");
            return "redirect:/addedPersons";
        }
        redirectAttributes.addFlashAttribute("failure", "Zaloguj się, aby wykonać tą operację.");
        return "redirect:/login";
    }

    private void setActors(Person person) {
        List<MovieActor> moviesActor = person.getMoviesAsActor();
        for (MovieActor movieActor : moviesActor) {
            Movie movie = movieService.getById(movieActor.getMovie().getId());
            movie.deleteActor(movieActor);
            movieService.createOrUpdate(movie);

            movieActorService.deleteLink(movie.getId(), person.getId());
        }
    }

    private void setWriters(Person person) {
        List<MovieWriter> moviesWriter = person.getMoviesAsWriter();
        for (MovieWriter movieWriter : moviesWriter) {
            Movie movie = movieService.getById(movieWriter.getMovie().getId());
            movie.deleteWriter(movieWriter);
            movieService.createOrUpdate(movie);

            movieWriterService.deleteLink(movie.getId(), person.getId());
        }
    }

    private void setDirectors(Person person) {
        List<MovieDirector> moviesDirector = person.getMoviesAsDirector();
        for (MovieDirector movieDirector : moviesDirector) {
            Movie movie = movieService.getById(movieDirector.getMovie().getId());
            movie.deleteDirector(movieDirector);
            movieService.createOrUpdate(movie);

            movieDirectorService.deleteLink(movie.getId(), person.getId());
        }
    }
}
