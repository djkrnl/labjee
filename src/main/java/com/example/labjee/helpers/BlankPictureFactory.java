package com.example.labjee.helpers;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

// Tydzień 2 - wzorzec Factory (Simple Factory) - klasa fabryki zwracająca tablicę bajtów zawierającą domyślne zdjęcie (wyświetlane w przypadku, gdy dany użytkownik/film/osoba nie ma swojego zdjęcia w bazie) w zależności od podanego jako argument typu zdjęcia
public class BlankPictureFactory {
    public static byte[] getBlankPicture(String type) throws IOException {
        //Tydzień 2 - wzorzec Builder - budowanie ścieżki do pliku
        ImagePathBuilder pathBuilder = new ImagePathBuilder();

        switch(type) {
            case "user":
                pathBuilder.setFileName("blankUserPicture");
                pathBuilder.setExtension("png");
            case "person":
                pathBuilder.setFileName("blankPersonPicture");
                pathBuilder.setExtension("png");
            case "movie":
                pathBuilder.setFileName("blankMoviePoster");
                pathBuilder.setExtension("png");
        }
        //Tydzień 2 - wzorzec Builder - koniec

        File file = new File(pathBuilder.build());
        return Files.readAllBytes(file.toPath());
    }
}
// Tydzień 2 - wzorzec Factory (Simple Factory) - koniec
