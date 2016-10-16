package com.coderschool.vinh.flicks.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private TextView tvTitle;
    private TextView tvOverview;
    private ImageView ivCover;
    private RatingBar rbVoteAverage;
    private TextView tvPopularity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        ivCover = (ImageView) findViewById(R.id.ivCoverBig);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        tvPopularity = (TextView) findViewById(R.id.tvPopularity);

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Glide.with(this)
                    .load(movie.getPosterPath())
                    .placeholder(R.drawable.placeholder_portrait)
                    .into(ivCover);
        } else {
            Glide.with(this)
                    .load(movie.getBackdropPath())
                    .placeholder(R.drawable.placeholder_landscape)
                    .into(ivCover);
        }

        rbVoteAverage.setRating(movie.getVoteAverage());
        tvPopularity.setText("Popularity: " + String.valueOf(movie.getPopularity()));


    }
}
