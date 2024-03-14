package com.example.labjee.helpers.ArticleSaver.abstraction;

import com.example.labjee.helpers.ArticleSaver.SavableArticleData;
import com.example.labjee.helpers.ArticleSaver.implementation.ArticleSaver;
import com.example.labjee.models.Person;
// Tydzień 3 - wzorzec Bridge - abstrakcja

public class PersonArticle extends Article {

    Person person;

    public PersonArticle(ArticleSaver articleSaver, Person person) {
        super(articleSaver);
        this.person = person;
    }

    @Override
    public byte[] save() {
        SavableArticleData data = new SavableArticleData();
        data.setFileName("person_" + this.person.getId() + ".txt");
        String fileData =
                this.person.getName() + "\n"
                + "Born: " + this.person.getBirthDate().toString() + "\n"
                + this.person.getBiography();
        data.setData(fileData);
        try {
            return articleSaver.saveArticle(data);
        } catch (Exception e) {}
        return new byte[0];
    }
}
