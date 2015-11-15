package br.com.suamusica.data.entities;

import java.util.ArrayList;
import java.util.List;

import br.com.suamusica.domain.entities.Album;

public class AlbumEntityListMarshaller implements Marshaller<List<AlbumEntity>, List<Album>> {
    @Override
    public List<Album> marshal(List<AlbumEntity> albumEntities) {
        List<Album> albums = new ArrayList<>(albumEntities.size());

        AlbumEntityMarshaller albumEntityMarshaller = new AlbumEntityMarshaller();

        for (AlbumEntity entity : albumEntities) {
            Album album = albumEntityMarshaller.marshal(entity);
            albums.add(album);
        }

        return albums;
    }

    @Override
    public List<AlbumEntity> unmarshal(List<Album> input) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
