package br.com.suamusica.app.presenter.view;

import java.util.List;

import br.com.suamusica.app.entities.AlbumViewModel;

public interface TrendingMusicView extends View {
    void showData(List<AlbumViewModel> albums);
    void openAlbumDetail(int albumId);
    void showQueryTypesToSelect();
    void changeTitle(int titleId);
}
