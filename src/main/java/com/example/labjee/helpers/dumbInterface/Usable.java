package com.example.labjee.helpers.dumbInterface;

public interface Usable {
    boolean isOn = false;

    boolean shouldBeUsed = true;

    boolean checkPermition();

    void turnOn();

    void fixItem();

    void breakItem();
}
