package com.coderschool.vinh.flicks.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.BindingAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.coderschool.vinh.flicks.R;
import com.coderschool.vinh.flicks.activity.TrailerActivity;
import com.coderschool.vinh.flicks.model.Movie;

import java.util.List;

/**
 * Created by Vinh on 10/13/2016.
 */

public class MovieAdapter extends ArrayAdapter<Movie>
        implements View.OnClickListener {
    static private final String EXTRA_ID = "id";
    private final int HIGH_RATING_MOVIE = 1;
    private final int NORMAL_RATING_MOVIE = 0;

    private List<Movie> mMovies;

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
        return getItemViewType(position) == NORMAL_RATING_MOVIE
                ? getNormalRatingMovieView(position, convertView, parent)
                : getHighRatingMovieView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return mMovies.size();
    }

    private View getNormalRatingMovieView(final int position, View convertView, @NonNull ViewGroup parent) {
        NormalRatingMovieVH normalRatingMovieVH;
        Movie movie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_normal_rating_movie, parent, false);
            normalRatingMovieVH = new NormalRatingMovieVH(convertView);
            convertView.setTag(normalRatingMovieVH);
        } else {
            normalRatingMovieVH = (NormalRatingMovieVH) convertView.getTag();
        }

        normalRatingMovieVH.binding.setMovie(movie);

        return convertView;
    }

    private View getHighRatingMovieView(final int position, View convertView, @NonNull ViewGroup parent) {
        Movie movie = getItem(position);
        HighRatingMovieVH highRatingMovieVH;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_high_rating_movie, parent, false);
            highRatingMovieVH = new HighRatingMovieVH(convertView);
            convertView.setTag(highRatingMovieVH);
        } else {
            highRatingMovieVH = (HighRatingMovieVH) convertView.getTag();
        }

        highRatingMovieVH.binding.setMovie(movie);
        highRatingMovieVH.binding.ivHighRatingCover.setTag(position);
        highRatingMovieVH.binding.ivHighRatingCover.setOnClickListener(this);

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
        Configuration configuration = view.getContext().getResources()
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

    @Nullable
    @Override
    public Movie getItem(int position) {
        return mMovies.get(position);
    }

    @Override
    public void onClick(View v) {
        int position = (int) v.getTag();
        Intent intent = new Intent(getContext(), TrailerActivity.class);
        intent.putExtra(EXTRA_ID, getItem(position).getId());
        getContext().startActivity(intent);
    }
}
