package br.com.suamusica.app.presenter;

import br.com.suamusica.app.entities.AlbumViewModel;
import br.com.suamusica.app.presenter.view.TrendingMusicView;

public interface TrendingAlbumsPresenter extends Presenter {
    void attachView(TrendingMusicView view);
    void queryData();
    void clickedAt(AlbumViewModel album, int position);
}
