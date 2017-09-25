package com.coderschool.vinh.flicks.api;

import com.coderschool.vinh.flicks.model.NowPlaying;
import com.coderschool.vinh.flicks.model.Youtube;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieApi {
    @GET("now_playing")
    Call<NowPlaying> getNowPlaying();

    @GET("{id}/trailers")
    Call<Youtube> getTrailer(@Path("id") int id);
}
