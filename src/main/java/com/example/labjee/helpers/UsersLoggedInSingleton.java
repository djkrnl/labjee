package com.example.labjee.helpers;

import com.example.labjee.helpers.observer.CustomObserver;
import lombok.Getter;

import java.util.ArrayList;

// Tydzień 2 - wzorzec Singleton - klasa obiektu Singleton trzymająca dane o ilości obecnie zalogowanych użytkowników
public class UsersLoggedInSingleton {

    @Getter
    private int count = 0;
    //Tydzień 8 - odwrócenie zależności - wykorzystanie interfejsu
    private static ArrayList<CustomObserver> observers;

    private static UsersLoggedInSingleton instance;

    private UsersLoggedInSingleton() {}

    public static UsersLoggedInSingleton getInstance() {
        if (instance == null) {
            instance = new UsersLoggedInSingleton();
            observers = new ArrayList<>();
        }
        return instance;
    }

    public void userLoggedIn() {
        count++;
        notifyObservers();
    }

    public void userLoggedOut() {
        if (count > 0) {
            count--;
            notifyObservers();
        }
    }
    // Tydzień 6 - Observer - metody tematu
    public void registerObserver(CustomObserver customObserver) {
        observers.add(customObserver);
    }

    public void removeObserver(CustomObserver customObserver) {
        observers.remove(customObserver);
    }

    private void notifyObservers() {
        for (CustomObserver observer : observers) {
            observer.update(count);
        }
    }
    // Tydzień 6 - Observer - metody tematu - koniec
}
