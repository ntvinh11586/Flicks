package com.coderschool.vinh.flicks.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.coderschool.vinh.flicks.MovieApi.MovieApi;
import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.model.NowPlaying;
import com.coderschool.vinh.flicks.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lvMovie;
    private MovieApi mMovieApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieApi = RetrofitUtils.get(getString(R.string.api_key)).create(MovieApi.class);
        lvMovie = (ListView)findViewById(R.id.lvMovie);
        mMovieApi.getNowPlaying().enqueue(new Callback<NowPlaying>() {
            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                Log.d("Response", String.valueOf(response.isSuccessful()));
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {
                Log.d("Response", t.getMessage());
            }
        });

    }
}
