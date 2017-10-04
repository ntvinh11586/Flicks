package com.coderschool.vinh.flicks.models;

import com.google.gson.annotations.SerializedName;

public class Trailer {
    @SerializedName("source")
    private String source;

    public String getSource() {
        return source;
    }
}
