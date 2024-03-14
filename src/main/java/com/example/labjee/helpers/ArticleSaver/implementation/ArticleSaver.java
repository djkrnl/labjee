package com.example.labjee.helpers.ArticleSaver.implementation;

import com.example.labjee.helpers.ArticleSaver.SavableArticleData;
// Tydzień 3 - wzorzec Bridge - implementacja

public interface ArticleSaver {
    public byte[] saveArticle(SavableArticleData data) throws Exception;
}
