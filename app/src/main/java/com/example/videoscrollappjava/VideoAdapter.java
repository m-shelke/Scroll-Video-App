package com.example.videoscrollappjava;

import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    ArrayList<Video> videoArrayList;

    public VideoAdapter(ArrayList<Video> videoArrayList) {
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public VideoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoAdapter.ViewHolder holder, int position) {

        holder.setData(videoArrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return videoArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

       public VideoView videoView;
        TextView title,description;
        ProgressBar progressBar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            videoView = itemView.findViewById(R.id.item_videoView);
            title = itemView.findViewById(R.id.item_title);
            description = itemView.findViewById(R.id.item_desc);
            progressBar = itemView.findViewById(R.id.item_progressBar);
        }

        void setData(Video video){

            videoView.setVideoPath(video.getVideoUrl());
            title.setText(video.getTitle());
            description.setText(video.getDesc());

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
