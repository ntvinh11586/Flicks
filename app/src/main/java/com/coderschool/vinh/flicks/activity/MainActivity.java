package com.coderschool.vinh.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.coderschool.vinh.flicks.MovieApi.MovieApi;
import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.adapter.MovieAdapter;
import com.coderschool.vinh.flicks.model.Movie;
import com.coderschool.vinh.flicks.model.NowPlaying;
import com.coderschool.vinh.flicks.utils.RetrofitUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView lvMovie;
    private MovieApi mMovieApi;
    private SwipeRefreshLayout swipeContainer;
    private NowPlaying nowPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMovieApi = RetrofitUtils.getMovie(getString(R.string.api_key)).create(MovieApi.class);
        lvMovie = (ListView)findViewById(R.id.lvMovie);
        lvMovie.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Movie movie = nowPlaying.getMovies().get(position);

                Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class);
                intent.putExtra("movie", movie);

                startActivity(intent);
            }
        });

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchMovies();
            }
        });
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_dark);


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
        nowPlaying = response.body();
        lvMovie.setAdapter(new MovieAdapter(this, nowPlaying.getMovies()));

        if (swipeContainer.isRefreshing())
            swipeContainer.setRefreshing(false);
    }

}
