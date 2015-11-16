package br.com.suamusica.app.entities;

import br.com.suamusica.domain.entities.Song;

public class SongViewModel {
    private Song song;

    public SongViewModel(Song song) {
        this.song = song;
    }

    public String getName() {
        return song.name.replace(".mp3", "");
    }

    public String getFileName() {
        return song.name;
    }

    public String getPlays() {
        return String.format("%d plays", song.plays);
    }
}
