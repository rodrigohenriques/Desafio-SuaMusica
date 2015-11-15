package br.com.suamusica.data.repository;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import br.com.suamusica.data.api.SuaMusicaApi;
import br.com.suamusica.data.api.TrendingMusicRequest;
import br.com.suamusica.data.entities.AlbumEntity;
import br.com.suamusica.data.entities.AlbumEntityListMarshaller;
import br.com.suamusica.domain.entities.Album;
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
        TrendingMusicRequest request = new TrendingMusicRequest(page, queryType);

        Call<List<AlbumEntity>> call = mSuaMusicaApi.listTrendingMusic(request);

        Response<List<AlbumEntity>> response;
        try {
            response = call.execute();

            if (response.isSuccess()) {
                List<AlbumEntity> episodeEntities = response.body();

                return new AlbumEntityListMarshaller().marshal(episodeEntities);
            } else {
                throw new IOException("could not retrieve albums from sua musica api: " + response.message());
            }
        } catch (IOException e) {
            throw new IOException("could not retrieve albums from sua musica api: ", e);
        }
    }
}
