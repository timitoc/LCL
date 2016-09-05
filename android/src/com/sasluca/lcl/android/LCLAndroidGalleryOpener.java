package com.sasluca.lcl.android;

/**
 * Created by Sas Luca on 7/26/2016
 * Copyright (C) 2016 - LCL
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import com.badlogic.gdx.Gdx;
import com.sasluca.lcl.abstractions.functional.ITask;
import com.sasluca.lcl.dialogs.IImageGalleryOpener;
import static android.app.Activity.RESULT_OK;

public class LCLAndroidGalleryOpener implements IImageGalleryOpener
{
    public static final int SELECT_IMAGE_CODE = 1;

    private ITask m_OnFinish;
    private Activity m_Activity;
    private boolean m_IsRunning;
    private String m_CurrentImagePath;

    public LCLAndroidGalleryOpener(Activity activity)
    {
        this.m_Activity = activity;
    }

    @Override public void callGalleryOpener(String title)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        m_Activity.startActivityForResult(Intent.createChooser(intent, title), SELECT_IMAGE_CODE);

        m_IsRunning = true;
    }

    @Override public void callGalleryOpener(String title, ITask onFinish)
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        m_Activity.startActivityForResult(Intent.createChooser(intent, title), SELECT_IMAGE_CODE);

        m_IsRunning = true;
        m_OnFinish = onFinish;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK && requestCode == LCLAndroidGalleryOpener.SELECT_IMAGE_CODE)
        {
            Uri imageUri = data.getData();

            m_CurrentImagePath = LCLAndroidUtils.getPathFromUri(imageUri, m_Activity);

            m_IsRunning = false;
            Gdx.app.postRunnable(m_OnFinish::task);
        }
    }

    @Override public boolean isRunning() { return m_IsRunning; }
    @Override public String getImagePath()
    {
        return m_CurrentImagePath;
    }
}

