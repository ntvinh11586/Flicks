package com.coderschool.vinh.flicks.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Vinh on 10/12/2016.
 */

public class NowPlaying {

    @SerializedName("results")
    private List<Movie> movies;

    public List<Movie> getMovides() {
        return movies;
    }
}
