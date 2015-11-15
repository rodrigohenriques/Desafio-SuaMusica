package br.com.suamusica.domain.interactor;

public abstract class AbstractUseCase {
    protected UiThreadExecutor mUiThreadExecutor;
    protected Thread mProcess;

    public AbstractUseCase(UiThreadExecutor mUiThreadExecutor) {
        this.mUiThreadExecutor = mUiThreadExecutor;
    }

    protected void executeAsync(Runnable runnable) {
        mProcess = new Thread(runnable);
        mProcess.start();
    }
}
