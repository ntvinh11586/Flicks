package com.coderschool.vinh.flicks.adapter;

import android.content.Context;
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
import com.coderschool.vinh.flicks.model.Movie;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

/**
 * Created by Vinh on 10/13/2016.
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    private List<Movie> mMovies;

    public MovieAdapter(Context context, List<Movie> movies) {
        super(context, -1);
        mMovies = movies;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);
            viewHolder.ivCover = (ImageView) convertView.findViewById(R.id.ivCover);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Movie movie = getItem(position);

        // Fill the data
        viewHolder.tvTitle.setText(movie.getTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        // Get configuration
        Configuration configuration = getContext().getResources()
                .getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Glide.with(getContext())
                    .load(movie.getPosterPath())
                    .bitmapTransform(new RoundedCornersTransformation(getContext(), 90, 0))
                    .placeholder(R.drawable.placeholder_portrait)
                    .into(viewHolder.ivCover);
        } else {
            Glide.with(getContext())
                    .load(movie.getBackdropPath())
                    .placeholder(R.drawable.placeholder_landscape)
                    .bitmapTransform(new RoundedCornersTransformation(getContext(), 90, 0))
                    .into(viewHolder.ivCover);
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

    private class ViewHolder {
        public TextView tvTitle;
        public TextView tvOverview;
        public ImageView ivCover;
    }
}
