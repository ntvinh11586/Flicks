package com.coderschool.vinh.flicks.activities;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.databinding.ActivityMovieDetailBinding;
import com.coderschool.vinh.flicks.models.Movie;

public class MovieDetailActivity extends AppCompatActivity {
    static private final String EXTRA_ID = "id";
    static private final String EXTRA_MOVIE = "movie";

    private ActivityMovieDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        Movie movie = (Movie) getIntent()
                .getSerializableExtra(EXTRA_MOVIE);
        binding.setMovie(movie);
    }

    public void onCoverClick(View view) {
        Intent intent = new Intent(MovieDetailActivity.this, TrailerActivity.class)
                .putExtra(EXTRA_ID, binding.getMovie().getId());
        startActivity(intent);
    }
}
