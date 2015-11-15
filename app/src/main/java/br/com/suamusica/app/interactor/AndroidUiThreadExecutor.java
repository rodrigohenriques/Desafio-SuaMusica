package br.com.suamusica.app.interactor;

import android.os.Handler;
import android.os.Looper;

import javax.inject.Inject;

import br.com.suamusica.domain.interactor.UiThreadExecutor;

public class AndroidUiThreadExecutor implements UiThreadExecutor {
    Handler handler;

    @Inject
    public AndroidUiThreadExecutor() {
        this.handler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void execute(Runnable runnable) {
        handler.post(runnable);
    }
}
