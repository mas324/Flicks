package com.example.maste.flicks;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.maste.flicks.adapters.GlideApp;
import com.example.maste.flicks.models.Movie;
import com.example.maste.flicks.models.VideoPlay;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class DetailedActivity extends YouTubeBaseActivity {

    private Movie movie;
    private YouTubePlayerView playerView;
    private List<String> videoPlayList;
    private Context context;

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detailed);
        movie = Parcels.unwrap(getIntent().getParcelableExtra("Data"));

        context = this;

        TextView title = findViewById(R.id.movieTitle);
        title.setText(movie.getTitle());

        playerView = findViewById(R.id.movieTrailer);

        videoPlayList = new ArrayList<>();
        final String TRAILER_URL = String.format("https://api.themoviedb.org/3/movie/%s/trailers?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", movie.getId());
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(TRAILER_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    List<VideoPlay> list = VideoPlay.fromArray(response.getJSONArray("youtube"));
                    for (VideoPlay item : list
                    ) {
                        Log.d("MoviePlaylistAdd", item.getSource());
                        videoPlayList.add(item.getSource());
                    }

                    playerView.initialize("AIzaSyC6Odsa6VEkW_mgrT-fdBSEend-D2e6otI", new YouTubePlayer.OnInitializedListener() {
                        @Override
                        public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                            Log.d("MoviePlay", "Successfully initialized player");
                            if (!videoPlayList.isEmpty()) {
                                Log.d("MoviePlaylist", "Playlist has stuff");
                                if (movie.getRating() >= 5)
                                    youTubePlayer.loadVideos(videoPlayList);
                                else
                                    youTubePlayer.cueVideos(videoPlayList);
                            } else {
                                Log.e("MoviePlaylist", "Playlist is empty");
                                fallback();
                            }
                        }

                        @Override
                        public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                            Log.e("MoviePlayError", youTubeInitializationResult.toString());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    fallback();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.e("MoviePlayError", "Couldn't contact server. Response: " + responseString);
                fallback();
            }
        });

        SimpleRatingBar ratingBar = findViewById(R.id.movieRating);
        ratingBar.setRating(movie.getRating().floatValue());

        TextView overview = findViewById(R.id.movieSum);
        overview.setText(movie.getOverview());
    }

    private void fallback() {
        playerView.setVisibility(View.INVISIBLE);
        ImageView defaultImage = findViewById(R.id.trailerDefault);
        defaultImage.setVisibility(View.VISIBLE);
        final ProgressBar bar = findViewById(R.id.progressBar);
        bar.setVisibility(View.VISIBLE);
        GlideApp.with(context).load(movie.getBackdropPath()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                bar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                bar.setVisibility(View.GONE);
                return false;
            }
        }).into(defaultImage);
        defaultImage.setVisibility(View.VISIBLE);
    }
}
