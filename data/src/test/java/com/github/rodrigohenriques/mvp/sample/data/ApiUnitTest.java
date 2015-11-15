package com.github.rodrigohenriques.mvp.sample.data;

import br.com.suamusica.data.api.SuaMusicaApi;
import br.com.suamusica.data.api.TrendingMusicRequest;
import br.com.suamusica.data.di.DataModule;
import br.com.suamusica.data.entities.AlbumEntity;

import org.junit.Test;

import java.util.List;

import retrofit.Call;
import retrofit.Response;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ApiUnitTest {
    @Test
    public void testListTrendingMusic() throws Exception {
        SuaMusicaApi api = new DataModule().provideApi();

        TrendingMusicRequest request = new TrendingMusicRequest(1, TrendingMusicRequest.QueryType.ALWAYS);
        Call<List<AlbumEntity>> call = api.listTrendingMusic(request);

        Response<List<AlbumEntity>> response = call.execute();

        assertTrue(response.isSuccess());

        List<AlbumEntity> albumEntities = response.body();

        assertNotNull(albumEntities);
    }
}