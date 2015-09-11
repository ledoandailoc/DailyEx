package com.example.ledoa.dailyexsuper.util;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.webkit.MimeTypeMap;

import java.util.ArrayList;
import java.util.List;

public class CommonUtils {

    /**
     * List all phone number in contact
     *
     * @param context
     * @return
     */
    public static List<String> getListPhoneInContact(Context context) {
        List<String> listPhoneNumber = new ArrayList<>();

        ContentResolver contentResolver = context.getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.Data.CONTENT_URI, null, null, null, ContactsContract.Data.RAW_CONTACT_ID + " ASC");

        long rawID = 0;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    long id = cursor.getLong(cursor.getColumnIndex(ContactsContract.Data.RAW_CONTACT_ID));
                    if (rawID != id) {
                        rawID = id;
                    }
                    String mimeType = cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE));
                    switch (mimeType) {
                        case ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE:
                            int typePhone = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.TYPE));
                            String phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                            if (typePhone == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE) {
                                listPhoneNumber.add(phoneNumber);
                            }
                            break;
                    }
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return listPhoneNumber;
    }

    public static List<String> getListPhoneInDifferent(List<String> listSource, List<String> listCompare) {
        List<String> listPhoneNumber = new ArrayList<>();

        if (listCompare.size() > 0) {
            if (listSource.size() > 0) {
                for (String phoneNumber : listSource) {
                    if (!listCompare.contains(phoneNumber)) {
                        listPhoneNumber.add(phoneNumber);
                    }
                }
            }
        } else {
            listPhoneNumber.addAll(listSource);
        }

        return listPhoneNumber;
    }

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static Bitmap getHexagonShape(Bitmap scaleBitmapImage) {
        int targetWidth = 600;
        int targetHeight = 600;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight, Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);

        Path path = new Path();
        float stdW = 300;
        float stdH = 300;
        float w3 = stdW / 2;
        float h2 = stdH / 2;
        path.moveTo(0, (float) (h2 * Math.sqrt(3) / 2));
        path.rLineTo(w3 / 2, -(float) (h2 * Math.sqrt(3) / 2));
        path.rLineTo(w3, 0);
        path.rLineTo(w3 / 2, (float) (h2 * Math.sqrt(3) / 2));
        path.rLineTo(-w3 / 2, (float) (h2 * Math.sqrt(3) / 2));
        path.rLineTo(-w3, 0);
        path.rLineTo(-w3 / 2, -(float) (h2 * Math.sqrt(3) / 2));

        canvas.clipPath(path);
        canvas.drawBitmap(scaleBitmapImage,
                new Rect(0, 0, scaleBitmapImage.getWidth(),
                        scaleBitmapImage.getHeight()),
                new Rect(0, 0, targetWidth,
                        targetHeight), null);
        return targetBitmap;
    }

    public static String getMimeType(String url) {
        String type = null;
        String extension = MimeTypeMap.getFileExtensionFromUrl(url);
        if (extension != null) {
            type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension);
        }
        return type;
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        return ni != null && cm.getActiveNetworkInfo().isConnected();
    }

}
