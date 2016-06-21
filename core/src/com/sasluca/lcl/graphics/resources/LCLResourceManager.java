package com.sasluca.lcl.graphics.resources;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.IUpdateable;
import com.sasluca.lcl.utils.threads.IAsyncTaskObserver;

import java.util.HashMap;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLResourceManager implements IUpdateable, IDisposable
{
    private HashMap<String, Object> m_Resources;
    private AssetManager m_AssetManager;

    public LCLResourceManager()
    {
        m_Resources = new HashMap<>();
        m_AssetManager = new AssetManager();
    }

    public final LCLResourceManager addResource(String name, Object resource)
    {
        m_Resources.put(name, resource);
        return this;
    }

    //<editor-fold desc="Add Texture">
    public final LCLResourceManager addTexture(String name, Texture texture)
    {
        m_Resources.put(name, texture);
        return this;
    }

    public final LCLResourceManager addTexture(String name, String path)
    {
        m_Resources.put(name, new Texture(Gdx.files.internal(path)));
        return this;
    }

    public final LCLResourceManager addTextureLL(String name, String path)
    {
        Texture texture = new Texture(Gdx.files.internal(path));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        m_Resources.put(name, texture);
        return this;
    }

    public final LCLResourceManager addTextureNN(String name, String path)
    {
        Texture texture = new Texture(Gdx.files.internal(path));
        texture.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        m_Resources.put(name, texture);
        return this;
    }

    public final LCLResourceManager addTextureRegion(String name, TextureRegion region)
    {
        if(m_Resources.containsKey(name))
        {
            //TODO: Error
            m_Resources.put(name, region);
        }
        m_Resources.put(name, region);
        return this;
    }
    //</editor-fold>

    public void addNinepatch(String name, String path)
    {
        if(!m_Resources.containsKey(name))
        {
            TextureAtlas atlas = new TextureAtlas(Gdx.files.getFileHandle(path, Files.FileType.Internal));
            m_Resources.put(name, atlas.createPatch(name));
        }
    }

    public final IAsyncTaskObserver executeLoadAsync()
    {
        return () -> { return true; };
    }

    public <Resource> Resource getResource(String name)
    {
        if(!m_Resources.containsKey(name))
        {
            //TODO: Error
        }

        return ((Resource) m_Resources.get(name));
    }

    @Override public void update()
    {
        m_AssetManager.update();
    }

    @Override public void dispose()
    {
        for(Object texture : m_Resources.values()) if(texture instanceof Texture) ((Texture) texture).dispose();
        for(Object disposable : m_Resources.values()) if(disposable instanceof IDisposable) ((IDisposable) disposable).dispose();
    }
}
