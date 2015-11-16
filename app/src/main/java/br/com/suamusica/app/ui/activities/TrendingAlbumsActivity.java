package br.com.suamusica.app.ui.activities;

import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import butterknife.Bind;
import butterknife.ButterKnife;

public class TrendingAlbumsActivity extends BaseActivity implements TrendingMusicView, TrendingAlbumsAdapter.OnItemClickListener {

    @Bind(R.id.toolbar) Toolbar mToolbar;
    @Bind(R.id.recycler) RecyclerView mRecyclerView;

    @Inject TrendingAlbumsPresenter mTrendingAlbumsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_music);

        initializeDependencies();

        configureViews();
    }

    private void configureViews() {
        setSupportActionBar(mToolbar);
        mTrendingAlbumsPresenter.attachView(this);
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

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            gridSpanCount = 2;
        } else {
            gridSpanCount = 1;
        }

        StaggeredGridLayoutManager mStaggeredLayoutManager = new StaggeredGridLayoutManager(gridSpanCount, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);
        TrendingAlbumsAdapter adapter = new TrendingAlbumsAdapter(this, albums);
        adapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void openAlbumDetail(int albumId, String albumName, String coverUrl) {
        mNavigator.openAlbumDetail(this, albumId, albumName, coverUrl);
    }

    @Override
    public void showQueryTypesToSelect() {
        new BottomSheet.Builder(this)
                .sheet(R.menu.menu_query_types)
                .listener(bottomSheetAction())
                .show();
    }

    @NonNull
    private DialogInterface.OnClickListener bottomSheetAction() {
        return new DialogInterface.OnClickListener() {
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
        };
    }

    @Override
    public void changeTitle(int titleId) {
        getSupportActionBar().setTitle(titleId);
    }

    @Override
    public void onClick(AlbumViewModel album, int position) {
        mTrendingAlbumsPresenter.clickedAt(album, position);
    }
}
