package br.com.suamusica.app.view.adapters;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import br.com.suamusica.app.R;
import br.com.suamusica.app.model.entities.Album;
import br.com.suamusica.app.view.activities.TrendingMusicAdapter;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TrendingMusicActivity extends AppCompatActivity {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.recycler_view_grid) RecyclerView mRecyclerView;
    private StaggeredGridLayoutManager mStaggeredLayoutManager;
    private List<Album> albums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trending_music);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mStaggeredLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredLayoutManager);

        albums = mock();

        mRecyclerView.setAdapter(new TrendingMusicAdapter(this, albums));
    }

    private List<Album> mock() {
        List<Album> mockAlbums = new ArrayList<>();


        String a = "Rodrigo Cotton";
        String b = "novo cd";
        String c = "";
        mockAlbums.add(new Album(a, b, c));

        return mockAlbums;
    }
}
