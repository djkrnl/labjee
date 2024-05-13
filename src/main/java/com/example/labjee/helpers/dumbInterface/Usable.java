package com.example.labjee.helpers.dumbInterface;

public interface Usable {
    boolean isOn = false;

    boolean shouldBeUsed = true;

    boolean checkPermission();

    void turnOn();

    void fixItem();

    void breakItem();
}
