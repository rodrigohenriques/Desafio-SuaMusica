package br.com.suamusica.app.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.suamusica.app.entities.AlbumViewModel;
import br.com.suamusica.app.presenter.view.TrendingMusicView;
import br.com.suamusica.domain.entities.Album;
import br.com.suamusica.domain.entities.QueryType;
import br.com.suamusica.domain.interactor.Callback;
import br.com.suamusica.domain.interactor.ListTrendingAlbumsUseCase;

public class TrendingAlbumsPresenterImpl implements TrendingAlbumsPresenter {

    private ListTrendingAlbumsUseCase mListTrendingAlbumsUseCase;
    private TrendingMusicView mTrendingMusicView;
    private boolean isLoading = false;
    private List<Album> mAlbumsCache;
    private long mCacheExpirationTime;

    private static final long CACHE_TIME = 30000;

    @Inject
    public TrendingAlbumsPresenterImpl(ListTrendingAlbumsUseCase listTrendingAlbumsUseCase) {
        this.mListTrendingAlbumsUseCase = listTrendingAlbumsUseCase;
    }

    @Override
    public void queryData() {
        if (shouldQueryData()) {
            showLoading();

            mListTrendingAlbumsUseCase.execute(1, QueryType.ALWAYS, new Callback<List<Album>>() {
                @Override
                public void onSuccess(List<Album> albums) {
                    mAlbumsCache = albums;
                    mCacheExpirationTime = System.currentTimeMillis() + CACHE_TIME;
                    showData(albums);
                }

                @Override
                public void onException(Exception e) {}

                @Override
                public void onPostExecute() {
                    hideLoading();
                }
            });
        }
    }

    private boolean shouldQueryData() {
        return !hasValidCache();
    }

    private boolean hasValidCache() {
        return mAlbumsCache != null && System.currentTimeMillis() < mCacheExpirationTime;
    }

    @Override
    public void clickedAt(AlbumViewModel album, int position) {
        if (mTrendingMusicView != null)
            mTrendingMusicView.openAlbumDetail(album.getId());
    }

    private void showLoading() {
        isLoading = true;

        if (mTrendingMusicView != null)
            mTrendingMusicView.showLoading();
    }

    private void hideLoading() {
        isLoading = false;

        if (mTrendingMusicView != null)
            mTrendingMusicView.hideLoading();
    }

    private void showData(List<Album> albums) {
        if (mTrendingMusicView != null) {
            List<AlbumViewModel> albumViewModels = new ArrayList<>(albums.size());

            for (Album album : albums) {
                AlbumViewModel albumViewModel = new AlbumViewModel(album);
                albumViewModels.add(albumViewModel);
            }

            mTrendingMusicView.showData(albumViewModels);
        }
    }

    @Override
    public void attachView(TrendingMusicView view) {
        mTrendingMusicView = view;

        if (isLoading) {
            showLoading();
        } else {
            hideLoading();
        }

        if (hasValidCache()) {
            showData(mAlbumsCache);
        }
    }
}
