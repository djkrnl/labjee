package com.example.labjee.helpers;

import com.example.labjee.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;

// Tydzień 4 - wzorzec Proxy - implementacja klasy zmieniającej hasła z uwzględnieniem prawidłowości haseł na podstawie przekazanego hasła z formularza i metod podstawowej implementacji
public class PasswordChangerProxy implements PasswordChangerInterface {
    private PasswordChanger passwordChanger;

    private String providedPassword;

    public PasswordChangerProxy(UserService userService, PasswordEncoder passwordEncoder, String providedPassword) {
        passwordChanger = new PasswordChanger(userService, passwordEncoder);

        this.providedPassword = providedPassword;
    }

    @Override
    public boolean checkPassword(String username, String password) {
        return passwordChanger.checkPassword(username, password);
    }

    @Override
    public boolean changePassword(String username, String newPassword) {
        if (passwordChanger.checkPassword(username, providedPassword)) {
            return passwordChanger.changePassword(username, newPassword);
        } else return false;
    }
}
// Tydzień 4 - wzorzec Proxy - koniec
