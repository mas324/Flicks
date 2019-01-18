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
import com.iarcuschin.simpleratingbar.SimpleRatingBar;

public class RatingHigh extends RecyclerView.ViewHolder {
    private MovieAdapter movieAdapter;
    private ImageView poster;
    private SimpleRatingBar ratingBar;

    public RatingHigh(MovieAdapter movieAdapter, View itemView) {
        super(itemView);
        this.movieAdapter = movieAdapter;
        poster = itemView.findViewById(R.id.moviePoster);
        ratingBar = itemView.findViewById(R.id.ratingBar);
    }

    public void fill(Movie movie) {
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
    }
}
