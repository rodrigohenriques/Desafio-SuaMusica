package br.com.suamusica.app.presenter;

import java.util.List;

import javax.inject.Inject;

import br.com.suamusica.app.presenter.view.TrendingMusicView;
import br.com.suamusica.domain.entities.Album;
import br.com.suamusica.domain.entities.QueryType;
import br.com.suamusica.domain.interactor.Callback;
import br.com.suamusica.domain.interactor.ListTrendingAlbumsUseCase;

public class TrendingAlbumsPresenterImpl implements TrendingAlbumsPresenter {
    @Inject
    ListTrendingAlbumsUseCase mListTrendingAlbumsUseCase;
    TrendingMusicView mTrendingMusicView;

    @Override
    public void queryData() {
        showLoading();

        mListTrendingAlbumsUseCase.execute(1, QueryType.ALWAYS, new Callback<List<Album>>() {
            @Override
            public void onSuccess(List<Album> albums) {
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
            mTrendingMusicView.showData(albums);
        }
    }

    @Override
    public void attachView(TrendingMusicView view) {
        mTrendingMusicView = view;
    }
}
