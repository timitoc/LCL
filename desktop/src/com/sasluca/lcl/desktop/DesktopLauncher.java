package com.sasluca.lcl.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sasluca.lcl.sandbox.Playground;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = false;
		config.height = (int)(1024 / 1.1f);
		config.width = (int)(576 / 1.1f);

		new LwjglApplication(new Playground(), config);
	}
}
