package com.example.labjee.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

// Tydzień 2 - wzorzec Factory (Simple Factory) - klasa fabryki zwracająca tablicę bajtów zawierającą domyślne zdjęcie (wyświetlane w przypadku, gdy dany użytkownik/film/osoba nie ma swojego zdjęcia w bazie) w zależności od podanego jako argument typu zdjęcia
public class BlankPictureFactory {
    public static byte[] getBlankPicture(String type) throws IOException {
        File file;

        switch(type) {
            case "user":
                file = new File("src/main/resources/images/blankUserPicture.png");
                return Files.readAllBytes(file.toPath());
            case "person":
                file = new File("src/main/resources/images/blankPersonPicture.png");
                return Files.readAllBytes(file.toPath());
            case "movie":
                file = new File("src/main/resources/images/blankMoviePoster.png");
                return Files.readAllBytes(file.toPath());
            default:
                return new byte[0];
        }
    }
}
// Tydzień 2 - wzorzec Factory (Simple Factory) - koniec
