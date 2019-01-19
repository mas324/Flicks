package com.example.maste.flicks.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VideoPlay {

    private String name;
    private String size;
    private String source;
    private String type;

    private VideoPlay(JSONObject jsonObject) throws JSONException {
        name = jsonObject.getString("name");
        size = jsonObject.getString("size");
        source = jsonObject.getString("source");
        type = jsonObject.getString("type");
    }

    public static List<VideoPlay> fromArray(JSONArray jsonArray) throws JSONException {
        List<VideoPlay> videos = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++) {
            videos.add(new VideoPlay(jsonArray.getJSONObject(i)));
        }
        return videos;
    }

    public String getSource() {
        return source;
    }
}
