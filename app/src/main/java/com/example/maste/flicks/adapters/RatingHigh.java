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

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

class RatingHigh extends RecyclerView.ViewHolder {
    private MovieAdapter movieAdapter;
    protected @BindView(R.id.moviePoster)
    ImageView poster;
    protected @BindView(R.id.movieItem)
    RelativeLayout layout;

    RatingHigh(MovieAdapter adapter, View itemView) {
        super(itemView);
        movieAdapter = adapter;
        ButterKnife.bind(this, itemView);
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

        }).transform(new RoundedCornersTransformation(10, 20)).into(poster);
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
