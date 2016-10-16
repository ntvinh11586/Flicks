package com.coderschool.vinh.flicks.activity;

import android.os.Bundle;

import com.coderschool.vinh.flicks.MovieApi.MovieApi;
import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.model.Trailer;
import com.coderschool.vinh.flicks.model.Youtube;
import com.coderschool.vinh.flicks.utils.RetrofitUtils;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailerActivity extends YouTubeBaseActivity {

    private MovieApi mMovieApi;
    private YouTubePlayerView youTubePlayerView;
    private int id;
    private Youtube youtube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trailer);

        youTubePlayerView = (YouTubePlayerView) findViewById(R.id.player);

        id = getIntent().getIntExtra("id", -1);

        mMovieApi = RetrofitUtils.getTrailer(getString(R.string.api_key), id).create(MovieApi.class);

        mMovieApi.getTrailter().enqueue(new Callback<Youtube>() {
            @Override
            public void onResponse(Call<Youtube> call, Response<Youtube> response) {
                youtube = response.body();
                final Trailer trailer = youtube.getTrailers().get(0);

                youTubePlayerView.initialize("AIzaSyA_pv9AD36HrVqwOPZwFOelJJ-6AdgFSxk",
                        new YouTubePlayer.OnInitializedListener() {
                            @Override
                            public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                                YouTubePlayer youTubePlayer, boolean b) {

                                youTubePlayer.loadVideo(trailer.getSource());
                            }

                            @Override
                            public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                                YouTubeInitializationResult youTubeInitializationResult) {

                            }
                        });

            }

            @Override
            public void onFailure(Call<Youtube> call, Throwable t) {

            }
        });
    }
}
