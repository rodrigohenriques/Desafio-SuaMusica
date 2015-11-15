package br.com.suamusica.app.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import java.util.List;

import javax.inject.Inject;

import br.com.suamusica.app.R;
import br.com.suamusica.app.presenter.TrendingMusicPresenter;
import br.com.suamusica.app.presenter.view.TrendingMusicView;
import br.com.suamusica.app.ui.activities.TrendingMusicAdapter;
import br.com.suamusica.domain.entities.Album;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TrendingAlbumsActivity extends AppCompatActivity implements TrendingMusicView {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.recycler_view_grid) RecyclerView mRecyclerView;

    @Inject
    TrendingMusicPresenter mTrendingMusicPresenter;

    ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_music);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);

        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mTrendingMusicPresenter.queryData();
    }

    @Override
    public void showData(List<Album> albums) {
        mRecyclerView.setAdapter(new TrendingMusicAdapter(this, albums));
    }

    @Override
    public void showLoading() {
        CharSequence title = getString(R.string.progress_title);
        CharSequence message = getString(R.string.progress_message);

        mProgressDialog = ProgressDialog.show(this, title, message, true, false);
    }

    @Override
    public void hideLoading() {
        mProgressDialog.dismiss();
    }
}
