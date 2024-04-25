package com.example.labjee.helpers.dumbInterface;

import jakarta.annotation.Nullable;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;

public interface Musicable {

    String voice = "miku";

    ArrayList<Byte> melody = new ArrayList<>();

    File musicFile = null;

    void sing();

    void changeVoice(String voice);

    void compose();

    ArrayList<Byte> playMusic();

    boolean generateFile(Path path);

    boolean uploadToSpotify(String composer, String title, @Nullable String album);

    boolean uploadToYoutube(String composer, String title);

    boolean uploadToAppleMusic(String composer, String title, @Nullable String album);
}
