package com.sasluca.lcl.android;

import android.content.Intent;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.sasluca.lcl.sandbox.Playground;

public class AndroidLauncher extends AndroidApplication
{
	LCLAndroidGalleryOpener opener;

	@Override protected void onCreate (Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		opener = new LCLAndroidGalleryOpener(this);

		initialize(new Playground(opener), config);
	}

	@Override protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		opener.onActivityResult(requestCode, resultCode, data, true);
	}
}
