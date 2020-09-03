package me.strugglingdoge.betteralpha.util;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import me.strugglingdoge.betteralpha.BetterAlpha;
import net.minecraft.src.ScreenShotHelper;

public class Screenshot {
    public static boolean enable;

	public static void takeScreenshot() {
        enable = false;
        ScreenShotHelper.func_4148_a(Wrapper.getMinecraft().getMinecraftDir(), Wrapper.getMinecraft().getMinecraft().displayWidth, Wrapper.getMinecraft().getMinecraft().displayHeight);
        final StringBuilder sb = new StringBuilder();
        final File screenshots = new File(sb.append(Wrapper.getMinecraft().getMinecraftDir()).append("\\screenshots").toString());
        final File[] files = screenshots.listFiles(File::isFile);
        long timeModified = Long.MIN_VALUE;
        File lastModified = null;
        File[] array;
        for (int length = (array = files).length, i = 0; i < length; ++i) {
            final File file = array[i];
            if (file.lastModified() > timeModified) {
                lastModified = file;
                timeModified = file.lastModified();
            }
        }
        if (lastModified != null) {
            uploadImage(lastModified);
        }
        else {
            BetterAlpha.addChatMessage("Unable to find ");
        }
    }
	
	public static void uploadImage(final File file) {
    	Thread thread = new Thread(() -> {
            try {
                BufferedImage image = ImageIO.read(new File(file.getAbsolutePath()));
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                ImageIO.write(image, "png", byteArray);
                byte[] fileByes = byteArray.toByteArray();
                String base64File = Base64.encodeBase64String(fileByes);
                String postData = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(base64File, "UTF-8");

                URL imgurApi = new URL("https://api.imgur.com/3/image");
                HttpURLConnection connection = (HttpURLConnection) imgurApi.openConnection();
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Authorization", "Client-ID 57e0280fe5e3a5e");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.connect();

                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                outputStreamWriter.write(postData);
                outputStreamWriter.flush();
                outputStreamWriter.close();

                StringBuilder stringBuilder = new StringBuilder();
                BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    stringBuilder.append(line).append(System.lineSeparator());
                }
                rd.close();

                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                JsonObject json = gson.fromJson(stringBuilder.toString(), JsonObject.class);
                String url = "http://i.imgur.com/" + json.get("data").getAsJsonObject().get("id").getAsString() + ".png";

                StringSelection contents = new StringSelection(url);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(contents, null);
                BetterAlpha.getInstance().addChatMessage("Screenshot URL copied to clipboard.");
            } catch (IOException e) {
            	BetterAlpha.getInstance().addChatMessage("Unable to upload screenshot.");
                e.printStackTrace();
            }

            if (!file.delete())
            	BetterAlpha.getInstance().addChatMessage("Unable to delete screenshot.");
        });
        thread.setName("Screenshot Upload Thread");
        thread.start();
    }
	
}
