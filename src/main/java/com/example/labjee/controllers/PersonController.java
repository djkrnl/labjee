package com.example.labjee.controllers;

import com.example.labjee.helpers.ArticleSaver.abstraction.MovieArticle;
import com.example.labjee.helpers.ArticleSaver.abstraction.PersonArticle;
import com.example.labjee.helpers.ArticleSaver.implementation.MovieArticleSaver;
import com.example.labjee.helpers.ArticleSaver.implementation.PersonArticleSaver;
import com.example.labjee.helpers.BlankPictureFactory;
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

        if (!file.isEmpty()) {
            if (file.getSize() > 1048576) {
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
        }
        
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
            PersonArticle personArticle = new PersonArticle(new PersonArticleSaver(), person);
            // Tydzień 3 - wzorzec Bridge - koniec

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
            if (!file.isEmpty()) {
                if (file.getSize() > 1048576) {
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
            }
        }
        
        if (validated) {
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

            personService.createOrUpdate(currentPersonData);

            return "redirect:/person/" + currentPersonData.getId();
        }
        
        m.addAttribute("person", currentPersonData);
        
        m.addAttribute("countries", countryService.getAll());
        
        return "editPerson";
    }

    @PostMapping("/deletePerson/{id}")
    public String deletePost(@PathVariable int id, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            Person person = personService.getById(id);

            if (person != null) {
                List<MovieDirector> moviesDirector = person.getMoviesAsDirector();
                for (MovieDirector movieDirector : moviesDirector) {
                    Movie movie = movieService.getById(movieDirector.getMovie().getId());
                    movie.deleteDirector(movieDirector);
                    movieService.createOrUpdate(movie);

                    movieDirectorService.deleteLink(movie.getId(), person.getId());
                }

                List<MovieWriter> moviesWriter = person.getMoviesAsWriter();
                for (MovieWriter movieWriter : moviesWriter) {
                    Movie movie = movieService.getById(movieWriter.getMovie().getId());
                    movie.deleteWriter(movieWriter);
                    movieService.createOrUpdate(movie);

                    movieWriterService.deleteLink(movie.getId(), person.getId());
                }

                List<MovieActor> moviesActor = person.getMoviesAsActor();
                for (MovieActor movieActor : moviesActor) {
                    Movie movie = movieService.getById(movieActor.getMovie().getId());
                    movie.deleteActor(movieActor);
                    movieService.createOrUpdate(movie);

                    movieActorService.deleteLink(movie.getId(), person.getId());
                }
                
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
}
