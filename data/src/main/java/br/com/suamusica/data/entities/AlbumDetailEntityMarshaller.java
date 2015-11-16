package br.com.suamusica.data.entities;

import java.util.List;

import br.com.suamusica.domain.entities.AlbumDetail;
import br.com.suamusica.domain.entities.Song;

public class AlbumDetailEntityMarshaller implements Marshaller<AlbumDetailEntity, AlbumDetail>{
    @Override
    public AlbumDetail marshal(AlbumDetailEntity albumDetailEntity) {
        ListMarshaller<SongEntity, Song> listMarshaller = new ListMarshaller<>(new SongEntityMarshaller());
        List<Song> songs = listMarshaller.marshal(albumDetailEntity.songs);
        return new AlbumDetail(albumDetailEntity.name, albumDetailEntity.plays, songs);
    }

    @Override
    public AlbumDetailEntity unmarshal(AlbumDetail input) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
