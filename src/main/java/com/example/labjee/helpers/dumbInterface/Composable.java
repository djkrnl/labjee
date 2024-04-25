package com.example.labjee.helpers.dumbInterface;

import java.util.ArrayList;

public interface Composable {
    String voice = "miku";

    ArrayList<Byte> melody = new ArrayList<>();

    void sing();

    void changeVoice(String voice);

    void compose();

    ArrayList<Byte> playMusic();
}
