package com.example.videoscrollappjava;

import android.media.MediaPlayer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class FirebaseVideoAdapter extends FirebaseRecyclerAdapter<FirebaseVideo,FirebaseVideoAdapter.FirebaseViewHolder> {


    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public FirebaseVideoAdapter(@NonNull FirebaseRecyclerOptions<FirebaseVideo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull FirebaseViewHolder holder, int position, @NonNull FirebaseVideo model) {

        holder.setData(model);
    }

    @NonNull
    @Override
    public FirebaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        return new FirebaseViewHolder(view);
    }

    public static class FirebaseViewHolder extends RecyclerView.ViewHolder{

        public VideoView videoView;
        TextView title,description;
        ProgressBar progressBar;

        public FirebaseViewHolder(@NonNull View itemView) {
            super(itemView);

            videoView = itemView.findViewById(R.id.item_videoView);
            title = itemView.findViewById(R.id.item_title);
            description = itemView.findViewById(R.id.item_desc);
            progressBar = itemView.findViewById(R.id.item_progressBar);
        }

        void setData(FirebaseVideo video){

            videoView.setVideoPath(video.getVideouri());
            title.setText(video.getName());
            description.setText(video.getDescription());

            videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    progressBar.setVisibility(View.GONE);
                }
            });

            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mp.setLooping(true);
                }
            });
            videoView.setKeepScreenOn(true);
        }
    }
}
