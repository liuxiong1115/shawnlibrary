package com.liuxiong.library.imageloader;

import android.content.Context;
import android.widget.ImageView;

//import com.bumptech.glide.request.RequestOptions;
import com.liuxiong.library.BaseConf;

import java.io.File;

/**
 *
 */

public interface ILoader {

    void init(Context context);

    void loadNet(ImageView target, String url, Options options);

    void loadResource(ImageView target, int resId, Options options);

    void loadAssets(ImageView target, String assetName, Options options);

    void loadFile(ImageView target, File file, Options options);

    void clearMemoryCache(Context context);

    void clearDiskCache(Context context);

    /**
     * 加载圆形图片
     * @param target
     * @param url
     * @param options
     */
    void loadCirImageView(ImageView target, String url, Options options);

    void loadCirImageView(ImageView target, File file, Options options);

    class Options {

        public int loadingResId = RES_NONE;        //加载中的资源id
        public int loadErrorResId = RES_NONE;      //加载失败的资源id

        public static final int RES_NONE = -1;

        public static Options defaultOptions() {
            return new Options(BaseConf.IL_LOADING_RES, BaseConf.IL_ERROR_RES);
        }

        public Options(int loadingResId, int loadErrorResId) {
            this.loadingResId = loadingResId;
            this.loadErrorResId = loadErrorResId;
        }

    }

}
