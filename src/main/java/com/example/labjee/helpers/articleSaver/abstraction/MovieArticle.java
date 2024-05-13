package com.example.labjee.helpers.articleSaver.abstraction;

import com.example.labjee.helpers.articleSaver.SaveableArticleData;
import com.example.labjee.helpers.articleSaver.implementation.ArticleSaver;
import com.example.labjee.helpers.flyweight.FlyweightPersonClient;
import com.example.labjee.models.Movie;
import com.example.labjee.models.MovieActor;
import com.example.labjee.models.MovieDirector;
import com.example.labjee.models.MovieWriter;

import java.util.ArrayList;
// Tydzień 3 - wzorzec Bridge - abstrakcja

public class MovieArticle extends Article {
    Movie movie;

    public MovieArticle(ArticleSaver articleSaver, Movie movie) {
        super(articleSaver);
        this.movie = movie;
    }
    @Override
    public byte[] save() {
        SaveableArticleData data = new SaveableArticleData();
        data.setFileName("movie_" + this.movie.getId() + ".txt");
        String fileData = this.movie.getTitle() + "\n" + "Released: " + this.movie.getReleaseDate().toString() + "\n" + this.movie.getDescription();
        ArrayList<FlyweightPersonClient> people = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();
        // Tydzien 4 - Flyweight
        this.addFlyweight(people, names);
        for (int i = 0; i < people.size(); i++) {
            fileData = fileData.concat("\n" + people.get(i).getText(names.get(i)));
        }
        // Tydzien 4 - Flyweight - koniec
        data.setData(fileData);
        try {
            return articleSaver.saveArticle(data);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new byte[0];
    }

    private void addFlyweight(ArrayList<FlyweightPersonClient> people, ArrayList<String> names) {
        for (MovieWriter writer : this.movie.getWriters()) {
            people.add(new FlyweightPersonClient("staff"));
            names.add(writer.getWriter().getName());
        }
        for (MovieDirector director : this.movie.getDirectors()) {
            people.add(new FlyweightPersonClient("staff"));
            names.add(director.getDirector().getName());
        }
        for (MovieActor actor : this.movie.getActors()) {
            people.add(new FlyweightPersonClient("actor"));
            names.add(actor.getActor().getName());
        }
    }
}
