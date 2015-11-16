package br.com.suamusica.domain.interactor;

import java.io.IOException;

import javax.inject.Inject;

import br.com.suamusica.domain.entities.AlbumDetail;
import br.com.suamusica.domain.repository.AlbumsRepository;

public class GetAlbumDetailUseCaseImpl extends AbstractUseCase<Integer, AlbumDetail> implements GetAlbumDetailUseCase {

    AlbumsRepository mAlbumsRepository;

    @Inject
    public GetAlbumDetailUseCaseImpl(UiThreadExecutor mUiThreadExecutor, AlbumsRepository albumsRepository) {
        super(mUiThreadExecutor);

        this.mAlbumsRepository = albumsRepository;
    }

    @Override
    public void execute(int albumId, Callback<AlbumDetail> callback) {
        executeAsync(albumId, callback);
    }

    @Override
    protected AlbumDetail executeOnBackground(Integer albumId) throws IOException {
        return mAlbumsRepository.getAlbumDetailById(albumId);
    }
}
