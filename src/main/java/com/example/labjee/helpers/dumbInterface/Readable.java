package com.example.labjee.helpers.dumbInterface;

import java.nio.file.Path;

public interface Readable {
    String readFromFile(Path path);

    FileType getFileType(Path path);

    boolean checkIfFileIsImage(Path path);
}
