package br.com.suamusica.domain.entities;

import java.util.List;

public class AlbumDetail {
    public final String name;
    public final int plays;
    public final List<Song> songs;

    public AlbumDetail(String name, int plays, List<Song> songs) {
        this.name = name;
        this.plays = plays;
        this.songs = songs;
    }
}
