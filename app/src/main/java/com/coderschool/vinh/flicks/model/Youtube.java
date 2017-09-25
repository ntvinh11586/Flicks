package com.coderschool.vinh.flicks.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Youtube {
    @SerializedName("youtube")
    private List<Trailer> trailers;

    public List<Trailer> getTrailers() {
        return trailers;
    }
}
