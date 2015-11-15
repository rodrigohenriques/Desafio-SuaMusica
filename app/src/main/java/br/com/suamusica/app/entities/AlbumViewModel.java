package br.com.suamusica.app.entities;

import java.text.NumberFormat;
import java.util.Locale;

import br.com.suamusica.domain.entities.Album;

public class AlbumViewModel {
    private Album album;

    public AlbumViewModel(Album album) {
        this.album = album;
    }

    public int getId() {
        return album.id;
    }

    public String getName() {
        return album.name;
    }

    public String getAuthor() {
        return album.username;
    }

    public Album getAlbum() {
        return album;
    }

    public String getDownloads() {
        NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMAN);
        return numberFormat.format(album.downloads) + " downloads";
    }

    public String getCoverUrl() {
        return album.coverUrl;
    }
}
