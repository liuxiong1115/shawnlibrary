package com.liuxiong.library.imageloader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
//import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.RequestManager;
//import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.BitmapImageViewTarget;

import java.io.File;

/**
 *
 */

public class GlideLoader implements ILoader {

    @Override
    public void init(Context context) {
    }

    @Override
    public void loadNet(ImageView target, String url, Options options) {
        Glide.with(target.getContext()).load(url).into(target);
    }

    @Override
    public void loadResource(ImageView target, int resId, Options options) {
        Glide.with(target.getContext()).load(resId).into(target);
    }

    @Override
    public void loadAssets(ImageView target, String assetName, Options options) {
        Glide.with(target.getContext()).load("file:///android_asset/" + assetName).into(target);
    }

    @Override
    public void loadFile(ImageView target, File file, Options options) {
        Glide.with(target.getContext()).load(file).into(target);
    }

    @Override
    public void clearMemoryCache(Context context) {
        Glide.get(context).clearMemory();
    }

    @Override
    public void clearDiskCache(Context context) {
        Glide.get(context).clearDiskCache();
    }

    @Override
    public void loadCirImageView(final ImageView target, String url, Options options) {
        Glide.with(target.getContext()).asBitmap().load(url).into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(target.getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                target.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    @Override
    public void loadCirImageView(final ImageView target, File file, Options options) {
        Glide.with(target.getContext()).asBitmap().load(file).into(new BitmapImageViewTarget(target) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(target.getContext().getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                target.setImageDrawable(circularBitmapDrawable);
            }
        });


//        Glide.with(target.getContext()).asBitmap().load(file).apply(RequestOptions.centerCropTransform()
//                .dontAnimate()).into(new BitmapImageViewTarget(target) {
//            @Override
//            protected void setResource(Bitmap resource) {
//                RoundedBitmapDrawable circularBitmapDrawable =
//                        RoundedBitmapDrawableFactory.create(target.getContext().getResources(), resource);
//                circularBitmapDrawable.setCircular(true);
//                target.setImageDrawable(circularBitmapDrawable);
//            }
//        });
    }


//    private RequestManager getRequestManager(Context context) {
//        if (context instanceof Activity) {
//            return Glide.with((Activity) context);
//        }
//        return Glide.with(context);
//    }

}
