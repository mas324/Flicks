package com.example.maste.flicks.adapters;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.maste.flicks.models.VideoPlay;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

class MoviePlayer extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    YouTubePlayerView playerView;
    private List<VideoPlay> playList;

    public MoviePlayer(View view, List<VideoPlay> playList) {
        this.playList = playList;
        //view.findViewById(R.id.player);
    }

    public void play() {
        playerView.initialize(playList.get(0).getSource(), this);
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        youTubePlayer.loadVideo(playList.get(0).getSource());
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Error loading video: " + youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
    }
}
