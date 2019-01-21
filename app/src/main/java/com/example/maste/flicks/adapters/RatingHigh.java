package com.example.maste.flicks.adapters;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.maste.flicks.DetailedActivity;
import com.example.maste.flicks.R;
import com.example.maste.flicks.models.Movie;

import org.parceler.Parcels;

class RatingHigh extends RecyclerView.ViewHolder {
    private MovieAdapter movieAdapter;
    private ImageView poster;
    private RelativeLayout layout;

    RatingHigh(MovieAdapter movieAdapter, View itemView) {
        super(itemView);
        this.movieAdapter = movieAdapter;
        poster = itemView.findViewById(R.id.moviePoster);
        layout = itemView.findViewById(R.id.movieItem);
    }

    void fill(final Movie movie) {
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
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(movieAdapter.getContext(), DetailedActivity.class);
                intent.putExtra("Data", Parcels.wrap(movie));
                movieAdapter.getContext().startActivity(intent);
            }
        });
    }
}
