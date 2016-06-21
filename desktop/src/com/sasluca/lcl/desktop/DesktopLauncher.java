package com.sasluca.lcl.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.sasluca.lcl.sandbox.Playground;

public class DesktopLauncher
{
	public static void main(String[] arg)
	{
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.resizable = true;
		config.height = 1024;
		config.width = 576;

		new LwjglApplication(new Playground(), config);
	}
}
