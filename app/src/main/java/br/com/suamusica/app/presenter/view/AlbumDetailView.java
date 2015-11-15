package br.com.suamusica.app.presenter.view;

import br.com.suamusica.app.entities.AlbumDetailViewModel;

public interface AlbumDetailView extends View {
    void showDetails(AlbumDetailViewModel albumDetailViewModel);
    void showCoverImage(String coverUrl);
}
