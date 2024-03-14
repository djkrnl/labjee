package com.example.labjee.helpers.ArticleSaver.implementation;

import com.example.labjee.helpers.ArticleSaver.SavableArticleData;

public interface ArticleSaver {
    public byte[] saveArticle(SavableArticleData data) throws Exception;
}
