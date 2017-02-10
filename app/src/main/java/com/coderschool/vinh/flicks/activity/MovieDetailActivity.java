package com.coderschool.vinh.flicks.activity;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.databinding.ActivityMovieDetailBinding;
import com.coderschool.vinh.flicks.model.Movie;

public class MovieDetailActivity extends AppCompatActivity {
    static private final String EXTRA_ID = "id";
    static private final String EXTRA_MOVIE = "movie";

    private ActivityMovieDetailBinding binding;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        movie = (Movie) getIntent().getSerializableExtra(EXTRA_MOVIE);
        binding.setMovie(movie);
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String url) {
        if (view.getId() == R.id.ivHighRatingCover) {
            Glide.with(view.getContext())
                    .load(url)
                    .placeholder(R.drawable.placeholder_landscape)
                    .into(view);
        }
    }

    public void onCoverClick(View view) {
        Intent intent = new Intent(MovieDetailActivity.this, TrailerActivity.class);
        intent.putExtra(EXTRA_ID, movie.getId());
        startActivity(intent);
    }
}
