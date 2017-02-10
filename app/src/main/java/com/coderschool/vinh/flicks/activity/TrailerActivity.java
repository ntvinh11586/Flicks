package com.coderschool.vinh.flicks.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.util.Log;

import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.api.MovieApi;
import com.coderschool.vinh.flicks.databinding.ActivityTrailerBinding;
import com.coderschool.vinh.flicks.model.Trailer;
import com.coderschool.vinh.flicks.model.Youtube;
import com.coderschool.vinh.flicks.utils.RetrofitUtils;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrailerActivity extends YouTubeBaseActivity
        implements YouTubePlayer.OnInitializedListener {
    static private final String EXTRA_ID = "id";

    private MovieApi mMovieApi;
    private Trailer trailer;

    private ActivityTrailerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_trailer);

        mMovieApi = RetrofitUtils.getTrailer(getString(R.string.api_key)).create(MovieApi.class);

        mMovieApi.getTrailer(getIntent().getIntExtra(EXTRA_ID, -1)).enqueue(new Callback<Youtube>() {
            @Override
            public void onResponse(Call<Youtube> call, Response<Youtube> response) {
                Youtube youtube = response.body();
                trailer = youtube.getTrailers().get(0);
                binding.youTubePlayerView.initialize(getString(R.string.api_google_key),
                        TrailerActivity.this);
            }

            @Override
            public void onFailure(Call<Youtube> call, Throwable t) {
                Log.d("err", t.getMessage());
            }
        });
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer youTubePlayer, boolean b) {
        // youTubePlayer.cueVideo(trailer.getSource());
        youTubePlayer.loadVideo(trailer.getSource());
        youTubePlayer.setFullscreen(true);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult youTubeInitializationResult) {

    }
}
