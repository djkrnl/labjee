package com.example.labjee.helpers;

import com.example.labjee.models.User;
import com.example.labjee.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

// Tydzień 4 - wzorzec Proxy - implementacja klasy zmiany hasła użytkownika, wykorzystująca przekazane usługi modelu użytkownika i klasy szyfrującej hasła do sprawdzenia równości haseł i zmiany hasła użytkownika na nowe
public class PasswordChanger implements PasswordChangerInterface {
    private UserService userService;

    private PasswordEncoder passwordEncoder;

    public PasswordChanger(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkPassword(String username, String password) {
        User userFromDatabase = userService.getByUsername(username);

        if (userFromDatabase != null) {
            String oldPassword = userFromDatabase.getPassword();

            return passwordEncoder.matches(password, oldPassword);
        } else return false;
    }

    @Override
    public boolean changePassword(String username, String newPassword) {
        User userFromDatabase = userService.getByUsername(username);

        if (userFromDatabase != null) {
            userFromDatabase.setPassword(newPassword);

            userService.createOrUpdate(userFromDatabase, true);

            return true;
        } else return false;
    }
}
// Tydzień 4 - wzorzec Proxy - koniec