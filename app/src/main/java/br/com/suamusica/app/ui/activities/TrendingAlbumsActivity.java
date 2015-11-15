package br.com.suamusica.app.ui.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.cocosw.bottomsheet.BottomSheet;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_music);

        initializeDependencies();

        configureViews();

        mTrendingAlbumsPresenter.attachView(this);
    }

    private void configureViews() {
        setSupportActionBar(mToolbar);
    }

    private void initializeDependencies() {
        mApplicationComponent.inject(this);
        ButterKnife.bind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_trending_music, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_filter:
                mTrendingAlbumsPresenter.clickedAtFilterMenu();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showData(List<AlbumViewModel> albums) {
        int gridSpanCount;
        int gridSpace;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridSpanCount = 2;
            gridSpace = 4;
        } else {
            gridSpanCount = 1;
            gridSpace = 16;
        }

        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(gridSpanCount, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        mRecyclerView.addItemDecoration(new ItemDecorationTrendingAlbums(gridSpace, gridSpanCount));
        TrendingAlbumsAdapter adapter = new TrendingAlbumsAdapter(this, albums);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void openAlbumDetail(int albumId) {
        mNavigator.openAlbumDetail(this, albumId);
    }

    @Override
    public void showQueryTypesToSelect() {
        new BottomSheet.Builder(this)
                .sheet(R.menu.menu_query_types)
                .listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        switch (which) {
                            case R.id.general:
                                mTrendingAlbumsPresenter.loadTrendingAlbumsEver();
                                break;
                            case R.id.year:
                                mTrendingAlbumsPresenter.loadTrendingAlbumsOfThisYear();
                                break;
                            case R.id.month:
                                mTrendingAlbumsPresenter.loadTrendingAlbumsOfThisMonth();
                                break;
                            case R.id.week:
                                mTrendingAlbumsPresenter.loadTrendingAlbumsOfThisWeek();
                                break;
                        }
                    }
                }).show();
    }

    @Override
    public void changeTitle(int titleId) {
        getSupportActionBar().setTitle(titleId);
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
