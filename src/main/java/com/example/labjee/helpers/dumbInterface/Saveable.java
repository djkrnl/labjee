package com.example.labjee.helpers.dumbInterface;

import java.nio.file.Path;

public interface Saveable {
    void saveToFile(Path path, String text);

    boolean generateFile(Path path);
}
