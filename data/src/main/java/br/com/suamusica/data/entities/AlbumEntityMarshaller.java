package br.com.suamusica.data.entities;


import br.com.suamusica.domain.entities.Album;

public class AlbumEntityMarshaller implements Marshaller<AlbumEntity, Album> {

    @Override
    public Album marshal(AlbumEntity input) {
        return new Album(input.username, input.titulo, input.cover);
    }

    @Override
    public AlbumEntity unmarshal(Album input) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
