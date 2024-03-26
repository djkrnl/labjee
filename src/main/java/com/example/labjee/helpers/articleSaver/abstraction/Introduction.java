package com.example.labjee.helpers.articleSaver.abstraction;

import com.example.labjee.helpers.articleSaver.implementation.ArticleSaver;

import java.lang.reflect.Array;

// Tydzień 3 - wzorzec Decorator - implementacja dekoratora
public class Introduction extends ArticleDecorator {

    Article article;

    String intro = "this is a shortened version of the article\n";

    public Introduction(ArticleSaver articleSaver, Article article) {
        super(articleSaver);
        this.article = article;
    }

    @Override
    public byte[] save() {
        return concatenate(intro.getBytes(), article.save());
    }

    private byte[] concatenate(byte[] a, byte[] b) {
        int aLen = a.length;
        int bLen = b.length;

        @SuppressWarnings("unchecked")
        byte[] c = (byte[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);

        return c;
    }
}
