package com.example.labjee.controllers;

import com.example.labjee.helpers.BlankPictureFactory;
import com.example.labjee.helpers.PasswordChangerProxy;
import com.example.labjee.models.Movie;
import com.example.labjee.models.Person;
import com.example.labjee.models.User;
import com.example.labjee.services.MovieService;
import com.example.labjee.services.PersonService;
import com.example.labjee.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.Date;
import java.util.List;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private PersonService personService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String loginPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user == null) {
            return "login";
        }

        return "redirect:/";
    }

    @GetMapping("/register")
    public String registerPage(Model m) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user == null) {
            m.addAttribute("user", new User());
            return "register";
        }

        return "redirect:/";
    }

    @PostMapping("/register")
    public String registerPost(Model m, @Valid User user, BindingResult binding, RedirectAttributes redirectAttributes) {
        User userFromDatabase = userService.getByUsername(user.getUsername());
        User userFromDatabaseWithEmail = userService.getByEmail(user.getEmail());

        if (binding.hasErrors() || userFromDatabase != null || userFromDatabaseWithEmail != null) {
            if (userFromDatabase != null) {
                m.addAttribute("usernameTaken", "");
            }

            if (userFromDatabaseWithEmail != null) {
                m.addAttribute("emailTaken", "");
            }

            return "register";
        }

        user.setCreationDate(new Date());
        
        userService.createOrUpdate(user, true);

        redirectAttributes.addFlashAttribute("success", "Rejestracja zakończona powodzeniem.");

        return "redirect:/login";
    }

    @GetMapping("/user/{username}")
    public String userPage(Model m, @PathVariable String username) {
        User user = userService.getByUsername(username);

        if (user != null) {
            m.addAttribute("user", user);

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (username.equals(auth.getName())) {
                m.addAttribute("authenticated", "");

                m.addAttribute("addedMovies", movieService.getNewestByUser(username));
                m.addAttribute("addedPersons", personService.getNewestByUser(username));
            }

            return "profile";
        }

        return "notFound";
    }

    @GetMapping("/userPicture/{username}")
    public void picturePage(@PathVariable String username, HttpServletResponse response) throws IOException {
        User user = userService.getByUsername(username);
        InputStream inputStream;

        // Tydzień 2 - wzorzec Factory - zastosowanie 2
        if (user != null) {
            if (user.getProfilePicture() != null) {
                inputStream = new ByteArrayInputStream(user.getProfilePicture());
            } else {
                inputStream = new ByteArrayInputStream(BlankPictureFactory.getBlankPicture("user"));
            } 
        } else {
            inputStream = new ByteArrayInputStream(BlankPictureFactory.getBlankPicture("user"));
        }
        // Tydzień 2 - wzorzec Factory - zastosowanie 2 - koniec
        
        response.setContentType(URLConnection.guessContentTypeFromStream(inputStream));
        IOUtils.copy(inputStream, response.getOutputStream());
    }
    
    @GetMapping("/addedPersons")
    public String addedPersonsPage(Model m, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            m.addAttribute("persons", user.getAddedPersons());

            return "addedPersons";
        }

        redirectAttributes.addFlashAttribute("failure", "Zaloguj się, aby wykonać tą operację.");

        return "redirect:/login";
    }
    
    @GetMapping("/addedMovies")
    public String addedMoviesPage(Model m, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            m.addAttribute("movies", user.getAddedMovies());

            return "addedMovies";
        }

        redirectAttributes.addFlashAttribute("failure", "Zaloguj się, aby wykonać tą operację.");

        return "redirect:/login";
    }

    @GetMapping("/settings")
    public String settingsPage(Model m, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            m.addAttribute("user", user);

            return "settings";
        }

        redirectAttributes.addFlashAttribute("failure", "Zaloguj się, aby wykonać tą operację.");

        return "redirect:/login";
    }

    @PostMapping("/settings")
    public String settingsPost(Model m, @Valid User newUserData, BindingResult binding, @RequestParam MultipartFile file, @RequestParam(defaultValue = "false") boolean fileDelete) throws IOException {
        boolean validated = true;
        
        User userFromDatabaseWithEmail = userService.getByEmail(newUserData.getEmail());
        User currentUserData = userService.getByUsername(newUserData.getUsername());

        if (binding.hasErrors()) {
            validated = false;
        }

        if (userFromDatabaseWithEmail != null) {
            if (!newUserData.getUsername().equals(userFromDatabaseWithEmail.getUsername())) {
                m.addAttribute("emailTaken", "");

                if (validated) {
                    validated = false;
                }
            }
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

                if (validated) {
                    currentUserData.setProfilePicture(file.getBytes());
                }
            }
        } else if (validated) {
            currentUserData.setProfilePicture(null);
        }  
        
        if (validated) {
            currentUserData.setName(newUserData.getName());
            currentUserData.setSurname(newUserData.getSurname());
            currentUserData.setEmail(newUserData.getEmail());

            userService.createOrUpdate(currentUserData, false);

            return "redirect:/logout";
        }
        
        m.addAttribute("user", currentUserData);
        
        return "settings";
    }

    @GetMapping("/changePassword")
    public String changePasswordPage(Model m, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            return "changePassword";
        }

        redirectAttributes.addFlashAttribute("failure", "Zaloguj się, aby wykonać tą operację.");

        return "redirect:/login";
    }

    @PostMapping("/changePassword")
    public String changePasswordPost(Model m, @RequestParam(value = "oldPassword") String oldPassword, @RequestParam(value = "newPassword") String newPassword) {
        boolean validated = true;

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!newPassword.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$")) {
            m.addAttribute("failure", "Hasło musi zawierać co najmniej 1 małą literę, 1 dużą literę i 1 cyfrę i nie może zawierać spacji");

            validated = false;
        }

        // Tydzień 4 - wzorzec Proxy - zastosowanie 1
        if (validated) {
            PasswordChangerProxy passwordChangerProxy = new PasswordChangerProxy(userService, passwordEncoder, oldPassword);

            if (passwordChangerProxy.changePassword(auth.getName(), newPassword)) {
                return "redirect:/logout";
            } else {
                m.addAttribute("failure", "Podane aktualne hasło jest nieprawidłowe");
            }
        }
        // Tydzień 4 - wzorzec Proxy - zastosowanie 1 - koniec

        return "changePassword";
    }

    @PostMapping("/delete")
    public String deletePost(RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByUsername(auth.getName());

        if (user != null) {
            List<Movie> addedMovies = user.getAddedMovies();
            for (Movie movie : addedMovies) {
                movie.setUser(null);
                movieService.createOrUpdate(movie);
            }
            
            List<Person> addedPersons = user.getAddedPersons();
            for (Person person : addedPersons) {
                person.setUser(null);
                personService.createOrUpdate(person);
            }
            
            userService.delete(user.getUsername());
            
            return "redirect:/logout";
        }

        redirectAttributes.addFlashAttribute("failure", "Zaloguj się, aby wykonać tą operację.");

        return "redirect:/login";
    }
}
