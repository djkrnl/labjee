package com.example.labjee.helpers.substitution;

import com.example.labjee.models.User;

// Tydzień 8 - podstawienie Liskov - przykład 3 - klasa bazowa
public class UserString {
    User User;

    public String makeString() {
        return User.getUsername() + " is an user";
    }


    public UserString(User User) {
        this.User = User;
    }
}
