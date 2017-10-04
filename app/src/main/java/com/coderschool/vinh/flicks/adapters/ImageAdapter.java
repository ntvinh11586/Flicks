package com.coderschool.vinh.flicks.adapters;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.coderschool.vinh.flicks.R;

final public class ImageAdapter {
    private ImageAdapter() {
    }

    @BindingAdapter({"bind:imageViewLandscapeSrc"})
    public static void loadImageViewLandscapeSrc(ImageView view, String src) {
        Glide.with(view.getContext())
                .load(src)
                .placeholder(R.drawable.placeholder_landscape)
                .into(view);
    }

    @BindingAdapter({"bind:imageViewPortraitSrc"})
    public static void loadImageViewPortraitSrc(ImageView view, String src) {
        Glide.with(view.getContext())
                .load(src)
                .placeholder(R.drawable.placeholder_landscape)
                .into(view);
    }
}
