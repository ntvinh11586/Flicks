package com.coderschool.vinh.flicks.adapter;

import android.view.View;

import com.coderschool.vinh.flicks.databinding.ItemNormalRatingMovieBinding;

public class NormalRatingMovieVH {
    ItemNormalRatingMovieBinding binding;

    NormalRatingMovieVH(View view) {
        binding = ItemNormalRatingMovieBinding.bind(view);
    }
}