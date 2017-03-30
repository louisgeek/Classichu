package com.classichu.classichu.basic.factory.imageloader.impls;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.classichu.classichu.basic.extend.KooImagesLoader;
import com.classichu.classichu.basic.factory.imageloader.interfaces.IImageLoader;
import com.classichu.classichu.basic.factory.imageloader.interfaces.ILoadImageCallback;
import com.classichu.classichu.basic.helper.VectorOrImageResHelper;
import com.classichu.classichu.basic.tool.ImageTool;
import com.classichu.classichu.basic.tool.ThreadTool;


/**
 * Created by louisgeek on 2016/12/28.
 */

public class SystemImageLoader implements IImageLoader {
    @Override
    public void displayImage(ImageView imageView, String url, int width, int height) {
        KooImagesLoader.getInstance().loadImage(url, imageView, width, height);
    }

    @Override
    public void displayImage(ImageView imageView, String url) {
        KooImagesLoader.getInstance().loadImage(url, imageView);
    }

    @Override
    public void displayImageHasNoPlaceholder(ImageView imageView, String url) {
        // TODO: 2016/12/28
        displayImage(imageView, url);
    }


    @Override
    public void displayImage(ImageView imageView, int imageResId) {
        //### imageView.setImageResource(imageResId);
        Drawable drawable = VectorOrImageResHelper.getDrawable(imageResId);
        imageView.setImageDrawable(drawable);
    }

    @Override
    public void loadImage(final String url, final ILoadImageCallback loadImageCallback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // TODO: 2016/12/28
                final Bitmap bitmap = ImageTool.downloadImageToBitmapWishImageSize(url, 200, 200);
                ThreadTool.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadImageCallback.onLoadImageBack(bitmap);
                    }
                });
            }
        }).start();
    }
}
