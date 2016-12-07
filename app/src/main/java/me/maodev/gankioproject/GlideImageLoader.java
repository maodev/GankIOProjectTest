package me.maodev.gankioproject;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by maoyu on 16/12/6.
 */

public class GlideImageLoader {
    public static void LoadImage(ImageView imageView, String imgUrl) {
        Glide.with(GenerApplication.getInstance())
                .load(imgUrl + "?imageView2/0/w/400")
                .placeholder(R.color.tab_title_text_selected)
                .error(R.mipmap.ic_launcher)
                .crossFade()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);

    }
}
