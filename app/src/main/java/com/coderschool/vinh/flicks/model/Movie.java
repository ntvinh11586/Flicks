package com.coderschool.vinh.flicks.model;

import com.coderschool.vinh.flicks.utils.Constant;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Vinh on 10/12/2016.
 */

public class Movie {

    @SerializedName("title")
    private String title;

    @SerializedName("overview  ")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("backgrop_path")
    private String backdropPath;

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
}
