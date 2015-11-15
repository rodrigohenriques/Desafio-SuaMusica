package br.com.suamusica.app;

import android.app.Application;

import br.com.suamusica.app.di.AppComponent;
import br.com.suamusica.app.di.AppModule;
import br.com.suamusica.app.di.DaggerAppComponent;
import br.com.suamusica.data.di.DataModule;

public class AndroidApplication extends Application {
    private AppComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .dataModule(new DataModule())
                .build();
    }

    public AppComponent getApplicationComponent() {
        return this.applicationComponent;
    }
}
