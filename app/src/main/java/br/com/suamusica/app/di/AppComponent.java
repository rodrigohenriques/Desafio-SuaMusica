package br.com.suamusica.app.di;

import br.com.suamusica.app.ui.activities.BaseActivity;
import br.com.suamusica.data.di.DataModule;

import br.com.suamusica.app.ui.activities.TrendingAlbumsActivity;
import dagger.Component;

@Component( modules = { DataModule.class, AppModule.class } )
public interface AppComponent {
    void inject(BaseActivity baseActivity);
    void inject(TrendingAlbumsActivity activity);
}
