package br.com.suamusica.app.presenter.view;

import java.util.List;

import br.com.suamusica.app.entities.AlbumViewModel;

public interface TrendingMusicView extends IView {
    void showData(List<AlbumViewModel> albums);
    void openAlbumDetail(int albumId, String albumName, String coverUrl);
    void showQueryTypesToSelect();
    void changeTitle(int titleId);
}
