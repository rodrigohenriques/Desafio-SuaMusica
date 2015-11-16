package br.com.suamusica.app.presenter.view;

public interface IView {
    void showLoading();
    void hideLoading();
    void showError(String message);
}
