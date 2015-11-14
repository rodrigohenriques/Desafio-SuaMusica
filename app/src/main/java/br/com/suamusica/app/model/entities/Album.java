package br.com.suamusica.app.model.entities;

public class Album {
    public final String author;
    public final String name;
    public final String coverUrl;

    public Album(String author, String name, String coverUrl) {
        this.author = author;
        this.name = name;
        this.coverUrl = coverUrl;
    }
}
