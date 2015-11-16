package br.com.suamusica.data.entities;

import br.com.suamusica.domain.entities.Song;

public class SongEntityMarshaller implements Marshaller<SongEntity, Song> {
    @Override
    public Song marshal(SongEntity songEntity) {
        return new Song(songEntity.id, songEntity.fileName, songEntity.plays);
    }

    @Override
    public SongEntity unmarshal(Song input) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}
