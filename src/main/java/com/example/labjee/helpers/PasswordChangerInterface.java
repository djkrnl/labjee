package com.example.labjee.helpers;

// Tydzień 4 - wzorzec Proxy - interfejs klasy odpowiedzialnej za zmianę hasła użytkownika
public interface PasswordChangerInterface {
    boolean checkPassword(String username, String password);
    boolean changePassword(String username, String newPassword);
}
// Tydzień 4 - wzorzec Proxy - koniec