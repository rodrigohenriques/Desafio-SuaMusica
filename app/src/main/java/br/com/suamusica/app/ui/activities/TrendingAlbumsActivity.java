package br.com.suamusica.app.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import br.com.suamusica.app.R;
import br.com.suamusica.app.presenter.TrendingAlbumsPresenter;
import br.com.suamusica.app.presenter.view.TrendingMusicView;
import br.com.suamusica.app.ui.adapters.TrendingAlbumsAdapter;
import br.com.suamusica.app.ui.custom.ItemDecorationTrendingAlbums;
import br.com.suamusica.domain.entities.Album;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TrendingAlbumsActivity extends BaseActivity implements TrendingMusicView, TrendingAlbumsAdapter.OnItemClickListener {

    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.recycler_view_grid)
    RecyclerView mRecyclerView;

    @Inject
    TrendingAlbumsPresenter mTrendingAlbumsPresenter;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_music);

        initializeDependencies();

        configureViews();
    }

    private void configureViews() {
        setSupportActionBar(mToolbar);
        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
    }

    private void initializeDependencies() {
        mApplicationComponent.inject(this);
        ButterKnife.bind(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTrendingAlbumsPresenter.queryData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTrendingAlbumsPresenter.attachView(this);
    }

    @Override
    public void showData(List<Album> albums) {
        TrendingAlbumsAdapter adapter = new TrendingAlbumsAdapter(this, albums);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addItemDecoration(new ItemDecorationTrendingAlbums(4, 2));
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
    public void onClick(Album album, int position) {
        Snackbar.make(mRecyclerView, album.name, Snackbar.LENGTH_LONG).show();
    }
}
