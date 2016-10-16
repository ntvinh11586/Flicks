package com.coderschool.vinh.flicks.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.activity.TrailerActivity;
import com.coderschool.vinh.flicks.model.Movie;

import java.util.List;

/**
 * Created by Vinh on 10/13/2016.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    private List<Movie> mMovies;

    private final int HIGH_RATING_MOVIE = 1;
    private final int NORMAL_RATING_MOVIE = 0;

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, -1);
        mMovies = movies;
    }

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).getVoteAverage() > 5.0) {
            return HIGH_RATING_MOVIE;
        } else {
            return NORMAL_RATING_MOVIE;
        }
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        int type = getItemViewType(position);
        Movie movie;
        switch (type) {
            case NORMAL_RATING_MOVIE:
                NormalRatingMovieViewHolder normalRatingMovieViewHolder;

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext())
                            .inflate(R.layout.item_normal_rating_movie, parent, false);
                    normalRatingMovieViewHolder = new NormalRatingMovieViewHolder();
                    normalRatingMovieViewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
                    normalRatingMovieViewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
                    normalRatingMovieViewHolder.ivCover = (ImageView) convertView.findViewById(R.id.ivCover);

                    convertView.setTag(normalRatingMovieViewHolder);
                } else {
                    normalRatingMovieViewHolder = (NormalRatingMovieViewHolder) convertView.getTag();
                }

                movie = getItem(position);

                normalRatingMovieViewHolder.tvTitle.setText(movie.getTitle());
                normalRatingMovieViewHolder.tvOverview.setText(movie.getOverview());

                Configuration configuration = getContext().getResources()
                        .getConfiguration();
                if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Glide.with(getContext())
                            .load(movie.getPosterPath())
//                            .bitmapTransform(new RoundedCornersTransformation(getContext(), 90, 0))
                            .placeholder(R.drawable.placeholder_portrait)
                            .into(normalRatingMovieViewHolder.ivCover);
                } else {
                    Glide.with(getContext())
                            .load(movie.getBackdropPath())
                            .placeholder(R.drawable.placeholder_landscape)
//                            .bitmapTransform(new RoundedCornersTransformation(getContext(), 90, 0))
                            .into(normalRatingMovieViewHolder.ivCover);
                }

                break;

            case HIGH_RATING_MOVIE:
                HighRatingMovieViewHolder highRatingMovieViewHolder;

                if (convertView == null) {
                    convertView = LayoutInflater.from(getContext())
                            .inflate(R.layout.item_high_rating_movie, parent, false);
                    highRatingMovieViewHolder = new HighRatingMovieViewHolder();
                    highRatingMovieViewHolder.ivCover = (ImageView) convertView.findViewById(R.id.ivCover);

                    convertView.setTag(highRatingMovieViewHolder);
                } else {
                    highRatingMovieViewHolder = (HighRatingMovieViewHolder) convertView.getTag();
                }

                movie = getItem(position);

                Glide.with(getContext())
                    .load(movie.getBackdropPath())
                    .placeholder(R.drawable.placeholder_landscape)
                        .into(highRatingMovieViewHolder.ivCover);

                highRatingMovieViewHolder.ivCover.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getContext(), TrailerActivity.class);
                        intent.putExtra("id", getItem(position).getId());
                        getContext().startActivity(intent);
                    }
                });

                break;
        }

        return convertView;
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }


    @Nullable
    @Override
    public Movie getItem(int position) {
        return mMovies.get(position);
    }

    private class NormalRatingMovieViewHolder {
        public TextView tvTitle;
        public TextView tvOverview;
        public ImageView ivCover;
    }

    private class HighRatingMovieViewHolder {
        public ImageView ivCover;
    }
}
