package br.com.suamusica.data.repository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import br.com.suamusica.data.api.SuaMusicaApi;
import br.com.suamusica.data.entities.AlbumDetailEntity;
import br.com.suamusica.data.entities.AlbumDetailEntityMarshaller;
import br.com.suamusica.data.entities.AlbumEntity;
import br.com.suamusica.data.entities.AlbumEntityMarshaller;
import br.com.suamusica.data.entities.ListMarshaller;
import br.com.suamusica.domain.entities.Album;
import br.com.suamusica.domain.entities.AlbumDetail;
import br.com.suamusica.domain.entities.QueryType;
import br.com.suamusica.domain.repository.AlbumsRepository;
import retrofit.Call;
import retrofit.Response;

public class RemoteAlbumsRepository implements AlbumsRepository {

    SuaMusicaApi mSuaMusicaApi;

    @Inject
    public RemoteAlbumsRepository(SuaMusicaApi suaMusicaApi) {
        this.mSuaMusicaApi = suaMusicaApi;
    }

    @Override
    public List<Album> listTrendingMusic(int page, QueryType queryType) throws IOException {
        Call<List<AlbumEntity>> call = mSuaMusicaApi.listTrendingMusic(page, queryType.typeString());

        Response<List<AlbumEntity>> response;
        try {
            response = call.execute();

            if (response.isSuccess()) {
                List<AlbumEntity> episodeEntities = response.body();

                ListMarshaller<AlbumEntity, Album> listMarshaller = new ListMarshaller<>(new AlbumEntityMarshaller());
                return listMarshaller.marshal(episodeEntities);
            } else {
                throw new IOException("could not retrieve albums from sua musica api: " + response.message());
            }
        } catch (IOException e) {
            throw new IOException("could not retrieve albums from sua musica api: ", e);
        }
    }

    @Override
    public AlbumDetail getAlbumDetailById(int albumId) throws IOException {
        Call<AlbumDetailEntity> call = mSuaMusicaApi.getAlbumDetailedById(albumId);

        Response<AlbumDetailEntity> response;
        try {
            response = call.execute();

            if (response.isSuccess()) {
                AlbumDetailEntity albumDetailEntity = response.body();

                return new AlbumDetailEntityMarshaller().marshal(albumDetailEntity);
            } else {
                throw new IOException("could not retrieve albums from sua musica api: " + response.message());
            }
        } catch (IOException e) {
            throw new IOException("could not retrieve albums from sua musica api: ", e);
        }
    }
}
