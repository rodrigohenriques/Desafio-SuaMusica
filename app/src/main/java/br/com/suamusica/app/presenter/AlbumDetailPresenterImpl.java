package br.com.suamusica.app.presenter;

import javax.inject.Inject;

import br.com.suamusica.app.entities.AlbumDetailViewModel;
import br.com.suamusica.app.presenter.view.AlbumDetailView;
import br.com.suamusica.domain.entities.AlbumDetail;
import br.com.suamusica.domain.interactor.Callback;
import br.com.suamusica.domain.interactor.GetAlbumDetailUseCase;

public class AlbumDetailPresenterImpl implements AlbumDetailPresenter {

    private GetAlbumDetailUseCase mGetAlbumDetailUseCase;
    private AlbumDetailView mAlbumDetailView;

    @Inject
    public AlbumDetailPresenterImpl(GetAlbumDetailUseCase getAlbumDetailUseCase) {
        this.mGetAlbumDetailUseCase = getAlbumDetailUseCase;
    }

    @Override
    public void attachView(AlbumDetailView albumDetailView) {
        this.mAlbumDetailView = albumDetailView;

        loadAlbumDetails(mAlbumDetailView.getAlbumId());
    }

    public void loadAlbumDetails(int albumId) {
        showLoading();
        mGetAlbumDetailUseCase.execute(albumId, new Callback<AlbumDetail>() {
            @Override
            public void onSuccess(AlbumDetail albumDetail) {
                showDetails(albumDetail);
            }

            @Override
            public void onException(Exception e) {
                showError(DEFAULT_ERROR_MESSAGE);
            }

            @Override
            public void onPostExecute() {
                hideLoading();
            }
        });
    }

    private void showError(String message) {
        if (mAlbumDetailView != null) {
            mAlbumDetailView.showError(message);
        }
    }

    private void showLoading() {
        if (mAlbumDetailView != null) {
            mAlbumDetailView.showLoading();
        }
    }

    private void hideLoading() {
        if (mAlbumDetailView != null) {
            mAlbumDetailView.hideLoading();
        }
    }

    private void showDetails(AlbumDetail albumDetail) {
        if (mAlbumDetailView != null) {
            mAlbumDetailView.showDetails(new AlbumDetailViewModel(albumDetail));
        }
    }
}
