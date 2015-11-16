package br.com.suamusica.app.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.suamusica.app.R;
import br.com.suamusica.app.entities.SongViewModel;
import butterknife.Bind;
import butterknife.ButterKnife;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder> {

    Context mContext;
    List<SongViewModel> mSongs;
    OnItemClickListener mItemClickListener;

    public SongAdapter(Context context, List<SongViewModel> songs) {
        this.mContext = context;
        this.mSongs = songs;
    }

    @Override
    public int getItemCount() {
        return mSongs.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_holder_song, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final SongViewModel songViewModel = mSongs.get(position);

        holder.songName.setText(songViewModel.getName());
        holder.songPlays.setText(songViewModel.getPlays());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemClickListener.onClick(songViewModel, position);
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textview_song_name) TextView songName;
        @Bind(R.id.textview_song_plays) TextView songPlays;

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
        void onClick(SongViewModel songViewModel, int position);
    }
}
