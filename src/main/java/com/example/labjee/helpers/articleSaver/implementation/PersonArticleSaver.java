package com.example.labjee.helpers.articleSaver.implementation;

import com.example.labjee.helpers.articleSaver.SavableArticleData;
import com.example.labjee.helpers.template.ArticleSaverTemplate;

import java.io.*;
// Tydzień 3 - wzorzec Bridge - implementacja
// Tydzień 6 - Template - implementacja
// Tydzień 7 - zasada otwarte-zamknięte
public class PersonArticleSaver extends ArticleSaverTemplate {

    @Override
    protected File prepareFile() {
        return new File("src\\main\\resources\\files\\persons/" + this.data.getFileName());
    }

    @Override
    protected InputStream getStreamFromFile() throws FileNotFoundException {
        return new FileInputStream("src\\main\\resources\\files\\persons/" + this.data.getFileName());
    }
}
