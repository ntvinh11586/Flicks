package com.coderschool.vinh.flicks.adapters;

import android.view.View;

import com.coderschool.vinh.flicks.databinding.ItemNormalRatingMovieBinding;

public class NormalRatingMovieViewHolder {
    private ItemNormalRatingMovieBinding binding;

    NormalRatingMovieViewHolder(View view) {
        binding = ItemNormalRatingMovieBinding.bind(view);
    }

    public ItemNormalRatingMovieBinding getBinding() {
        return binding;
    }
}