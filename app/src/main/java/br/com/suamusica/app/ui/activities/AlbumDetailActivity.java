package br.com.suamusica.app.ui.activities;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import br.com.suamusica.app.R;
import br.com.suamusica.app.entities.AlbumDetailViewModel;
import br.com.suamusica.app.presenter.view.AlbumDetailView;
import br.com.suamusica.app.ui.custom.PaletteTransformation;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlbumDetailActivity extends BaseActivity implements AlbumDetailView {

    public static final String ALBUM_ID = "album-id";
    public static final String COVER_URL = "cover-url";
    public static final String ALBUM_NAME = "album-name";

    private int mAlbumId;
    private String mAlbumName;
    private String mCoverUrl;

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.collapsing_toolbar) CollapsingToolbarLayout mCollapsingToolbarLayout;
    @Bind(R.id.imageview_album_detail_cover) ImageView mImageViewAlbumCover;
    @Bind(R.id.view_album_detail_cover_background) View mCoverTextBackground;
    @Bind(R.id.fab_album_detail_favorite) FloatingActionButton mFabFavoriteAlbum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        initializeDependencies();

        configureExtras();

        configureViews();
    }

    private void configureExtras() {
        mAlbumId = getIntent().getIntExtra(ALBUM_ID, -1);
        mAlbumName = getIntent().getStringExtra(ALBUM_NAME);
        mCoverUrl = getIntent().getStringExtra(COVER_URL);
    }

    @SuppressWarnings("ConstantConditions")
    private void configureViews() {
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mAlbumName);

        Picasso.with(this)
                .load(mCoverUrl)
                .transform(PaletteTransformation.instance())
                .fit()
                .placeholder(R.drawable.placeholder)
                .into(mImageViewAlbumCover);

        int primaryDarkColor = getResources().getColor(R.color.colorPrimaryDark);

        GradientDrawable gd = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP,
                new int[]{ primaryDarkColor, Color.alpha(primaryDarkColor)});
        gd.setCornerRadius(0f);

        mCoverTextBackground.setBackground(gd);
    }

    private void initializeDependencies() {
        mApplicationComponent.inject(this);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab_album_detail_favorite)
    void favoriteAlbum(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void showDetails(AlbumDetailViewModel albumDetailViewModel) {

    }
}
