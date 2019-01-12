package com.example.maste.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    private String posterURL;
    private String title;
    private String overview;

    private Movie(JSONObject jsonObject) throws JSONException {
        posterURL = jsonObject.getString("poster_path");
        title = jsonObject.getString("title");
        overview = jsonObject.getString("overview");
    }

    public static List<Movie> fromArray(JSONArray jsonArray) throws JSONException {
        List<Movie> movies = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            movies.add(new Movie(jsonArray.getJSONObject(i)));
        }
        return movies;
    }

    public String getPosterURL() {
        return "https://image.tmdb.org/t/p/w500" + posterURL;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }
}
