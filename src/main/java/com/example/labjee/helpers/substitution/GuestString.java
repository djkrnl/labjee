package com.example.labjee.helpers.substitution;

import com.example.labjee.models.User;

// Tydzień 8 - podstawienie Liskov - przykład 3 - klasa pochodna
public class GuestString extends UserString {
    int activeForHours;

    public GuestString(User User, int activeForHours) {
        super(User);
        this.activeForHours = activeForHours;
    }
}
