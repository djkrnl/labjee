package com.example.labjee.helpers.ArticleSaver.implementation;

import com.example.labjee.helpers.ArticleSaver.SavableArticleData;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
// Tydzień 3 - wzorzec Bridge - implementacja

public class MovieArticleSaver implements ArticleSaver {
    @Override
    public byte[] saveArticle(SavableArticleData data) throws Exception {
        File file = new File("src\\main\\resources\\files\\movies/" + data.getFileName());
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(data.getData());
        writer.close();
        InputStream in = new FileInputStream("src\\main\\resources\\files\\movies/" + data.getFileName());
        return in.readAllBytes();
    }
}
