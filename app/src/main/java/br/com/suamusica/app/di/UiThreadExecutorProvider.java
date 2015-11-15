package br.com.suamusica.app.di;


import javax.inject.Provider;

import br.com.suamusica.app.interactor.AndroidUiThreadExecutor;
import br.com.suamusica.domain.interactor.UiThreadExecutor;

public class UiThreadExecutorProvider implements Provider<UiThreadExecutor> {
    @Override
    public UiThreadExecutor get() {
        return new AndroidUiThreadExecutor();
    }
}
