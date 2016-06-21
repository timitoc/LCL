package com.sasluca.lcl.utils.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.nio.charset.Charset;

/**
 * Created by Sas Luca on 14-Jun-16.
 * Copyright (C) 2016 - LCL
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

    public static synchronized String getStringFromURL(String urlString)
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
                if (bufferedReader != null)
                {
                    int cp;
                    while ((cp = bufferedReader.read()) != -1) sb.append((char) cp);

                    bufferedReader.close();
                }
            }
            in.close();
        }
        catch (Exception e) { System.out.println("Exception while calling URL: "+ urlString + "\n" + e); }

        return sb.toString();
    }

    public static String encodeParameter(String s)
    { try { return URLEncoder.encode(s, "UTF-8"); } catch (UnsupportedEncodingException e) { e.printStackTrace(); return null; } }
}
