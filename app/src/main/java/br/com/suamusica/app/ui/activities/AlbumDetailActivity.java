package br.com.suamusica.app.ui.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;

import br.com.suamusica.app.R;
import br.com.suamusica.app.presenter.view.AlbumDetailView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumDetailActivity extends BaseActivity implements AlbumDetailView {

    public static final String ALBUM_ID = "album-id";
    public static final String COVER_URL = "cover-url";

    @Bind(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        initializeDependencies();

        configureViews();
    }

    private void configureViews() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void initializeDependencies() {
        mApplicationComponent.inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab)
    void favoriteAlbum(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
