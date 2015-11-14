package br.com.suamusica.app.view.activities;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.suamusica.app.R;
import br.com.suamusica.app.model.entities.Album;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TrendingMusicAdapter extends RecyclerView.Adapter<TrendingMusicAdapter.ViewHolder> {

    Context mContext;
    List<Album> albums;

    public TrendingMusicAdapter(Context context, List<Album> albums) {
        this.mContext = context;
        this.albums = albums;
    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_item_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Album album = albums.get(position);
        Picasso.with(mContext).load(album.coverUrl).into(holder.albumCoverImage);
        holder.albumAuthor.setText(album.author);
        holder.albumName.setText(album.name);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview_album_cover) ImageView albumCoverImage;
        @Bind(R.id.textview_album_author) TextView albumAuthor;
        @Bind(R.id.textview_album_name) TextView albumName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}