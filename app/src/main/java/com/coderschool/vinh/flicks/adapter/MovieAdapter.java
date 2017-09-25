package com.coderschool.vinh.flicks.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.model.Movie;

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
        if (movie != null && movie.isHighRatingMovie()) {
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
            normalRatingMovieViewHolder = (NormalRatingMovieViewHolder) convertView.getTag();
        }

        normalRatingMovieViewHolder.getBinding()
                .setMovie(getItem(position));

        return convertView;
    }

    private View getHighRatingMovieView(final int position,
                                        View convertView,
                                        @NonNull ViewGroup parent) {
        Movie movie = getItem(position);
        HighRatingMovieViewHolder highRatingMovieViewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_high_rating_movie, parent, false);
            highRatingMovieViewHolder = new HighRatingMovieViewHolder(convertView);
            convertView.setTag(highRatingMovieViewHolder);
        } else {
            highRatingMovieViewHolder = (HighRatingMovieViewHolder) convertView.getTag();
        }

        highRatingMovieViewHolder.getBinding()
                .setMovie(movie);
        highRatingMovieViewHolder.getBinding()
                .ivHighRatingCover
                .setTag(position);

        return convertView;
    }

    @BindingAdapter({"bind:imageHighUrl"})
    public static void loadHighImage(ImageView view, String url) {
        if (view.getId() == R.id.ivHighRatingCover) {
            Glide.with(view.getContext())
                    .load(url)
                    .placeholder(R.drawable.placeholder_landscape)
                    .into(view);
        }
    }

    @BindingAdapter({"bind:imageNormalUrl"})
    public static void loadNormalImage(ImageView view, String url) {
        Configuration configuration = view.getContext()
                .getResources()
                .getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Glide.with(view.getContext())
                    .load(url)
                    .placeholder(R.drawable.placeholder_portrait)
                    .into(view);
        } else {
            Glide.with(view.getContext())
                    .load(url)
                    .placeholder(R.drawable.placeholder_landscape)
                    .into(view);
        }
    }
}
