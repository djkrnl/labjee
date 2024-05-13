package com.example.labjee.helpers.articleSaver.implementation;

import com.example.labjee.helpers.articleSaver.SaveableArticleData;
// Tydzień 3 - wzorzec Bridge - implementacja
// Tydzień 7 - zasada otwarte-zamknięte
public interface ArticleSaver {
    public byte[] saveArticle(SaveableArticleData data) throws Exception;
}
