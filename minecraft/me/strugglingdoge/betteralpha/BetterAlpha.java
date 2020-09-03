package me.strugglingdoge.betteralpha;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;

import me.strugglingdoge.betteralpha.command.CommandManager;
import me.strugglingdoge.betteralpha.util.Screenshot;
import me.strugglingdoge.betteralpha.util.Wrapper;
import net.minecraft.src.Item;
import net.minecraft.src.ItemDoor;
import net.minecraft.src.ItemFood;
import net.minecraft.src.ItemSign;

public class BetterAlpha {

	public static String version = "1.0";
	private boolean killSwitchEnabled = false;
    private static CommandManager cmdmanager;
    public static ArrayList<String> playerCapes = new ArrayList<String>();
    public static volatile BetterAlpha instance;

	public static void onKeyPressed(int key) {
		if(key ==  Keyboard.KEY_F2) {
			Screenshot.takeScreenshot();
		}
		if(key == Keyboard.KEY_F4) {
			addChatMessage("Reloading chunks...");
			Wrapper.reloadWorld();
		}
	}
	
	public boolean enableKillSwitch()
    {
        HttpURLConnection httpurlconnection = null;
        try
        {
            URL url = new URL("https://raw.githubusercontent.com/StrugglingDoge/BetterAlpha/master/enabled");
            httpurlconnection = (HttpURLConnection)url.openConnection();
            httpurlconnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(false);
            httpurlconnection.connect();
            if(httpurlconnection.getResponseCode() / 100 == 4)
            {
            	System.out.println(httpurlconnection.getResponseCode());
                return true;
			}
			BufferedInputStream in = new BufferedInputStream(httpurlconnection.getInputStream());
			byte[] contents = new byte[1024];

			int bytesRead = 0;
			String strFileContents = "";
			while ((bytesRead = in.read(contents)) != -1) {
				strFileContents += new String(contents, 0, bytesRead);
			}
			if(!strFileContents.contains("true"))
				return true;
			else
				return false;
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
			return true;
        }
        finally
        {
        	try 
        	{
        		httpurlconnection.disconnect();
        	}
        	catch(Exception exception)
            {
                exception.printStackTrace();
            }
        }
    }
	
	public void findAllUserSkins()
    {
        HttpURLConnection httpurlconnection = null;
        try
        {
            URL url = new URL("https://raw.githubusercontent.com/StrugglingDoge/BetterAlpha/master/playercapes");
            httpurlconnection = (HttpURLConnection)url.openConnection();
            httpurlconnection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(false);
            httpurlconnection.connect();
            if(httpurlconnection.getResponseCode() / 100 == 4)
            {
            	System.out.println(httpurlconnection.getResponseCode());
            	return;
			}
			String text = new String(ByteStreams.toByteArray(httpurlconnection.getInputStream()),Charsets.UTF_8);
			String[] splitCapesByPlayer = text.split("\n");
			for(String capes : splitCapesByPlayer) {
				playerCapes.add(capes);
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
                exception.printStackTrace();
            }
        }
    }
	
	public void register() throws LWJGLException {
		killSwitchEnabled = enableKillSwitch();
		if(killSwitchEnabled)
			Wrapper.getMinecraft().shutdown();
		findAllUserSkins();
		cmdmanager = new CommandManager();
	}
	
	public static float getBrightnessLevel() {
		int setting = Wrapper.getGameSettings().brightness;
		if(setting == 3) {
			return 1.3F;
		}else if(setting == 2) {
			return 1.2F;
		}else if(setting == 1) {
			return 1.1F;
		}else if(setting == 0) {
			return 1.0F;
		}
		return 1.0F;
	}
	
	public static void changeItemStackContent() {
		if (Wrapper.getGameSettings().allowStackableItems) {
			for (Item item : Item.itemsList) {
				if (item instanceof ItemFood)
					item.maxStackSize = 64;
				else if (item instanceof ItemDoor)
					item.maxStackSize = 16;
				else if (item instanceof ItemSign)
					item.maxStackSize = 16;
			}
		}else {
			for (Item item : Item.itemsList) {
				if (item instanceof ItemFood)
					item.maxStackSize = 1;
				else if (item instanceof ItemDoor)
					item.maxStackSize = 1;
				else if (item instanceof ItemSign)
					item.maxStackSize = 1;
			}
		}
	}
	
	public static boolean onSendChatMessage(final String s) {
        if (s.startsWith("-") && Wrapper.getGameSettings().clientCommands) {
            BetterAlpha.cmdmanager.callCommand(s.substring(1));
            return false;
        }
        return true;
    }
	
	public static BetterAlpha getInstance() {
        BetterAlpha result = BetterAlpha.instance;
        if (result == null) {
            synchronized (BetterAlpha.class) {
                result = BetterAlpha.instance;
                if (result == null) {
                    result = (BetterAlpha.instance = new BetterAlpha());
                }
            }
        }
        return result;
    }
	
	public static void addChatMessage(final String message) {
        Wrapper.thePlayer().addChatMessage("[\2474BetterAlpha\247r]: " + message);
    }
	
}
