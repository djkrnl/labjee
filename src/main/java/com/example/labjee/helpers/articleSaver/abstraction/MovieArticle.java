package com.example.labjee.helpers.articleSaver.abstraction;

import com.example.labjee.helpers.articleSaver.SavableArticleData;
import com.example.labjee.helpers.articleSaver.implementation.ArticleSaver;
import com.example.labjee.models.Movie;
// Tydzień 3 - wzorzec Bridge - abstrakcja

public class MovieArticle extends Article {

    Movie movie;

    public MovieArticle(ArticleSaver articleSaver, Movie movie) {
        super(articleSaver);
        this.movie = movie;
    }
    @Override
    public byte[] save() {
        SavableArticleData data = new SavableArticleData();
        data.setFileName("movie_" + this.movie.getId() + ".txt");
        String fileData =
                this.movie.getTitle() + "\n"
                        + "Released: " + this.movie.getReleaseDate().toString() + "\n"
                        + this.movie.getDescription();
        data.setData(fileData);
        try {
            return articleSaver.saveArticle(data);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new byte[0];
    }
}
