package br.com.suamusica.domain.interactor;

import br.com.suamusica.domain.entities.AlbumDetail;

public interface GetAlbumDetailUseCase extends UseCase {
    void execute(int albumId, Callback<AlbumDetail> callback);
}
