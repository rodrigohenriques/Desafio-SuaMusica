package br.com.suamusica.app.ui.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import br.com.suamusica.app.AndroidApplication;
import br.com.suamusica.app.Navigator;
import br.com.suamusica.app.di.AppComponent;

public class BaseActivity extends AppCompatActivity {

    AppComponent mApplicationComponent;

    @Inject Navigator mNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplicationComponent =  ((AndroidApplication) getApplication()).getApplicationComponent();
        mApplicationComponent.inject(this);
    }
}
