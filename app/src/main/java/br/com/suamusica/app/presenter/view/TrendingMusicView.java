package br.com.suamusica.app.presenter.view;

import java.util.List;

import br.com.suamusica.domain.entities.Album;

public interface TrendingMusicView extends View {
    void showData(List<Album> albums);
}
