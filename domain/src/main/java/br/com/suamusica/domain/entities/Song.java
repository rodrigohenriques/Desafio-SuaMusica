package br.com.suamusica.domain.entities;

public class Song {
    public final int id;
    public final String name;
    public final int plays;

    public Song(int id, String name, int plays) {
        this.id = id;
        this.name = name;
        this.plays = plays;
    }
}
