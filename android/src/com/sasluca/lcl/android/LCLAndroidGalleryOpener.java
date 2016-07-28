package com.sasluca.lcl.android;

/**
 * Created by Sas Luca on 7/26/2016
 * Copyright (C) 2016 - LCL
 */

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.sasluca.lcl.abstractions.functional.ITask;
import com.sasluca.lcl.dialogs.IImageGalleryOpener;
import static android.app.Activity.RESULT_OK;

public class LCLAndroidGalleryOpener implements IImageGalleryOpener
{
    private static android.app.Activity Activity;
    private static final int SELECT_IMAGE_CODE = 1;

    private boolean m_IsRunning;
    private String m_CurrentImagePath;
    private ITask m_OnFinish;

    public LCLAndroidGalleryOpener(Activity activity)
    {
        this.Activity = activity;
    }

    @Override public void callGalleryOpener(String title)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        Activity.startActivityForResult(Intent.createChooser(intent, title), SELECT_IMAGE_CODE);

        m_IsRunning = true;
    }

    @Override public void callGalleryOpener(String title, ITask onFinish)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        Activity.startActivityForResult(Intent.createChooser(intent, title), SELECT_IMAGE_CODE);

        m_IsRunning = true;
        m_OnFinish = onFinish;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data, boolean log)
    {
        if(resultCode == RESULT_OK && requestCode == LCLAndroidGalleryOpener.SELECT_IMAGE_CODE)
        {
            Uri imageUri = data.getData();

            m_CurrentImagePath = getPathFromUri(imageUri);

            if(log) Gdx.app.log("AndroidGalleryOpener", "Image path is " + m_CurrentImagePath);

            m_IsRunning = false;
            Gdx.app.postRunnable(m_OnFinish::task);
        }
    }

    @Override public boolean isRunning() { return m_IsRunning; }
    @Override public String getImagePath()
    {
        return m_CurrentImagePath;
    }


    //<editor-fold desc="Description">
    public static String getPathFromUri(Uri uri)
    {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) return getPathFromUriPreKitKat(uri); else return getPathFromUriPostKitKat(uri);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String getPathFromUriPostKitKat(Uri uri)
    {
        String filePath = "";
        String wholeID = DocumentsContract.getDocumentId(uri);

        // Split at colon, use second item in the array
        String id = wholeID.split(":")[1];

        String[] column = { MediaStore.Images.Media.DATA };

        // Where id is equal to
        String sel = MediaStore.Images.Media._ID + "=?";

        Cursor cursor = Activity.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, column, sel, new String[]{ id }, null);

        assert cursor != null;
        int columnIndex = cursor.getColumnIndex(column[0]);

        if (cursor.moveToFirst()) filePath = cursor.getString(columnIndex);

        cursor.close();
        return filePath;
    }

    private static String getPathFromUriPreKitKat(Uri uri)
    {
        // just some safety built in
        if( uri == null ) return null;

        // try to retrieve the image from the media store first
        // this will only work for images selected from gallery
        String[] projection = { MediaStore.Images.Media.DATA };

        Cursor cursor = Activity.managedQuery(uri, projection, null, null, null);

        if( cursor != null )
        {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

            cursor.moveToFirst();

            return cursor.getString(column_index);
        }

        return uri.getPath();
    }
    //</editor-fold>
}

