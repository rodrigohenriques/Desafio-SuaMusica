package br.com.suamusica.domain.interactor;

import java.util.List;

import javax.inject.Inject;

import br.com.suamusica.domain.entities.Album;
import br.com.suamusica.domain.entities.QueryType;
import br.com.suamusica.domain.repository.AlbumsRepository;

public class ListTrendingAlbumsUseCaseImpl  extends AbstractUseCase implements ListTrendingAlbumsUseCase {

    private int mPage;
    private QueryType mQueryType;
    private Callback<List<Album>> mCallback;
    private AlbumsRepository mAlbumsRepository;

    @Inject
    public ListTrendingAlbumsUseCaseImpl(UiThreadExecutor mUiThreadExecutor, AlbumsRepository albumsRepository) {
        super(mUiThreadExecutor);

        this.mAlbumsRepository = albumsRepository;
    }

    @Override
    public void execute(int page, QueryType queryType, Callback<List<Album>> callback) {
        this.mPage = page;
        this.mQueryType = queryType;
        this.mCallback = callback;

        callback.setUiThreadExecutor(mUiThreadExecutor);

        executeAsync(this);
    }

    @Override
    public void run() {
        try {
            List<Album> albums = mAlbumsRepository.listTrendingMusic(mPage, mQueryType);

            mCallback.dispatchResult(albums);
        } catch (Exception e) {
            mCallback.dispatchException(e);
        }
    }
}
