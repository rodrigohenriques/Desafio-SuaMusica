package br.com.suamusica.app.presenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.suamusica.app.R;
import br.com.suamusica.app.entities.AlbumViewModel;
import br.com.suamusica.app.presenter.view.TrendingMusicView;
import br.com.suamusica.domain.entities.Album;
import br.com.suamusica.domain.entities.QueryType;
import br.com.suamusica.domain.interactor.Callback;
import br.com.suamusica.domain.interactor.ListTrendingAlbumsUseCase;

public class TrendingAlbumsPresenterImpl implements TrendingAlbumsPresenter {

    private ListTrendingAlbumsUseCase mListTrendingAlbumsUseCase;
    private TrendingMusicView mTrendingMusicView;
    private List<Album> mAlbumsCache;
    private int mPage;
    private QueryType mQueryType;
    private long mCacheExpirationTime;

    private static final long CACHE_TIME = 30000;

    @Inject
    public TrendingAlbumsPresenterImpl(ListTrendingAlbumsUseCase listTrendingAlbumsUseCase) {
        this.mListTrendingAlbumsUseCase = listTrendingAlbumsUseCase;
    }

    @Override
    public void loadTrendingAlbumsEver() {
        queryData(1, QueryType.ALWAYS);
        changeTitle(R.string.title_trending_albums_ever);
    }

    private void changeTitle(int titleId) {
        mTrendingMusicView.changeTitle(titleId);
    }

    @Override
    public void loadTrendingAlbumsOfThisYear() {
        queryData(1, QueryType.THIS_YEAR);
        changeTitle(R.string.title_trending_albums_this_year);
    }

    @Override
    public void loadTrendingAlbumsOfThisMonth() {
        queryData(1, QueryType.THIS_MONTH);
        changeTitle(R.string.title_trending_albums_this_month);
    }

    @Override
    public void loadTrendingAlbumsOfThisWeek() {
        queryData(1, QueryType.THIS_WEEK);
        changeTitle(R.string.title_trending_albums_this_week);
    }

    private void queryData(final int page, final QueryType queryType) {
        if (shouldQueryData(page, queryType)) {
            showLoading();

            mListTrendingAlbumsUseCase.execute(page, queryType, new Callback<List<Album>>() {
                @Override
                public void onSuccess(List<Album> albums) {
                    mAlbumsCache = albums;
                    mCacheExpirationTime = System.currentTimeMillis() + CACHE_TIME;
                    mPage = page;
                    mQueryType = queryType;

                    showData(albums);
                }

                @Override
                public void onException(Exception e) {
                }

                @Override
                public void onPostExecute() {
                    hideLoading();
                }
            });
        }
    }

    private boolean shouldQueryData(int page, QueryType queryType) {
        return !hasValidCache(page, queryType);
    }

    private boolean hasValidCache(int page, QueryType queryType) {
        return page == mPage &&
                queryType == mQueryType &&
                mAlbumsCache != null &&
                System.currentTimeMillis() < mCacheExpirationTime;
    }

    private boolean hasValidCache() {
        return mPage != 0 && mQueryType != null && hasValidCache(mPage, mQueryType);
    }

    @Override
    public void clickedAt(AlbumViewModel album, int position) {
        if (mTrendingMusicView != null)
            mTrendingMusicView.openAlbumDetail(album.getId());
    }

    @Override
    public void clickedAtFilterMenu() {
        if (mTrendingMusicView != null) {
            mTrendingMusicView.showQueryTypesToSelect();
        }
    }

    private void showLoading() {
        if (mTrendingMusicView != null)
            mTrendingMusicView.showLoading();
    }

    private void hideLoading() {
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

        if (hasValidCache()) {
            showData(mAlbumsCache);
        } else {
            loadTrendingAlbumsEver();
        }
    }
}
