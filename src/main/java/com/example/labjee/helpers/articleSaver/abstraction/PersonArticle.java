package com.example.labjee.helpers.articleSaver.abstraction;

import com.example.labjee.helpers.BirthDateAdapter;
import com.example.labjee.helpers.IBirthDateAdapter;
import com.example.labjee.helpers.articleSaver.SavableArticleData;
import com.example.labjee.helpers.articleSaver.implementation.ArticleSaver;
import com.example.labjee.models.Person;

import java.time.Year;
import java.util.Date;
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
        IBirthDateAdapter adapterCaller = new BirthDateAdapter(person.getBirthDate());
        String birthDate;
        if (Year.now().getValue() == person.getBirthDate().getYear()) {
            birthDate = adapterCaller.getBirthWhenDateText();
        } else {
            birthDate = adapterCaller.getBirthDateAgoText();
        }
        String fileData =
                this.person.getName() + "\n"
                + birthDate + "\n"
                + this.person.getBiography();
        data.setData(fileData);
        try {
            return articleSaver.saveArticle(data);
        } catch (Exception e) {}
        return new byte[0];
    }
}
