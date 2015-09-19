package com.example.ledoa.dailyexsuper.caches;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;


import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.example.ledoa.dailyexsuper.MainApplication;

import java.io.File;

/**
 * Author: VinhNguyen
 */
public class ImageLoaderUtil {

    public static void display(String url, final ImageView imageView) {
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
    }

    public static void display(String url, final ImageView imageView, int targetSize, final int defaultImage) {
        AQuery aq = new AQuery(MainApplication.getContext());
        File fSave = aq.getCachedFile(url);
        if (fSave != null) {
            aq.id(imageView).image(fSave, true, targetSize, null);
        } else {
            aq.id(imageView).image(url, true, true, targetSize, defaultImage);
        }
    }

    public static void display(String url, final ImageView imageView, final int defaultImage) {
        imageView.setImageResource(defaultImage);
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
    }

    public static void display(String url, final ImageView imageView, final Bitmap defaultImage) {
        imageView.setImageBitmap(defaultImage);
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
    }

    public static void display(String url, final ImageView imageView, final SimpleImageLoaderUtilCallback callback) {
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    callback.onCompleted(url, imageView, bitmap);
                }
            }
        });
    }

    public static void display(String url, final ImageView imageView, final ImageLoaderUtilCallback callback) {
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    callback.onCompleted(url, imageView, bitmap);
                } else {
                    callback.onFailed(url, imageView, new ErrorReason());
                }
            }
        });
    }

    public static void display(String url, final ImageView imageView, final int defaultImage, final SimpleImageLoaderUtilCallback callback) {
        imageView.setImageResource(defaultImage);
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    callback.onCompleted(url, imageView, bitmap);
                }
            }
        });
    }

    public static void display(String url, final ImageView imageView, final int defaultImage, final ImageLoaderUtilCallback callback) {
        imageView.setImageResource(defaultImage);
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    callback.onCompleted(url, imageView, bitmap);
                } else {
                    callback.onFailed(url, imageView, new ErrorReason());
                }
            }
        });
    }

    public static void display(String url, final ImageView imageView, final Bitmap defaultImage, final SimpleImageLoaderUtilCallback callback) {
        imageView.setImageBitmap(defaultImage);
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    callback.onCompleted(url, imageView, bitmap);
                }
            }
        });
    }

    public static void display(String url, final ImageView imageView, final Bitmap defaultImage, final ImageLoaderUtilCallback callback) {
        imageView.setImageBitmap(defaultImage);
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
                if (bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                    callback.onCompleted(url, imageView, bitmap);
                } else {
                    callback.onFailed(url, imageView, new ErrorReason());
                }
            }
        });
    }

    public static void load(String url) {
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
            }
        });
    }

    public static void load(String url, final SimpleImageLoaderUtilCallback callback) {
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
                if (bitmap != null) {
                    callback.onCompleted(url, null, bitmap);
                }
            }
        });
    }

    public static void load(String url, final ImageLoaderUtilCallback callback) {
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.ajax(url, Bitmap.class, 0, new AjaxCallback<Bitmap>() {
            @Override
            public void callback(String url, Bitmap bitmap, AjaxStatus status) {
                if (bitmap != null) {
                    callback.onCompleted(url, null, bitmap);
                } else {
                    callback.onFailed(url, null, new ErrorReason());
                }
            }
        });
    }


    public static File getCacheFile(String url) {
        AQuery aq = new AQuery(MainApplication.getContext());
        return aq.getCachedFile(url);
    }

    public static void shouldDelayListView(int position, View convertView, ViewGroup parent, String url) {
        AQuery aq = new AQuery(MainApplication.getContext());
        if (convertView == null) {
            Log.d("convertView null", "convertView null");
        }
        aq.shouldDelay(position, convertView, parent, url);
    }

    public static void applyAdapter(int listView, ArrayAdapter adapter) {
        AQuery aq = new AQuery(MainApplication.getContext());
        aq.id(listView).adapter(adapter);
    }

}
