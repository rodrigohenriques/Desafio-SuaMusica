package br.com.suamusica.app.di;

import javax.inject.Singleton;

import br.com.suamusica.app.ui.activities.AlbumDetailActivity;
import br.com.suamusica.app.ui.activities.BaseActivity;
import br.com.suamusica.app.ui.activities.TrendingAlbumsActivity;
import br.com.suamusica.data.di.DataModule;
import dagger.Component;

@Singleton
@Component( modules = { DataModule.class, AppModule.class } )
public interface AppComponent {
    void inject(BaseActivity baseActivity);
    void inject(TrendingAlbumsActivity activity);
    void inject(AlbumDetailActivity activity);
}
