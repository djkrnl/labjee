package com.example.labjee.helpers.dumbInterface;

import java.nio.file.Path;

public interface Fileable {

    void saveToFile(Path path, String text);

    String readFromFile(Path path);

    boolean generateFile(Path path);

    FileType getFileType(Path path);

    boolean checkIfFileReadable(Path path);

    String stripTags(Path path);

    String stripTags(String string);

    boolean checkIfFileIsImage(Path path);
}

