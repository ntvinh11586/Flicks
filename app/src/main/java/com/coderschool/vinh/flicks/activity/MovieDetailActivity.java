package com.coderschool.vinh.flicks.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

    private Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        movie = (Movie) getIntent().getSerializableExtra("movie");

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvOverview = (TextView) findViewById(R.id.tvOverview);
        ivCover = (ImageView) findViewById(R.id.ivCover);
        rbVoteAverage = (RatingBar) findViewById(R.id.rbVoteAverage);
        tvPopularity = (TextView) findViewById(R.id.tvPopularity);

        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());

        Glide.with(this)
                .load(movie.getBackdropPath())
                .placeholder(R.drawable.placeholder_landscape)
                .into(ivCover);

        ivCover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MovieDetailActivity.this, TrailerActivity.class);
                intent.putExtra("id", movie.getId());
                startActivity(intent);
            }
        });

        rbVoteAverage.setRating(movie.getVoteAverage());
        tvPopularity.setText(String.valueOf(movie.getPopularity()));


    }
}
