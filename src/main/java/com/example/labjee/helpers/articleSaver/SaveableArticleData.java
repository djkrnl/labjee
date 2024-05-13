package com.example.labjee.helpers.articleSaver;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SaveableArticleData {
    @Setter
    private String fileName;

    @Setter
    private String data;
}
