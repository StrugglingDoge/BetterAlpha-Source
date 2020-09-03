package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;

class ThreadDownloadImage extends Thread
{

    ThreadDownloadImage(ThreadDownloadImageData threaddownloadimagedata, String s, ImageBuffer imagebuffer)
    {
//        super();
        imageData = threaddownloadimagedata;
        location = s;
        buffer = imagebuffer;
    }

    public void run()
    {
        HttpURLConnection httpurlconnection = null;
        try
        {
            URL url = new URL(location);
            httpurlconnection = (HttpURLConnection)url.openConnection();
            httpurlconnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(false);
            httpurlconnection.connect();
            if(httpurlconnection.getResponseCode() / 100 == 4)
            {
                return;
            }
            if(buffer == null)
            {
                imageData.image = ImageIO.read(httpurlconnection.getInputStream());
            } else
            {
                imageData.image = buffer.func_883_a(ImageIO.read(httpurlconnection.getInputStream()));
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
        	try 
        	{
        		httpurlconnection.disconnect();
        	}
        	catch(Exception exception)
            {
            }
        }
    }

    final String location; /* synthetic field */
    final ImageBuffer buffer; /* synthetic field */
    final ThreadDownloadImageData imageData; /* synthetic field */
}
