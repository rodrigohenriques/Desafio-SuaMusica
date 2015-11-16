package br.com.suamusica.app.di;

import android.app.Application;

import javax.inject.Singleton;

import br.com.suamusica.app.interactor.AndroidUiThreadExecutor;
import br.com.suamusica.app.presenter.AlbumDetailPresenter;
import br.com.suamusica.app.presenter.AlbumDetailPresenterImpl;
import br.com.suamusica.app.presenter.TrendingAlbumsPresenter;
import br.com.suamusica.app.presenter.TrendingAlbumsPresenterImpl;
import br.com.suamusica.domain.interactor.GetAlbumDetailUseCase;
import br.com.suamusica.domain.interactor.GetAlbumDetailUseCaseImpl;
import br.com.suamusica.domain.interactor.ListTrendingAlbumsUseCase;
import br.com.suamusica.domain.interactor.ListTrendingAlbumsUseCaseImpl;
import br.com.suamusica.domain.interactor.UiThreadExecutor;
import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides @Singleton public Application provideContext() {
        return application;
    }

    @Provides @Singleton public UiThreadExecutor provideUiThreadExecutor() {
        return new AndroidUiThreadExecutor();
    }

    @Provides @Singleton public TrendingAlbumsPresenter provideTrendingMusicPresenter(TrendingAlbumsPresenterImpl trendingMusicPresenter) {
        return trendingMusicPresenter;
    }

    @Provides @Singleton public ListTrendingAlbumsUseCase provideListTrendingAlbumsUseCase(ListTrendingAlbumsUseCaseImpl listTrendingAlbumsUseCase) {
        return  listTrendingAlbumsUseCase;
    }

    @Provides @Singleton public AlbumDetailPresenter provideAlbumDetailPresenter(AlbumDetailPresenterImpl albumDetailPresenter) {
        return albumDetailPresenter;
    }

    @Provides @Singleton public GetAlbumDetailUseCase provideGetAlbumDetailUseCase(GetAlbumDetailUseCaseImpl albumDetailUseCase) {
        return albumDetailUseCase;
    }
}
