package org.github.kilarukirankumar.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

/**
 * ImageUtils
 */

public class ImageUtils {

    private ImageUtils() {

    }

    public static void setImage(Context context, ImageView imageView, String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url).into(imageView);
        }
    }

    public static void setImage(Context context, ImageView imageView, String url,
                                Drawable defaultDrawable) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url)
                    .placeholder(defaultDrawable).into(imageView);
        }
    }

    public static void setImage(Context context, Target target, String url) {
        if (!TextUtils.isEmpty(url)) {
            Picasso.with(context).load(url).into(target);
        }
    }
}
