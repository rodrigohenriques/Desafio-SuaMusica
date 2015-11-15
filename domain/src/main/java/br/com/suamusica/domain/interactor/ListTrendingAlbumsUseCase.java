package br.com.suamusica.domain.interactor;

import java.util.List;

import br.com.suamusica.domain.entities.Album;
import br.com.suamusica.domain.entities.QueryType;

public interface ListTrendingAlbumsUseCase extends UseCase {
    void execute(int page, QueryType queryType, Callback<List<Album>> callback);
}
