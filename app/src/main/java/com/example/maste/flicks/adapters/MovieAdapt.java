package com.example.maste.flicks.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maste.flicks.R;
import com.example.maste.flicks.models.Movie;

import java.util.List;

public class MovieAdapt extends RecyclerView.Adapter<MovieAdapt.ViewHolder> {

    private Context context;
    private List<Movie> movies;

    public MovieAdapt(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
        Movie movie = movies.get(i);
        holder.fill(movie);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView overview;
        ImageView poster;

        ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.movieTitle);
            overview = itemView.findViewById(R.id.movieSum);
        }

        void fill(Movie movie) {
            title.setText(movie.getTitle());
            overview.setText(movie.getOverview());
            Glide.with(context).load(movie.getPosterURL()).into(poster);
        }
    }
}
