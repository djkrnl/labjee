package com.example.labjee.helpers.articleSaver.abstraction;

import com.example.labjee.helpers.articleSaver.implementation.ArticleSaver;

// Tydzień 3 - wzorzec Decorator - klasa dekoratora
public abstract class ArticleDecorator extends Article {
    public ArticleDecorator(ArticleSaver articleSaver) {
        super(articleSaver);
    }

    public abstract byte[] save();
}
