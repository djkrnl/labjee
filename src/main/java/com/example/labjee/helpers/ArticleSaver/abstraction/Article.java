package com.example.labjee.helpers.ArticleSaver.abstraction;

import com.example.labjee.helpers.ArticleSaver.implementation.ArticleSaver;
// Tydzień 3 - wzorzec Bridge - abstrakcja

public abstract class Article {
    ArticleSaver articleSaver;

    public Article(ArticleSaver articleSaver) {
        this.articleSaver = articleSaver;
    }

    abstract public byte[] save();
}
