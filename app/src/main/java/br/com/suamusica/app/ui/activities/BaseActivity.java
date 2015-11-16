package br.com.suamusica.app.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import br.com.suamusica.app.AndroidApplication;
import br.com.suamusica.app.Navigator;
import br.com.suamusica.app.R;
import br.com.suamusica.app.di.AppComponent;
import br.com.suamusica.app.presenter.view.View;

public abstract class BaseActivity extends AppCompatActivity implements View {

    protected @Inject Navigator mNavigator;
    protected AppComponent mApplicationComponent;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mApplicationComponent =  ((AndroidApplication) getApplication()).getApplicationComponent();
        mApplicationComponent.inject(this);
    }

    @Override
    public void showLoading() {
        CharSequence title = getString(R.string.progress_title);
        CharSequence message = getString(R.string.progress_message);

        mProgressDialog = ProgressDialog.show(this, title, message, true, false);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void showError(String message) {
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.setAction("Fechar", null);
        snackbar.show();
    }
}
