package br.com.suamusica.domain.entities;

public class Album {
    public final int id;
    public final String username;
    public final String name;
    public final String coverUrl;
    public final int downloads;

    public Album(int id, String username, String name, String coverUrl, int downloads) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.coverUrl = coverUrl;
        this.downloads = downloads;
    }
}
