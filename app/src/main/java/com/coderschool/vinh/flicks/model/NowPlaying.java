package com.coderschool.vinh.flicks.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NowPlaying implements Serializable {
    @SerializedName("results")
    private List<Movie> movies;

    @SerializedName("page")
    private int page;

    public List<Movie> getMovies() {
        return movies;
    }

    public int getPage() {
        return page;
    }
}
