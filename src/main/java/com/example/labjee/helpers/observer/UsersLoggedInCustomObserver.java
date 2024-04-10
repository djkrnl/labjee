package com.example.labjee.helpers.observer;

import com.example.labjee.helpers.UsersLoggedInSingleton;
// Tydzień 6 - Observer - metody obserwatora

public class UsersLoggedInCustomObserver implements CustomObserver {

    public int value;

    public UsersLoggedInCustomObserver() {
        UsersLoggedInSingleton.getInstance().registerObserver(this);
    }

    @Override
    public void update(int count) {
        value = count;
    }
}
