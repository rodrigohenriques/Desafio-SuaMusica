package br.com.suamusica.app.ui.activities;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import br.com.suamusica.app.R;
import br.com.suamusica.app.entities.AlbumViewModel;
import br.com.suamusica.app.presenter.TrendingAlbumsPresenter;
import br.com.suamusica.app.presenter.view.TrendingMusicView;
import br.com.suamusica.app.ui.adapters.TrendingAlbumsAdapter;
import br.com.suamusica.app.ui.custom.ItemDecorationTrendingAlbums;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TrendingAlbumsActivity extends BaseActivity implements TrendingMusicView, TrendingAlbumsAdapter.OnItemClickListener {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.recycler_view_grid) RecyclerView mRecyclerView;

    @Inject TrendingAlbumsPresenter mTrendingAlbumsPresenter;

    ProgressDialog mProgressDialog;
    StaggeredGridLayoutManager mStaggeredLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_music);

        initializeDependencies();

        configureViews();

        mTrendingAlbumsPresenter.queryData();
        mTrendingAlbumsPresenter.attachView(this);
    }

    private void configureViews() {
        setSupportActionBar(mToolbar);

        int gridSpanCount;
        int gridSpace;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridSpanCount = 2;
            gridSpace = 4;
        } else {
            gridSpanCount = 1;
            gridSpace = 16;
        }

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(gridSpanCount, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        mRecyclerView.addItemDecoration(new ItemDecorationTrendingAlbums(gridSpace, gridSpanCount));
    }

    private void initializeDependencies() {
        mApplicationComponent.inject(this);
        ButterKnife.bind(this);
    }

    @Override
    public void showData(List<AlbumViewModel> albums) {
        TrendingAlbumsAdapter adapter = new TrendingAlbumsAdapter(this, albums);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void openAlbumDetail(int albumId) {
        mNavigator.openAlbumDetail(this, albumId);
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
    public void onClick(AlbumViewModel album, int position) {
        mTrendingAlbumsPresenter.clickedAt(album, position);
    }
}
