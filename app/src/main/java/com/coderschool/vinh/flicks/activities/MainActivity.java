package com.coderschool.vinh.flicks.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.adapters.MovieAdapter;
import com.coderschool.vinh.flicks.api.MovieApi;
import com.coderschool.vinh.flicks.databinding.ActivityMainBinding;
import com.coderschool.vinh.flicks.models.Movie;
import com.coderschool.vinh.flicks.models.NowPlaying;
import com.coderschool.vinh.flicks.utils.RetrofitUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener,
        SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = "MainActivity";
    static private final String EXTRA_TRAILER = "id";
    static private final String EXTRA_MOVIE = "movie";

    private MovieApi movieApi;
    private MovieAdapter movieAdapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.lvMovie.setOnItemClickListener(this);
        binding.swipe.setColorSchemeResources(android.R.color.holo_blue_dark);
        binding.swipe.setOnRefreshListener(this);
        binding.swipe.setRefreshing(true);

        movieAdapter = new MovieAdapter(this, new ArrayList<Movie>());
        binding.lvMovie.setAdapter(movieAdapter);

        movieApi = RetrofitUtils
                .getMovie(getString(R.string.api_key))
                .create(MovieApi.class);

        fetchMovies();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Movie movie = movieAdapter.getItem(position);

        if (movie == null) {
            return;
        }

        if (movie.isHighRatingMovie()) {
            Intent intent = new Intent(MainActivity.this, TrailerActivity.class)
                    .putExtra(EXTRA_TRAILER, movie.getId());
            startActivity(intent);
        } else {
            Intent intent = new Intent(MainActivity.this, MovieDetailActivity.class)
                    .putExtra(EXTRA_MOVIE, movie);
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        fetchMovies();
    }

    private void fetchMovies() {
        movieApi.getNowPlaying()
                .enqueue(new Callback<NowPlaying>() {
                    @Override
                    public void onResponse(Call<NowPlaying> call,
                                           Response<NowPlaying> response) {
                        NowPlaying nowPlaying = response.body();
                        List<Movie> movies = nowPlaying.getMovies();

                        movieAdapter.clear();
                        movieAdapter.addAll(movies);

                        if (binding.swipe.isRefreshing()) {
                            binding.swipe.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onFailure(Call<NowPlaying> call, Throwable t) {
                        Log.e(TAG, Arrays.toString(t.getStackTrace()));
                    }
                });
    }
}
