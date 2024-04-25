package com.example.labjee.helpers.dumbInterface;

import jakarta.annotation.Nullable;

import java.io.File;

public interface Uplodable {

    File musicFile = null;

    boolean uploadToSpotify(String composer, String title, @Nullable String album);

    boolean uploadToYoutube(String composer, String title);

    boolean uploadToAppleMusic(String composer, String title, @Nullable String album);
}
