package com.example.labjee.helpers;

import lombok.Getter;
import lombok.Setter;

//Tydzień 2 - wzorzec Builder - klasa do budowania ścieżki do pliku
public class ImagePathBuilder {

    private String path = "";

    @Getter
    @Setter
    private String fileName = "";

    @Getter
    @Setter
    private String extension = "";

    public ImagePathBuilder() {
        path = "src/main/resources/images";
    }

    public ImagePathBuilder(String customPath) {
        path = customPath;
    }

    public String build() {
        if (fileName.isEmpty()) {
            fileName = "fallbackImage";
        }

        if (path.isEmpty()) {
            path = "png";
        }

        fileName = fileName.trim();
        path = path.trim();

        return path + "/" + fileName + "." + extension;
    }
}
//Tydzień 2 - wzorzec Builder - koniec

