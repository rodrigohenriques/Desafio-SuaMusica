package br.com.suamusica.app;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;

import javax.inject.Inject;

import br.com.suamusica.app.ui.activities.AlbumDetailActivity;

public class Navigator {

    Application mApplication;

    @Inject
    public Navigator(Application application) {
        this.mApplication = application;
    }

    public void openAlbumDetail(Activity activity, int albumId, String albumName, String coverUrl) {
        Intent intent = new Intent(activity, AlbumDetailActivity.class);
        intent.putExtra(AlbumDetailActivity.ALBUM_ID, albumId);
        intent.putExtra(AlbumDetailActivity.ALBUM_NAME, albumName);
        intent.putExtra(AlbumDetailActivity.COVER_URL, coverUrl);
        activity.startActivity(intent);
    }
}
