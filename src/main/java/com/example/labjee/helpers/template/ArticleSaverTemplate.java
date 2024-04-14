package com.example.labjee.helpers.template;

import com.example.labjee.helpers.articleSaver.SavableArticleData;
import com.example.labjee.helpers.articleSaver.implementation.ArticleSaver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStream;

// Tydzień 6 - Template - abstrakcyjny template
// Tydzień 7 - zasada otwarte-zamknięte
public abstract class ArticleSaverTemplate implements ArticleSaver {

    protected SavableArticleData data;

    @Override
    public byte[] saveArticle(SavableArticleData data) throws Exception {
        this.data = data;
        File file = prepareFile();
        createFile(file);
        InputStream in = getStreamFromFile();
        return in.readAllBytes();
    }

    final void createFile(File file) throws Exception {
        FileWriter writer = new FileWriter(file);
        writer.write(this.data.getData());
        writer.close();
    }

    protected abstract File prepareFile();

    protected abstract InputStream getStreamFromFile() throws FileNotFoundException;
}
