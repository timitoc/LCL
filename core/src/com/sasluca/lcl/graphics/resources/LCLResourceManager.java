package com.sasluca.lcl.graphics.resources;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.IUpdatable;
import com.sasluca.lcl.abstractions.IUpdate;
import com.sasluca.lcl.utils.collections.LCLObjectMap;
import com.sasluca.lcl.utils.threads.IAsyncTaskObserver;

/**
 * Created by Sas Luca on 11-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class LCLResourceManager implements IUpdate<LCLResourceManager>, IDisposable
{
    private LCLObjectMap<String, Object> m_Resources;
    private LCLObjectMap<String, String> m_LoadingQueue;
    private AssetManager m_AssetManager;

    public LCLResourceManager()
    {
        m_Resources = new LCLObjectMap<>();
        m_AssetManager = new AssetManager();
    }

    public final LCLResourceManager addResource(String name, Object resource)
    {
        m_Resources.put(name, resource);
        return this;
    }

    //<editor-fold desc="Add Texture">
    public final LCLResourceManager addTextureToQueue(String name, String path)
    {
        m_AssetManager.load(path, Texture.class);
        m_LoadingQueue.put(path, name);
        return this;
    }

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

    public void addNinepatchFromAtlas(String name, String path)
    {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.getFileHandle(path, Files.FileType.Internal));
        m_Resources.put(name, atlas.createPatch(name));
    }

    public void addNinepatchesFromAtlas(String path, String... names)
    {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.getFileHandle(path, Files.FileType.Internal));

        for (String name : names)
        {
            if(m_Resources.containsKey(name)) return;
            m_Resources.put(name, atlas.createPatch(name));
        }
    }

    public void addTextureAtlas(String name, String path)
    {
        TextureAtlas atlas = new TextureAtlas(Gdx.files.getFileHandle(path, Files.FileType.Internal));
        m_Resources.put(name, atlas);
    }

    public <Resource> Resource getResource(String name)
    {
        if(!m_Resources.containsKey(name))
        {
            //TODO: Error
        }

        return ((Resource) m_Resources.get(name));
    }

    @Override public LCLResourceManager update()
    {
        if(m_AssetManager.update())
            for(String path : m_LoadingQueue.keys()) m_Resources.put(m_LoadingQueue.get(path), m_Resources.get(path));

        return this;
    }

    @Override public void dispose()
    {
        for(Object texture : m_Resources.values()) if(texture instanceof Texture) ((Texture) texture).dispose();
        for(Object disposable : m_Resources.values()) if(disposable instanceof IDisposable) ((IDisposable) disposable).dispose();
    }
}
