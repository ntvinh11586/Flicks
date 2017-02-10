package com.coderschool.vinh.flicks.adapter;

import android.view.View;

import com.coderschool.vinh.flicks.databinding.ItemHighRatingMovieBinding;

public class HighRatingMovieVH {
    ItemHighRatingMovieBinding binding;

    HighRatingMovieVH(View view) {
        binding = ItemHighRatingMovieBinding.bind(view);
    }
}