package com.example.ledoa.dailyexsuper.caches;

import android.graphics.Bitmap;
import android.view.View;

public interface ImageLoaderUtilCallback {

    void onStarted(String imageUri, View view);

    void onFailed(String imageUri, View view, ErrorReason failReason);

    void onCompleted(String imageUri, View view, Bitmap loadedImage);

    void onCancelled(String imageUri, View view);
}