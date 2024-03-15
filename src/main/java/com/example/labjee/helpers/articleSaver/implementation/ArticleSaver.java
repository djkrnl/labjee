package com.example.labjee.helpers.articleSaver.implementation;

import com.example.labjee.helpers.articleSaver.SavableArticleData;
// Tydzień 3 - wzorzec Bridge - implementacja

public interface ArticleSaver {
    public byte[] saveArticle(SavableArticleData data) throws Exception;
}
