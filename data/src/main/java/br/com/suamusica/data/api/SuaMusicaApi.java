package br.com.suamusica.data.api;

import br.com.suamusica.data.entities.AlbumEntity;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SuaMusicaApi {
    @POST("newapi/json_maisBaixadostmp.php")
    Call<List<AlbumEntity>> listTrendingMusic(@Body TrendingMusicRequest request);
}
