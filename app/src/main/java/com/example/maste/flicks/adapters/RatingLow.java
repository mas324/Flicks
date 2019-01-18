package com.example.maste.flicks.adapters;

import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.maste.flicks.R;
import com.example.maste.flicks.models.Movie;

public class RatingLow extends RecyclerView.ViewHolder {
    private MovieAdapter movieAdapter;
    private TextView title;
    private TextView overview;
    private ImageView poster;
    private RatingBar ratingBar;

    public RatingLow(MovieAdapter adapter, @NonNull View itemView) {
        super(itemView);
        movieAdapter = adapter;
        title = itemView.findViewById(R.id.movieTitle);
        overview = itemView.findViewById(R.id.movieSum);
        poster = itemView.findViewById(R.id.moviePoster);
        ratingBar = itemView.findViewById(R.id.ratingBar);
    }

    public void fill(Movie movie) {
        title.setText(movie.getTitle());
        overview.setText(movie.getOverview());
        ratingBar.setRating(movie.getRating().floatValue());
        final ProgressBar progressBar = itemView.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        GlideApp.with(movieAdapter.getContext()).load(movie.getPosterPath()).listener(new RequestListener<Drawable>() {
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
