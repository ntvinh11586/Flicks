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

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailActivity extends AppCompatActivity {
    static private final String EXTRA_ID = "id";
    static private final String EXTRA_MOVIE = "movie";

    @BindView(R.id.tvTitle)
    TextView tvTitle;
    @BindView(R.id.tvOverview)
    TextView tvOverview;
    @BindView(R.id.ivCover)
    ImageView ivCover;
    @BindView(R.id.rbVoteAverage)
    RatingBar rbVoteAverage;
    @BindView(R.id.tvPopularity)
    TextView tvPopularity;

    private Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);

        movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);

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
                intent.putExtra(EXTRA_ID, movie.getId());
                startActivity(intent);
            }
        });

        rbVoteAverage.setRating(movie.getVoteAverage());
        tvPopularity.setText(String.valueOf(movie.getPopularity()));
    }
}
