package br.com.suamusica.data.api;

import java.util.List;

import br.com.suamusica.data.entities.AlbumEntity;
import retrofit.Call;
import retrofit.http.POST;
import retrofit.http.Query;

public interface SuaMusicaApi {
    @POST("newapi/json_maisBaixadostmp.php")
    Call<List<AlbumEntity>> listTrendingMusic(@Query("page") int page, @Query("tipo") String type);
}
