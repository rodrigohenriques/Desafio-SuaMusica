package br.com.suamusica.domain.interactor;

public interface UiThreadExecutor {
    void execute(Runnable runnable);
}
