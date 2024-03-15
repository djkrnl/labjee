package com.example.labjee.helpers.articleSaver;

import lombok.Getter;
import lombok.Setter;

@Getter
public class SavableArticleData {
    @Setter
    private String fileName;

    @Setter
    private String data;
}
