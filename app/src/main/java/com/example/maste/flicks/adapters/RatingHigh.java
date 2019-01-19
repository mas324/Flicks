package com.example.maste.flicks.adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.maste.flicks.R;
import com.example.maste.flicks.models.Movie;
import com.example.maste.flicks.models.VideoPlay;
import com.iarcuschin.simpleratingbar.SimpleRatingBar;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

class RatingHigh extends RecyclerView.ViewHolder {
    private final MovieAdapter movieAdapter;
    private final ImageView poster;
    private final SimpleRatingBar ratingBar;

    RatingHigh(MovieAdapter movieAdapter, View itemView) {
        super(itemView);
        this.movieAdapter = movieAdapter;
        poster = itemView.findViewById(R.id.moviePoster);
        ratingBar = itemView.findViewById(R.id.ratingBar);
    }

    void fill(Movie movie) {
        ratingBar.setRating(movie.getRating().floatValue());
        final ProgressBar progressBar = itemView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        GlideApp.with(movieAdapter.getContext()).load(movie.getBackdropPath()).listener(new RequestListener<Drawable>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                progressBar.setVisibility(View.GONE);
                return false;
            }

        }).into(poster);
        play(movie);
    }

    void play(Movie movie) {

        AsyncHttpClient client = new AsyncHttpClient();
        final List<VideoPlay> playList = new ArrayList<>();
        final String VID_PATH = String.format("https://api.themoviedb.org/3/movie/%s/trailers?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed", movie.getId());
        client.get(VID_PATH, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    playList.addAll(VideoPlay.fromArray(response.getJSONArray("youtube")));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

        MoviePlayer player = new MoviePlayer(itemView, playList);
        player.play();
    }
}
