package com.example.maste.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Movie {

    protected String posterURL;
    protected String title;
    protected String overview;

    public Movie(JSONObject jsonObject) throws JSONException {
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
}
