package com.sasluca.lcl.sandbox;

import com.sasluca.lcl.applogic.managers.statemanager.IStateHandler;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sas Luca on 8/12/2016.
 */

public class TestHandler4 implements IStateHandler<Playground.State>
{
    public static final String URL = "http://localhost:63776/api/Default/PostTest/";

    @Override public void onState(Playground.State currentState)
    {

    }

    @Override public void onChangeState(Playground.State currentState, Playground.State newState) {
        test3();
    }

    public void test()
    {
        try
        {
            HttpClient client = HttpClients.createDefault();

            //File file = new File(pathName);
            //byte[] data = FileUtils.readFileToByteArray(file);
            MultipartEntity form = new MultipartEntity();

            //ContentBody cd = new ByteArrayBody(data, file.getName());
            form.addPart("test", new StringBody("test1"));

            HttpEntityEnclosingRequestBase post = new HttpPost(URL);
            //post.setHeader("Content-Type", "x-www-form-urlencoded");
            post.setEntity(form);
            client.execute(post);
        }
        catch (Exception e) { e.printStackTrace(); }
    }

    /**
     * The working piece of shit
     */
    public void test3()
    {
        HttpClient httpClient = HttpClients.createDefault();

        StringBuilder sb = new StringBuilder();
        InputStreamReader in;

        try
        {
            HttpPost request = new HttpPost(URL);
            request.setHeader("content-Type", "application/json");
            StringEntity params =new StringEntity("{\"name\":\"test1\"}");
            //request.addHeader("content-type", "application/x-www-form-urlencoded");
            request.setEntity(params);
            HttpResponse response = httpClient.execute(request);

            // handle response here...
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                InputStream instream = entity.getContent();
                try
                {
                    // do something useful
                    in = new InputStreamReader(instream, Charset.defaultCharset());
                    BufferedReader bufferedReader = new BufferedReader(in);
                    if (bufferedReader != null)
                    {
                        int cp;
                        while ((cp = bufferedReader.read()) != -1) sb.append((char) cp);

                        bufferedReader.close();
                    }
                } finally { instream.close(); }
            }

            System.out.println(sb.toString());

        }catch (Exception ex) {
            // handle exception here
        } finally {
            httpClient.getConnectionManager().shutdown(); //Deprecated
        }
    }

    public void test2()
    {
        try
        {
            HttpPost post = new HttpPost(URL);
            post.addHeader("Content-Type", "application/json");
            StringEntity entity = new StringEntity("test");
            post.setEntity(entity);
            HttpClient client = new DefaultHttpClient();
            HttpResponse response = client.execute(post);

            // assert
            System.err.println("RESPONSE: " + response.toString());
            //assertEquals(200, response.getStatusLine().getStatusCode());
            //System.err.println("\n: " + Util.bytesToHex("\n".getBytes()));
        }
        catch (IOException e) { e.printStackTrace(); }
    }

    public void post()
    {
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(URL);

        StringBuilder sb = new StringBuilder();
        InputStreamReader in;

        // Request parameters and other properties.
        List<NameValuePair> params = new ArrayList<>(1);
        params.add(new BasicNameValuePair("test", "luca"));
        //params.add(new BasicNameValuePair("param-2", "Hello!"));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

            //Execute and get the response.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                InputStream instream = entity.getContent();
                try
                {
                    // do something useful
                    in = new InputStreamReader(instream, Charset.defaultCharset());
                    BufferedReader bufferedReader = new BufferedReader(in);
                    if (bufferedReader != null)
                    {
                        int cp;
                        while ((cp = bufferedReader.read()) != -1) sb.append((char) cp);

                        bufferedReader.close();
                    }
                } finally { instream.close(); }
            }
        } catch(Exception e) { e.printStackTrace(); }

        System.out.println();
    }
}
