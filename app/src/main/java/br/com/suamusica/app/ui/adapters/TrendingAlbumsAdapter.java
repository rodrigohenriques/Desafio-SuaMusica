package br.com.suamusica.app.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import br.com.suamusica.app.R;
import br.com.suamusica.domain.entities.Album;
import butterknife.Bind;
import butterknife.ButterKnife;

public class TrendingAlbumsAdapter extends RecyclerView.Adapter<TrendingAlbumsAdapter.ViewHolder> {

    Context mContext;
    List<Album> albums;
    OnItemClickListener mItemClickListener;

    public TrendingAlbumsAdapter(Context context, List<Album> albums) {
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

        Glide.with(mContext)
                .load(album.coverUrl)
                .fitCenter()
                .crossFade()
                .placeholder(R.drawable.placeholder)
                .into(holder.albumCoverImage);

        holder.albumName.setText(album.name);
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onClick(album, position);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.imageview_album_cover) ImageView albumCoverImage;
        @Bind(R.id.textview_album_name) TextView albumName;

        View rootView;

        public ViewHolder(View rootView) {
            super(rootView);
            ButterKnife.bind(this, rootView);

            this.rootView = rootView;
        }
    }

    public void setOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(Album album, int position);
    }
}
