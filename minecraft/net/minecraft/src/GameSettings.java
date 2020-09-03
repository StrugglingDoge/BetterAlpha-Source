package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

import org.lwjgl.input.Keyboard;

import me.strugglingdoge.betteralpha.BetterAlpha;
import me.strugglingdoge.betteralpha.util.EnumOptions;
import me.strugglingdoge.betteralpha.util.EnumOptionsMappingHelper;
import me.strugglingdoge.betteralpha.util.StringTranslate;
import me.strugglingdoge.betteralpha.util.Wrapper;
import net.minecraft.client.Minecraft;

public class GameSettings
{

    public GameSettings(Minecraft minecraft, File file)
    {
        musicVolume = 1.0F;
        soundVolume = 1.0F;
        mouseSensitivity = 0.5F;
        invertMouse = false;
        renderDistance = 0;
        brightness = 0;
        viewBobbing = true;
        anaglyph = false;
        limitFramerate = false;
        fancyGraphics = true;
        renderClouds = true;
        showBlockInfo = false;
        showEntityInfo = false;
        showPlayerInfo = false;
        allowStackableItems = false;
        fancyChat = false;
        showToolTips = true;
        clientCommands = false;
        showCompassWatch = false;
        userCapeUrl = "";
        skin = "Default";
        keyBindForward = new KeyBinding("Forward", 17);
        keyBindLeft = new KeyBinding("Left", 30);
        keyBindBack = new KeyBinding("Back", 31);
        keyBindRight = new KeyBinding("Right", 32);
        keyBindJump = new KeyBinding("Jump", 57);
        keyBindInventory = new KeyBinding("Inventory", 23);
        keyBindDrop = new KeyBinding("Drop", 16);
        keyBindChat = new KeyBinding("Chat", 20);
        keyBindToggleFog = new KeyBinding("Toggle fog", 33);
        keyBindSneak = new KeyBinding("Sneak", 42);
        keyBindings = (new KeyBinding[] {
            keyBindForward, keyBindLeft, keyBindBack, keyBindRight, keyBindJump, keyBindSneak, keyBindDrop, keyBindInventory, keyBindChat, keyBindToggleFog
        });
        numberOfOptions = 11;
        difficulty = 2;
        thirdPersonView = false;
        this.guiScale = 0;
        fov = 0.0F;
        field_12259_z = "";
        mc = minecraft;
        optionsFile = new File(file, "options.txt");
        loadOptions();
    }

    public GameSettings()
    {
        musicVolume = 1.0F;
        soundVolume = 1.0F;
        mouseSensitivity = 0.5F;
        invertMouse = false;
        renderDistance = 0;
        brightness = 0;
        viewBobbing = true;
        anaglyph = false;
        limitFramerate = false;
        fancyGraphics = true;
        renderClouds = true;
        showBlockInfo = false;
        showEntityInfo = false;
        showPlayerInfo = false;
        allowStackableItems = false;
        fancyChat = false;
        clientCommands = false;
        showToolTips = true;
        showCompassWatch = false;
        userCapeUrl = "";
        skin = "Default";
        keyBindForward = new KeyBinding("Forward", 17);
        keyBindLeft = new KeyBinding("Left", 30);
        keyBindBack = new KeyBinding("Back", 31);
        keyBindRight = new KeyBinding("Right", 32);
        keyBindJump = new KeyBinding("Jump", 57);
        keyBindInventory = new KeyBinding("Inventory", 23);
        keyBindDrop = new KeyBinding("Drop", 16);
        keyBindChat = new KeyBinding("Chat", 20);
        keyBindToggleFog = new KeyBinding("Toggle fog", 33);
        keyBindSneak = new KeyBinding("Sneak", 42);
        keyBindings = (new KeyBinding[] {
            keyBindForward, keyBindLeft, keyBindBack, keyBindRight, keyBindJump, keyBindSneak, keyBindDrop, keyBindInventory, keyBindChat, keyBindToggleFog
        });
        numberOfOptions = 11;
        difficulty = 2;
        thirdPersonView = false;
        field_12259_z = "";
        this.guiScale = 0;
        fov = 0.0F;
    }

    public String getKeyBinding(int i)
    {
        return (new StringBuilder()).append(keyBindings[i].keyDescription).append(": ").append(Keyboard.getKeyName(keyBindings[i].keyCode)).toString();
    }
    
    public String getKeyBinding(EnumOptions enumoptions)
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        String s = (new StringBuilder()).append(stringtranslate.translateKey(enumoptions.getEnumString())).append(": ").toString();
        if(enumoptions.getEnumFloat())
        {
            float f = getOptionFloatValue(enumoptions);
            if(enumoptions == EnumOptions.SENSITIVITY)
            {
                if(f == 0.0F)
                {
                    return (new StringBuilder()).append(s).append(stringtranslate.translateKey("options.sensitivity.min")).toString();
                }
                if(f == 1.0F)
                {
                    return (new StringBuilder()).append(s).append(stringtranslate.translateKey("options.sensitivity.max")).toString();
                } else
                {
                    return (new StringBuilder()).append(s).append((int)(f * 200F)).append("%").toString();
                }
            }
            if(enumoptions == EnumOptions.FOV)
            {
            	if(f == 0.0F) {
            		return "FOV: 70(Default)";
            	}
            	if(f == 1.0F)
                {
                    return "FOV: Quake Pro";
                } else
                {
                	return "FOV: " + (int)(70.0f + f * 40.0f);
                }
            }
            if(f == 0.0F)
            {
                return (new StringBuilder()).append(s).append(stringtranslate.translateKey("options.off")).toString();
            } else
            {
                return (new StringBuilder()).append(s).append((int)(f * 100F)).append("%").toString();
            }
            
        }
        if(enumoptions.getEnumBoolean())
        {
            boolean flag = getOptionOrdinalValue(enumoptions);
            if(flag)
            {
                return (new StringBuilder()).append(s).append(stringtranslate.translateKey("options.on")).toString();
            } else
            {
                return (new StringBuilder()).append(s).append(stringtranslate.translateKey("options.off")).toString();
            }
        }
        if(enumoptions == EnumOptions.RENDER_DISTANCE)
        {
            return (new StringBuilder()).append(s).append(stringtranslate.translateKey(RENDER_DISTANCES[renderDistance])).toString();
        }
        if(enumoptions == EnumOptions.DIFFICULTY)
        {
            return (new StringBuilder()).append(s).append(stringtranslate.translateKey(DIFFICULTIES[difficulty])).toString();
        }
        if(enumoptions == EnumOptions.GUI_SCALE)
        {
            return (new StringBuilder()).append(s).append(stringtranslate.translateKey(GUISCALES[guiScale])).toString();
        }
        if(enumoptions == EnumOptions.BRIGHTNESS)
        {
            return (new StringBuilder()).append(s).append(stringtranslate.translateKey(BRIGHTNESSES[brightness])).toString();
        }
        if(enumoptions == EnumOptions.RENDER_CLOUDS)
        {
        	return (new StringBuilder()).append(s).append(renderClouds ? "ON" : "OFF").toString();
        }
        if(enumoptions == EnumOptions.SHOW_BLOCK_INFO)
        {
        	return (new StringBuilder()).append(s).append(showBlockInfo ? "ON" : "OFF").toString();
        }
        if(enumoptions == EnumOptions.SHOW_ENTITY_INFO)
        {
        	return (new StringBuilder()).append(s).append(showEntityInfo ? "ON" : "OFF").toString();
        }
        if(enumoptions == EnumOptions.SHOW_PLAYER_INFO)
        {
        	return (new StringBuilder()).append(s).append(showPlayerInfo ? "ON" : "OFF").toString();
        }
        if(enumoptions == EnumOptions.SHOW_ITEM_TOOLTIPS)
        {
        	return (new StringBuilder()).append(s).append(showToolTips ? "ON" : "OFF").toString();
        }
        if(enumoptions == EnumOptions.STACKABLE_ITEMS)
        {
        	BetterAlpha.changeItemStackContent();
        	return (new StringBuilder()).append(s).append(allowStackableItems ? "ON" : "OFF").toString();
        }
        if(enumoptions == EnumOptions.FANCY_CHAT)
        {
        	return (new StringBuilder()).append(s).append(fancyChat ? "ON" : "OFF").toString();
        }
        if(enumoptions == EnumOptions.CLIENT_COMMANDS) 
        {
        	return (new StringBuilder()).append(s).append(clientCommands ? "ON" : "OFF").toString();
        }
        if(enumoptions == EnumOptions.SHOW_COMPASS) 
        {
        	return (new StringBuilder()).append(s).append(showCompassWatch ? "ON" : "OFF").toString();
        }
        if(enumoptions == EnumOptions.FRAMERATE_LIMIT)
        {
        	return (new StringBuilder()).append("Limit framerate: ").append(limitFramerate ? "ON" : "OFF").toString();
        }
        if(enumoptions == EnumOptions.GRAPHICS)
        {
            if(fancyGraphics)
            {
                return (new StringBuilder()).append(s).append(stringtranslate.translateKey("options.graphics.fancy")).toString();
            } else
            {
                return (new StringBuilder()).append(s).append(stringtranslate.translateKey("options.graphics.fast")).toString();
            }
        } else
        {
            return s;
        }
    }

    public void setKeyBinding(int i, int j)
    {
        keyBindings[i].keyCode = j;
        saveOptions();
    }
    
    public float getOptionFloatValue(EnumOptions enumoptions)
    {
        if(enumoptions == EnumOptions.MUSIC)
        {
            return musicVolume;
        }
        if(enumoptions == EnumOptions.SOUND)
        {
            return soundVolume;
        }
        if(enumoptions == EnumOptions.SENSITIVITY)
        {
            return mouseSensitivity;
        } 
        if(enumoptions == EnumOptions.FOV)
        {
        	return fov;
        }
        else
        {
            return 0.0F;
        }
    }

    public boolean getOptionOrdinalValue(EnumOptions enumoptions)
    {
        switch(EnumOptionsMappingHelper.enumOptionsMappingHelperArray[enumoptions.ordinal()])
        {
        case 1:
            return invertMouse;

        case 2:
            return viewBobbing;
            
        case 3:
        	return renderClouds;
            
        case 4:
        	return showBlockInfo;
            
        case 5:
        	return showEntityInfo;
            
        case 6:
        	return showPlayerInfo;
        
        case 7:
        	return showToolTips;
        	
        case 8:
        	return allowStackableItems;
        	
        case 9:
        	return fancyChat;
        	
        case 10:
        	return clientCommands;
        
        case 11:
        	return showCompassWatch;
        }
        return false;
    }
    
    public void setOptionFloatValue(int i, float f)
    {
        if(i == 0)
        {
            musicVolume = f;
            mc.sndManager.onSoundOptionsChanged();
        }
        if(i == 1)
        {
            soundVolume = f;
            mc.sndManager.onSoundOptionsChanged();
        }
        if(i == 3)
        {
            mouseSensitivity = f;
        }
        if(i == 10)
        {
        	fov = f;
        }
    }

    public void setOptionValue(int i, int j)
    {
        if(i == 2)
        {
            invertMouse = !invertMouse;
        }
        if(i == 4)
        {
            renderDistance = renderDistance + j & 3;
        }
        if(i == 5)
        {
            viewBobbing = !viewBobbing;
        }
        if(i == 6)
        {
        	this.guiScale = this.guiScale + j & 0x3;
        }
        if(i == 7)
        {
            limitFramerate = !limitFramerate;
        }
        if(i == 8)
        {
            difficulty = difficulty + j & 3;
        }
        if(i == 9)
        {
            fancyGraphics = !fancyGraphics;
            mc.field_6323_f.func_958_a();
        }
        saveOptions();
    }

    public int getOptionControlType(int i)
    {
        if(i == 0)
        {
            return 1;
        }
        if(i == 1)
        {
            return 1;
        }
        if(i == 10) {
        	return 1;
        }
        return i != 3 ? 0 : 1;
    }

    public float getOptionFloatValue(int i)
    {
        if(i == 0)
        {
            return musicVolume;
        }
        if(i == 1)
        {
            return soundVolume;
        }
        if(i == 3)
        {
            return mouseSensitivity;
        } 
        if(i == 10)
        {
        	return fov;
        }else
        {
            return 0.0F;
        }
    }

    public void loadOptions()
    {
        try
        {
            if(!optionsFile.exists())
            {
                return;
            }
            BufferedReader bufferedreader = new BufferedReader(new FileReader(optionsFile));
            for(String s = ""; (s = bufferedreader.readLine()) != null;)
            {
                String as[] = s.split(":");
                if(as[0].equals("music"))
                {
                    musicVolume = parseFloat(as[1]);
                }
                if(as[0].equals("sound"))
                {
                    soundVolume = parseFloat(as[1]);
                }
                if(as[0].equals("mouseSensitivity"))
                {
                    mouseSensitivity = parseFloat(as[1]);
                }
                if(as[0].equals("invertYMouse"))
                {
                    invertMouse = as[1].equals("true");
                }
                if(as[0].equals("viewDistance"))
                {
                    renderDistance = Integer.parseInt(as[1]);
                }
                if(as[0].equals("brightness"))
                {
                	brightness = Integer.parseInt(as[1]);
                }
                if(as[0].equals("bobView"))
                {
                    viewBobbing = as[1].equals("true");
                }
                if(as[0].equals("anaglyph3d"))
                {
                    anaglyph = as[1].equals("true");
                }
                if(as[0].equals("renderClouds"))
                {
                	renderClouds = as[1].equals("true");
                }
                if(as[0].equals("showBlockInfo"))
                {
                	showBlockInfo = as[1].equals("true");
                }
                if(as[0].equals("userCapeUrl"))
                {
                	try {
                		userCapeUrl = as[1].replace(" ", ":");
                	}catch(ArrayIndexOutOfBoundsException ex) {
                		userCapeUrl = "";
                	}
                }
                if(as[0].equals("showEntityInfo"))
                {
                	showEntityInfo = as[1].equals("true");
                }
                if(as[0].equals("showPlayerInfo"))
                {
                	showPlayerInfo = as[1].equals("true");
                }
                if(as[0].equals("showToolTips"))
                {
                	showToolTips = as[1].equals("true");
                }
                if(as[0].equals("fancyChat"))
                {
                	fancyChat = as[1].equals("true");
                }
                if(as[0].equals("clientCommands"))
                {
                	clientCommands = as[1].equals("true");
                }
                if(as[0].equals("showCompassWatch"))
                {
                	showCompassWatch = as[1].equals("true");
                }
                if(as[0].equals("limitFramerate"))
                {
                    limitFramerate = as[1].equals("true");
                }
                if (as[0].equals("guiScale")) {
                	this.guiScale = Integer.parseInt(as[1]);
                }
                if (as[0].equals("allowStackableItems")) {
                	allowStackableItems = as[1].equals("true");
                }
                if(as[0].equals("difficulty"))
                {
                    difficulty = Integer.parseInt(as[1]);
                }
                if(as[0].equals("fancyGraphics"))
                {
                    fancyGraphics = as[1].equals("true");
                }
                if(as[0].equals("skin"))
                {
                    skin = as[1];
                }
                if(as[0].equals("lastServer"))
                {
                    field_12259_z = as[1];
                }
                if(as[0].equals("fov"))
                {
                    fov = parseFloat(as[1]);
                }
                int i = 0;
                while(i < keyBindings.length) 
                {
                    if(as[0].equals((new StringBuilder()).append("key_").append(keyBindings[i].keyDescription).toString()))
                    {
                        keyBindings[i].keyCode = Integer.parseInt(as[1]);
                    }
                    i++;
                }
            }

            bufferedreader.close();
        }
        catch(Exception exception)
        {
            System.out.println("Failed to load options");
            exception.printStackTrace();
        }
    }

    private float parseFloat(String s)
    {
        if(s.equals("true"))
        {
            return 1.0F;
        }
        if(s.equals("false"))
        {
            return 0.0F;
        } else
        {
            return Float.parseFloat(s);
        }
    }

    public void saveOptions()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(optionsFile));
            printwriter.println((new StringBuilder()).append("music:").append(musicVolume).toString());
            printwriter.println((new StringBuilder()).append("sound:").append(soundVolume).toString());
            printwriter.println((new StringBuilder()).append("invertYMouse:").append(invertMouse).toString());
            printwriter.println((new StringBuilder()).append("mouseSensitivity:").append(mouseSensitivity).toString());
            printwriter.println((new StringBuilder()).append("viewDistance:").append(renderDistance).toString());
            printwriter.println((new StringBuilder()).append("guiScale:").append(guiScale).toString());
            printwriter.println((new StringBuilder()).append("bobView:").append(viewBobbing).toString());
            printwriter.println((new StringBuilder()).append("anaglyph3d:").append(anaglyph).toString());
            printwriter.println((new StringBuilder()).append("limitFramerate:").append(limitFramerate).toString());
            printwriter.println((new StringBuilder()).append("difficulty:").append(difficulty).toString());
            printwriter.println((new StringBuilder()).append("fancyGraphics:").append(fancyGraphics).toString());
            printwriter.println((new StringBuilder()).append("skin:").append(skin).toString());
            printwriter.println((new StringBuilder()).append("lastServer:").append(field_12259_z).toString());
            printwriter.println((new StringBuilder()).append("fov:").append(fov).toString());
            printwriter.println((new StringBuilder()).append("renderClouds:").append(renderClouds).toString());
            printwriter.println((new StringBuilder()).append("showBlockInfo:").append(showBlockInfo).toString());
            printwriter.println((new StringBuilder()).append("showEntityInfo:").append(showEntityInfo).toString());
            printwriter.println((new StringBuilder()).append("showPlayerInfo:").append(showPlayerInfo).toString());
            printwriter.println((new StringBuilder()).append("showToolTips:").append(showToolTips).toString());
            printwriter.println((new StringBuilder()).append("brightness:").append(brightness).toString());
            printwriter.println((new StringBuilder()).append("allowStackableItems:").append(allowStackableItems).toString());
            printwriter.println((new StringBuilder()).append("fancyChat:").append(fancyChat).toString());
            printwriter.println((new StringBuilder()).append("clientCommands:").append(clientCommands).toString());
            printwriter.println((new StringBuilder()).append("showCompassWatch:").append(showCompassWatch).toString());
            printwriter.println((new StringBuilder()).append("userCapeUrl:").append(userCapeUrl.replace(":", " ")).toString());
            for(int i = 0; i < keyBindings.length; i++)
            {
                printwriter.println((new StringBuilder()).append("key_").append(keyBindings[i].keyDescription).append(":").append(keyBindings[i].keyCode).toString());
            }

            printwriter.close();
        }
        catch(Exception exception)
        {
            System.out.println("Failed to save options");
            exception.printStackTrace();
        }
    }

    public void setOptionFloatValue(EnumOptions enumoptions, float f)
    {
        if(enumoptions == EnumOptions.MUSIC)
        {
            musicVolume = f;
            mc.sndManager.onSoundOptionsChanged();
        }
        if(enumoptions == EnumOptions.SOUND)
        {
            soundVolume = f;
            mc.sndManager.onSoundOptionsChanged();
        }
        if(enumoptions == EnumOptions.SENSITIVITY)
        {
            mouseSensitivity = f;
        } 
        if(enumoptions == EnumOptions.FOV)
        {
        	fov = f;
        }
    }
    
    public void setOptionValue(EnumOptions enumoptions, int i)
    {
        if(enumoptions == EnumOptions.INVERT_MOUSE)
        {
            invertMouse = !invertMouse;
        }
        if(enumoptions == EnumOptions.RENDER_DISTANCE)
        {
            renderDistance = renderDistance + i & 3;
        }
        if(enumoptions == EnumOptions.BRIGHTNESS)
        {
            brightness = brightness + i & 3;
            if(Wrapper.theWorld() != null)
            	Wrapper.reloadWorld();
        }
        if(enumoptions == EnumOptions.GUI_SCALE)
        {
            guiScale = guiScale + i & 3;
        }
        if(enumoptions == EnumOptions.VIEW_BOBBING)
        {
            viewBobbing = !viewBobbing;
        }
        if(enumoptions == EnumOptions.RENDER_CLOUDS)
        {
            renderClouds = !renderClouds;
        }
        if(enumoptions == EnumOptions.SHOW_BLOCK_INFO)
        {
            showBlockInfo = !showBlockInfo;
        }
        if(enumoptions == EnumOptions.SHOW_ENTITY_INFO)
        {
            showEntityInfo = !showEntityInfo;
        }
        if(enumoptions == EnumOptions.SHOW_PLAYER_INFO)
        {
            showPlayerInfo = !showPlayerInfo;
        }
        if(enumoptions == EnumOptions.SHOW_ITEM_TOOLTIPS)
        {
            showToolTips = !showToolTips;
        }
        if(enumoptions == EnumOptions.STACKABLE_ITEMS)
        {
            allowStackableItems = !allowStackableItems;
        }
        if(enumoptions == EnumOptions.FANCY_CHAT)
        {
            fancyChat = !fancyChat;
        }
        if(enumoptions == EnumOptions.CLIENT_COMMANDS)
        {
            clientCommands = !clientCommands;
        }
        if(enumoptions == EnumOptions.SHOW_COMPASS) 
        {
        	showCompassWatch = !showCompassWatch;
        }
        if(enumoptions == EnumOptions.FRAMERATE_LIMIT)
        {
            limitFramerate = !limitFramerate;
        }
        if(enumoptions == EnumOptions.DIFFICULTY)
        {
            difficulty = difficulty + i & 3;
        }
        if(enumoptions == EnumOptions.GRAPHICS)
        {
            fancyGraphics = !fancyGraphics;
            Wrapper.loadRenderers();
        }
        saveOptions();
    }
    
	private static final String RENDER_DISTANCES[] = { "FAR", "NORMAL", "SHORT", "TINY" };
	private static final String DIFFICULTY_LEVELS[] = { "Peaceful", "Easy", "Normal", "Hard" };
	private static final String DIFFICULTIES[] = { "options.difficulty.peaceful", "options.difficulty.easy",
			"options.difficulty.normal", "options.difficulty.hard" };
	private static final String GUISCALES[] = { "options.guiScale.auto", "options.guiScale.small",
			"options.guiScale.normal", "options.guiScale.large" };
	private static final String BRIGHTNESSES[] = { "Default", "Low", "Medium", "Bright" };
    public float musicVolume;
    public float soundVolume;
    public float mouseSensitivity;
    public boolean invertMouse;
    public int renderDistance;
    public int brightness;
    public boolean viewBobbing;
    public boolean anaglyph;
    public boolean limitFramerate;
    public boolean fancyGraphics;
    public boolean renderClouds;
    public boolean showBlockInfo;
    public String userCapeUrl;
    public boolean showEntityInfo;
    public boolean showPlayerInfo;
    public boolean showToolTips;
    public boolean allowStackableItems;
    public boolean fancyChat;
    public boolean clientCommands;
    public boolean showCompassWatch;
    public String skin;
    public float fov;
    public KeyBinding keyBindForward;
    public KeyBinding keyBindLeft;
    public KeyBinding keyBindBack;
    public KeyBinding keyBindRight;
    public KeyBinding keyBindJump;
    public KeyBinding keyBindInventory;
    public KeyBinding keyBindDrop;
    public KeyBinding keyBindChat;
    public KeyBinding keyBindToggleFog;
    public KeyBinding keyBindSneak;
    public KeyBinding keyBindings[];
    protected Minecraft mc;
    private File optionsFile;
    public int numberOfOptions;
    public int difficulty;
    public boolean thirdPersonView;
    public String field_12259_z;
    public int guiScale;

}
