package com.coderschool.vinh.flicks.model;

import com.coderschool.vinh.flicks.utils.Constant;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Vinh on 10/12/2016.
 */

public class Movie implements Serializable {
    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("vote_average")
    private float voteAverage;

    @SerializedName("popularity")
    private float popularity;

    @SerializedName("id")
    private int id;

    public int getId() {
        return id;
    }

    public float getVoteAverage() {
        return voteAverage;
    }

    public float getPopularity() {
        return popularity;
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return Constant.STATIC_BASE_URL + posterPath;
    }

    public String getBackdropPath() {
        return Constant.STATIC_BASE_URL + backdropPath;
    }

    public boolean isHighRatingMovie() {
        return getVoteAverage() > 5.0;
    }
}
