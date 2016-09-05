package com.sasluca.lcl.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;

/**
 * Created by Sas Luca on 8/7/2016.
 * Copyright (C) 2016 - LCL
 */

public class LCLAndroidUtils
{
    private LCLAndroidUtils() { }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static String getPathFromUriPostKitKat(Uri uri, Activity activity)
    {
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = activity.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, new String[]{ id }, null);

        assert cursor != null;
        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) filePath = cursor.getString(columnIndex);

        cursor.close();
        return filePath;
    }

    public static String getPathFromUriPreKitKat(Uri uri, Activity activity)
    {
        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };

        Cursor cursor = activity.managedQuery(uri, projection, null, null, null);

        if(cursor != null)
        {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            cursor.moveToFirst();

            return cursor.getString(column_index);
        }

        return uri.getPath();
    }

    public static String getPathFromUri(Uri uri, Activity activity)
    {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return LCLAndroidUtils.getPathFromUriPreKitKat(uri, activity); else return LCLAndroidUtils.getPathFromUriPostKitKat(uri, activity);
    }
}
