package br.com.suamusica.app.presenter.view;

import br.com.suamusica.app.entities.AlbumDetailViewModel;

public interface AlbumDetailView extends IView {
    void showDetails(AlbumDetailViewModel albumDetailViewModel);
    int getAlbumId();
}
