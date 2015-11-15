package br.com.suamusica.app.entities;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Test;

import br.com.suamusica.domain.entities.Album;

import static org.junit.Assert.assertEquals;

@SmallTest
public class AlbumViewModelTest {
    @Test
    public void testFormatDownloadNumber() throws Exception {
        Album album = new Album(1, null, null, null, 350000);
        AlbumViewModel albumViewModel = new AlbumViewModel(album);
        String result = albumViewModel.getDownloads();
        assertEquals(result, "350.000 downloads");
    }
}