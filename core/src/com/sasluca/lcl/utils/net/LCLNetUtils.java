package com.sasluca.lcl.utils.net;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;

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

public class LCLNetUtils
{
    public static synchronized boolean checkInternetConnectivity()
    {
        Socket sock = new Socket();
        InetSocketAddress addr = new InetSocketAddress("google.com", 80);

        try { sock.connect(addr, 3000); return true; }
        catch (IOException e) { return false; }
        finally { try { sock.close(); } catch (IOException e) { return false;} }
    }

    public static synchronized boolean callURL(String url)
    {
        Socket sock = new Socket();
        InetSocketAddress addr = new InetSocketAddress(url, 80);

        try { sock.connect(addr, 3000); return true; }
        catch (IOException e) { return false; }
        finally { try { sock.close(); } catch (IOException e) { return false;} }
    }

    public static synchronized String httpGet(String urlString)
    {
        StringBuilder sb = new StringBuilder();
        URLConnection urlConn = null;
        InputStreamReader in = null;

        try
        {
            URL url = new URL(urlString);
            urlConn = url.openConnection();

            if (urlConn != null) urlConn.setReadTimeout(60 * 1000);
            if (urlConn != null && urlConn.getInputStream() != null)
            {
                in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
                BufferedReader bufferedReader = new BufferedReader(in);

                int i;
                while ((i = bufferedReader.read()) != -1) sb.append((char) i);

                in.close();
                bufferedReader.close();
            }
        }
        catch (Exception e) { System.out.println("Exception while calling URL: "+ urlString + "\n" + e); }

        return sb.toString();
    }

    public static synchronized String httpPost(String url, String json)
    {
        HttpClient httpClient = HttpClients.createDefault();
        StringBuilder sb = new StringBuilder();
        InputStreamReader in;

        try
        {
            HttpPost request = new HttpPost(url);
            request.setHeader("content-Type", "application/json");

            StringEntity params = new StringEntity(json);

            request.setEntity(params);

            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                try
                {
                    in = new InputStreamReader(entity.getContent(), Charset.defaultCharset());
                    BufferedReader bufferedReader = new BufferedReader(in);

                    int i;
                    while ((i = bufferedReader.read()) != -1) sb.append((char) i);

                    bufferedReader.close();
                }
                finally { entity.getContent().close(); }
            }
        }
        catch (Exception ex) { System.out.println("Exception while calling URL: "+ url + "\n" + ex); }

        return sb.toString();
    }

    public synchronized static String encodeParameter(String s)
    { try { return URLEncoder.encode(s, "UTF-8"); } catch (UnsupportedEncodingException e) { e.printStackTrace(); return null; } }
}
