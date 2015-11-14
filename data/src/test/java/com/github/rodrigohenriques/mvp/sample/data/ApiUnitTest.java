package com.github.rodrigohenriques.mvp.sample.data;

import com.github.rodrigohenriques.mvp.sample.data.api.SuaMusicaApi;
import com.github.rodrigohenriques.mvp.sample.data.api.TrendingMusicRequest;
import com.github.rodrigohenriques.mvp.sample.data.di.DataModule;
import com.github.rodrigohenriques.mvp.sample.data.entities.AlbumEntity;

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