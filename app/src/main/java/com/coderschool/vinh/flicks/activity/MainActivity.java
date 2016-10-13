package com.coderschool.vinh.flicks.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.coderschool.vinh.flicks.MovieApi.MovieApi;
import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.adapter.MovieAdapter;
import com.coderschool.vinh.flicks.model.NowPlaying;
import com.coderschool.vinh.flicks.utils.Constant;
import com.coderschool.vinh.flicks.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {

    private ListView lvMovie;
    private ProgressBar pbLoading;
    private MovieApi mMovieApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieApi = RetrofitUtils.get(Constant.API_KEY).create(MovieApi.class);
        lvMovie = (ListView)findViewById(R.id.lvMovie);
        pbLoading = (ProgressBar) findViewById(R.id.pdLoading);
        fetchMovies();
    }

    private void fetchMovies() {
        mMovieApi.getNowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                handleResponse(response);
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                Log.d("Response", t.getMessage());
            }
        });
    }

    private void handleResponse(Response<NowPlaying> response) {
        lvMovie.setAdapter(new MovieAdapter(this, response.body().getMovies()));
        pbLoading.setVisibility(GONE);
    }

}
