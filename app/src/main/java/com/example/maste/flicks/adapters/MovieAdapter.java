package com.example.maste.flicks.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.maste.flicks.R;
import com.example.maste.flicks.models.Movie;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HIGH = 10;
    private final List<Movie> movies;
    private final Context context;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    Context getContext() {
        return context;
    }

    @Override
    public int getItemViewType(int position) {
        if (movies.get(position).getRating() >= 5)
            return HIGH;
        else
            return 4;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (i == HIGH)
            return new RatingHigh(this, inflater.inflate(R.layout.movie_high, parent, false));
        else
            return new RatingLow(this, inflater.inflate(R.layout.movie_low, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == HIGH)
            ((RatingHigh) holder).fill(movies.get(position));
        else
            ((RatingLow) holder).fill(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}
