package com.github.rodrigohenriques.mvp.sample.data.api;

import com.github.rodrigohenriques.mvp.sample.data.entities.AlbumEntity;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SuaMusicaApi {
    @POST("newapi/json_maisBaixadostmp.php")
    Call<List<AlbumEntity>> listTrendingMusic(@Body TrendingMusicRequest request);
}
