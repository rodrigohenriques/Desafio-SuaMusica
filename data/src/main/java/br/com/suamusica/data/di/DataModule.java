package br.com.suamusica.data.di;

import br.com.suamusica.data.api.SuaMusicaApi;
import br.com.suamusica.data.repository.RemoteAlbumsRepository;
import br.com.suamusica.domain.repository.AlbumsRepository;
import dagger.Module;
import dagger.Provides;
import retrofit.JacksonConverterFactory;
import retrofit.Retrofit;

@Module
public class DataModule {
    public static final String BASE_URL = "http://app.suamusica.com.br:8082/";

    @Provides
    public SuaMusicaApi provideApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();

        return retrofit.create(SuaMusicaApi.class);
    }

    @Provides public AlbumsRepository provideAlbumsRepository(RemoteAlbumsRepository albumsRepository) {
        return albumsRepository;
    }
}
