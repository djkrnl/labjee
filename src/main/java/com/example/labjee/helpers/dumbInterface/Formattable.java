package com.example.labjee.helpers.dumbInterface;

import java.nio.file.Path;

public interface Formattable {
    String stripTags(Path path);

    String stripTags(String string);
}
