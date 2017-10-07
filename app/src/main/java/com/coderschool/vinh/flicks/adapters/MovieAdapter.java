package com.coderschool.vinh.flicks.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.models.Movie;

import java.util.List;

public class MovieAdapter extends ArrayAdapter<Movie> {
    private final int HIGH_RATING_MOVIE = 1;
    private final int NORMAL_RATING_MOVIE = 0;

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, -1, movies);
    }

    @Override
    public int getItemViewType(int position) {
        Movie movie = getItem(position);
        return (movie != null && movie.isHighRatingMovie())
                ? HIGH_RATING_MOVIE
                : NORMAL_RATING_MOVIE;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @NonNull
    @Override
    public View getView(final int position,
                        View convertView,
                        @NonNull ViewGroup parent) {
        return getItemViewType(position) == NORMAL_RATING_MOVIE
                ? getNormalRatingMovieView(position, convertView, parent)
                : getHighRatingMovieView(position, convertView, parent);
    }

    private View getNormalRatingMovieView(final int position,
                                          View convertView,
                                          @NonNull ViewGroup parent) {
        NormalRatingMovieViewHolder normalRatingMovieViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_normal_rating_movie, parent, false);
            normalRatingMovieViewHolder = new NormalRatingMovieViewHolder(convertView);
            convertView.setTag(normalRatingMovieViewHolder);
        } else {
            normalRatingMovieViewHolder =
                    (NormalRatingMovieViewHolder) convertView.getTag();
        }

        normalRatingMovieViewHolder.getBinding()
                .setMovie(getItem(position));

        return convertView;
    }

    private View getHighRatingMovieView(final int position,
                                        View convertView,
                                        @NonNull ViewGroup parent) {
        HighRatingMovieViewHolder highRatingMovieViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_high_rating_movie, parent, false);
            highRatingMovieViewHolder = new HighRatingMovieViewHolder(convertView);
            convertView.setTag(highRatingMovieViewHolder);
        } else {
            highRatingMovieViewHolder =
                    (HighRatingMovieViewHolder) convertView.getTag();
        }

        highRatingMovieViewHolder.getBinding()
                .setMovie(getItem(position));

        return convertView;
    }
}
