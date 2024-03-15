package com.example.labjee.helpers;

import lombok.Getter;

// Tydzień 2 - wzorzec Singleton - klasa obiektu Singleton trzymająca dane o ilości obecnie zalogowanych użytkowników
public class UsersLoggedInSingleton {

    @Getter
    private int count = 0;

    private static UsersLoggedInSingleton instance;

    private UsersLoggedInSingleton() {}

    public static UsersLoggedInSingleton getInstance() {
        if (instance == null) {
            instance = new UsersLoggedInSingleton();
        }
        return instance;
    }

    public void userLoggedIn() {
        count++;
    }

    public void userLoggedOut() {
        if (count > 0) {
            count--;
        }
    }
}
