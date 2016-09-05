package com.sasluca.lcl.graphics.resources;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.sasluca.lcl.abstractions.IDisposable;
import com.sasluca.lcl.abstractions.IUpdate;
import com.sasluca.lcl.utils.collections.LCLMap;

/*
 * Copyright 2016 Sas Luca
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class LCLResourceManager implements IUpdate, IDisposable
{
    private LCLMap<String, Object> m_Resources;
    private LCLMap<String, String> m_LoadingQueue;
    private AssetManager m_AssetManager;

    public LCLResourceManager()
    {
        m_Resources = new LCLMap<>();
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

    @Override public void update()
    {
        if(m_AssetManager.update())
            for(String path : m_LoadingQueue.getKeys()) m_Resources.put(m_LoadingQueue.get(path), m_Resources.get(path));
    }

    @Override public void dispose()
    {
        m_AssetManager.dispose();
        for(Object texture : m_Resources.getValues()) if(texture instanceof Texture) ((Texture) texture).dispose();
        for(Object disposable : m_Resources.getValues()) if(disposable instanceof IDisposable) ((IDisposable) disposable).dispose();
    }
}
