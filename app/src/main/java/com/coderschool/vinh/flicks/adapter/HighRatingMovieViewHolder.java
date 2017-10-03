package com.coderschool.vinh.flicks.adapter;

import android.view.View;

import com.coderschool.vinh.flicks.databinding.ItemHighRatingMovieBinding;


public class HighRatingMovieViewHolder {
    private ItemHighRatingMovieBinding binding;

    HighRatingMovieViewHolder(View view) {
        binding = ItemHighRatingMovieBinding.bind(view);
    }

    public ItemHighRatingMovieBinding getBinding() {
        return binding;
    }
}