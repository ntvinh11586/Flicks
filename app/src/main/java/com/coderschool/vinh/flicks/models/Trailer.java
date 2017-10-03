package com.coderschool.vinh.flicks.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Vinh on 10/16/2016.
 */

public class Trailer {
    @SerializedName("source")
    private String source;

    public String getSource() {
        return source;
    }
}
