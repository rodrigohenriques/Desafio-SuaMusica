package br.com.suamusica.app.entities;

import java.util.List;

import br.com.suamusica.data.entities.ListMarshaller;
import br.com.suamusica.data.entities.Marshaller;
import br.com.suamusica.domain.entities.AlbumDetail;
import br.com.suamusica.domain.entities.Song;

public class AlbumDetailViewModel {
    private AlbumDetail albumDetail;

    public AlbumDetailViewModel(AlbumDetail albumDetail) {
        this.albumDetail = albumDetail;
    }

    public boolean hasSongs() {
        return albumDetail.songs != null && albumDetail.songs.size() > 0;
    }

    public List<SongViewModel> getSongs() {
        ListMarshaller<Song, SongViewModel> listMarshaller = new ListMarshaller<>(new Marshaller<Song, SongViewModel>() {
            @Override
            public SongViewModel marshal(Song song) {
                return new SongViewModel(song);
            }

            @Override
            public Song unmarshal(SongViewModel input) {
                return null;
            }
        });

        return listMarshaller.marshal(albumDetail.songs);
    }
}
