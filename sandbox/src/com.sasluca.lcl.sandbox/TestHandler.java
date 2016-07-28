package com.sasluca.lcl.sandbox;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import com.sasluca.lcl.ui.material_design.LCLMaterialDesign;
import com.sasluca.lcl.ui.material_design.image.UIImage;
import com.sasluca.lcl.ui.material_design.label.UILabel;
import com.sasluca.lcl.utils.LCLUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.HttpEntityWrapper;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static com.sasluca.lcl.sandbox.Playground.GalleryOpener;
import static com.sasluca.lcl.sandbox.Playground.State;
import static org.apache.http.HttpHeaders.USER_AGENT;

/**
 * Created by Sas Luca on 12-Jun-16.
 * Copyright (C) 2016 - LCL
 */

public class TestHandler implements IStateHandler<State>
{
    UILabel getImage;
    UILabel postImage;
    UIImage image;

    @Override public void onState(State currentState)
    {
        postImage.render();
        getImage.render();

        if(image != null) image.render();
    }

    @Override public void onChangeState(State currentState, State newState)
    {
        getImage = new UILabel("Roboto_Medium", "Get Image", LCLMaterialDesign.PrimaryColor);
        LCLUtils.center(getImage, true, true);
        getImage.subscribeToInputLayer(1);
        getImage.onTouchUp = (x, y, a, b, s) ->
        {
            GalleryOpener.callGalleryOpener("Select image", () -> {
                Texture texture = new Texture(Gdx.files.absolute(GalleryOpener.getImagePath()));
                image = new UIImage(texture);
                try {
                    String url = "http://drlikewebservice.azurewebsites.net/api/Default/PostTest";

                    HttpClient client = new DefaultHttpClient();
                    HttpPost post = new HttpPost(url);

                    // add header
                    post.setHeader("User-Agent", USER_AGENT);
                    post.setHeader("Content-Type", "application/xml");

                    //List<NameValuePair> urlParameters = new ArrayList<>();
                    //urlParameters.add(new BasicNameValuePair("test", "test"));
                    MultipartEntity entity = new MultipartEntity();
                    entity.addPart("test", new StringBody("SasLuca"));

                    //post.setEntity(entity);

                    post.setEntity(entity);
                    //post.setRequestHeader("Content-type", "application/octet-stream");

                    HttpResponse response = client.execute(post);
                    System.out.println("\nSending 'POST' request to URL : " + url);
                    System.out.println("Post parameters : " + post.getEntity());
                    System.out.println("Response Code : " +
                            response.getStatusLine().getStatusCode());

                    BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                    StringBuffer result = new StringBuffer();
                    String line = "";
                    while ((line = rd.readLine()) != null) {
                        result.append(line);
                    }

                    System.out.println(result.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        };

        postImage = new UILabel("Roboto_Medium", "Post Image", LCLMaterialDesign.PrimaryColor);
        postImage.subscribeToInputLayer(1);
        LCLUtils.center(postImage, true, true);
        postImage.setPosY(postImage.getY() - postImage.getHeight() - 50);
        postImage.onTouchUp = (x, y, a, b, s) -> {
            try {
                String url = "http://drlikewebservice.azurewebsites.net/api/Default/PostTest";

                HttpClient client = new DefaultHttpClient();
                HttpPost post = new HttpPost(url);

                // add header
                post.setHeader("User-Agent", USER_AGENT);
                post.setHeader("Content-Type", "application/xml");

                //List<NameValuePair> urlParameters = new ArrayList<>();
                //urlParameters.add(new BasicNameValuePair("test", "test"));
                MultipartEntity entity = new MultipartEntity();
                entity.addPart("test", new StringBody("SasLuca"));

                //post.setEntity(entity);

                post.setEntity(new StringEntity("test"));
                //post.setHeader("Content-type", "application/octet-stream");

                HttpResponse response = client.execute(post);
                System.out.println("\nSending 'POST' request to URL : " + url);
                System.out.println("Post parameters : " + post.getEntity());
                System.out.println("Response Code : " +
                        response.getStatusLine().getStatusCode());

                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = rd.readLine()) != null) {
                    result.append(line);
                }

                System.out.println(result.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }
}
