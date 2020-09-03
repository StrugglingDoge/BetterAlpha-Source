package net.minecraft.src;

import me.strugglingdoge.betteralpha.gui.NahrFont;
import me.strugglingdoge.betteralpha.gui.NahrFont.FontType;
import me.strugglingdoge.betteralpha.util.EnumOptions;
import me.strugglingdoge.betteralpha.util.RenderUtils;
import me.strugglingdoge.betteralpha.util.StringTranslate;

public class GuiBetterAlphaSettings  extends GuiScreen
{

	private static boolean isHovering = false;
	
	private static NahrFont font;
	
    public GuiBetterAlphaSettings(GuiScreen guiscreen, GameSettings gamesettings)
    {
        field_22107_a = "Better Alpha Settings";
        field_22110_h = guiscreen;
        guiGameSettings = gamesettings;
        if(font == null) {
            this.font = new NahrFont("Verdana", 18.0F);
        }
    }

    public void initGui()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        int i = 0;
        EnumOptions aenumoptions[] = field_22108_k;
        int j = aenumoptions.length;
        for(int k = 0; k < j; k++)
        {
            EnumOptions enumoptions = aenumoptions[k];
            if(!enumoptions.getEnumFloat())
            {
                controlList.add(new GuiSmallButton(enumoptions.returnEnumOrdinal(), (width / 2 - 155) + (i % 2) * 160, height / 6 + 24 * (i >> 1), enumoptions, guiGameSettings.getKeyBinding(enumoptions)));
            } else
            {
                controlList.add(new GuiSlider(enumoptions.returnEnumOrdinal(), (width / 2 - 155) + (i % 2) * 160, height / 6 + 24 * (i >> 1), enumoptions, guiGameSettings.getKeyBinding(enumoptions), guiGameSettings.getOptionFloatValue(enumoptions)));
            }
            i++;
        }

        controlList.add(new GuiButton(102, width / 2 - 100, height / 6 + 138, "Change Cape"));
        controlList.add(new GuiButton(200, width / 2 - 100, height / 6 + 168, stringtranslate.translateKey("gui.done")));
    }
    
    public static void hoveringButton(GuiButton guibutton, int x, int y, int width, int height) {
    	if(guibutton.displayString.contains("Fancy Chat")) {
    		RenderUtils.drawBorderedRect(x - 100, y - 35, x + 83, y, 1,
    				-16777216, 0x90000000);
    		font.drawString("Fancy Chat:", x - 95, y - 35, FontType.NORMAL, 0xffffffff);
    		font.drawString("Changes chat font to Verdana, making", x - 95, y - 25, FontType.NORMAL, 0xffffffff);
    		font.drawString("it look fancier.", x - 95, y - 15, FontType.NORMAL, 0xffffffff);
    	}else if(guibutton.displayString.contains("Render Clouds")) {
    		RenderUtils.drawBorderedRect(x - 100, y - 35, x + 80, y, 1,
    				-16777216, 0x90000000);
    		font.drawString("Render Clouds:", x - 95, y - 35, FontType.NORMAL, 0xffffffff);
    		font.drawString("Allows you to toggle clouds which can", x - 95, y - 25, FontType.NORMAL, 0xffffffff);
    		font.drawString("improve performance if turned off.", x - 95, y - 15, FontType.NORMAL, 0xffffffff);
    	}else if(guibutton.displayString.contains("Show Block Info")) {
    		RenderUtils.drawBorderedRect(x - 100, y - 35, x + 80, y, 1,
    				-16777216, 0x90000000);
    		font.drawString("Block Info:", x - 95, y - 35, FontType.NORMAL, 0xffffffff);
    		font.drawString("Shows info about the block you are", x - 95, y - 25, FontType.NORMAL, 0xffffffff);
    		font.drawString("currently looking at.", x - 95, y - 15, FontType.NORMAL, 0xffffffff);
    	}else if(guibutton.displayString.contains("Show Entity Info")) {
    		RenderUtils.drawBorderedRect(x - 100, y - 35, x + 80, y, 1,
    				-16777216, 0x90000000);
    		font.drawString("Entity Info:", x - 95, y - 35, FontType.NORMAL, 0xffffffff);
    		font.drawString("Shows info about the entity you are", x - 95, y - 25, FontType.NORMAL, 0xffffffff);
    		font.drawString("currently looking at.", x - 95, y - 15, FontType.NORMAL, 0xffffffff);
    	}else if(guibutton.displayString.contains("Show Player Info")) {
    		RenderUtils.drawBorderedRect(x - 100, y - 35, x + 78, y, 1,
    				-16777216, 0x90000000);
    		font.drawString("Player Info:", x - 95, y - 35, FontType.NORMAL, 0xffffffff);
    		font.drawString("Shows your XYZ, FPS, and IGN in the", x - 95, y - 25, FontType.NORMAL, 0xffffffff);
    		font.drawString("bottom left of the screen.", x - 95, y - 15, FontType.NORMAL, 0xffffffff);
    	}else if(guibutton.displayString.contains("Show Tooltips")) {
    		RenderUtils.drawBorderedRect(x - 100, y - 35, x + 80, y, 1,
    				-16777216, 0x90000000);
    		font.drawString("Tool Tips:", x - 95, y - 35, FontType.NORMAL, 0xffffffff);
    		font.drawString("Shows the name of the item that you", x - 95, y - 25, FontType.NORMAL, 0xffffffff);
    		font.drawString("are currently hovering over/holding.", x - 95, y - 15, FontType.NORMAL, 0xffffffff);
    	}else if(guibutton.displayString.contains("Brightness")) {
    		RenderUtils.drawBorderedRect(x - 100, y - 23, x + 70, y, 1,
    				-16777216, 0x90000000);
    		font.drawString("Brightness:", x - 95, y - 25, FontType.NORMAL, 0xffffffff);
    		font.drawString("Changes the brightness of the world.", x - 95, y - 15, FontType.NORMAL, 0xffffffff);
    	}else if(guibutton.displayString.contains("Stackable Items")) {
    		RenderUtils.drawBorderedRect(x - 100, y - 35, x + 60, y, 1,
    				-16777216, 0x90000000);
    		font.drawString("Stackable Items:", x - 95, y - 35, FontType.NORMAL, 0xffffffff);
    		font.drawString("Allows items such as food, doors,", x - 95, y - 25, FontType.NORMAL, 0xffffffff);
    		font.drawString("and signs to be stackable.", x - 95, y - 15, FontType.NORMAL, 0xffffffff);
    	}else if(guibutton.displayString.contains("Client Commands")) {
    		RenderUtils.drawBorderedRect(x - 100, y - 35, x + 78, y, 1,
    				-16777216, 0x90000000);
    		font.drawString("Client Commands:", x - 95, y - 35, FontType.NORMAL, 0xffffffff);
    		font.drawString("Adds some fun and useful commands", x - 95, y - 25, FontType.NORMAL, 0xffffffff);
    		font.drawString("to the game. Try typing -help in chat.", x - 95, y - 15, FontType.NORMAL, 0xffffffff);
    	}else if(guibutton.displayString.contains("Show Compass")) {
    		RenderUtils.drawBorderedRect(x - 100, y - 35, x + 66, y, 1,
    				-16777216, 0x90000000);
    		font.drawString("Show Compass + Watch:", x - 95, y - 35, FontType.NORMAL, 0xffffffff);
    		font.drawString("Shows a compass and watch in the", x - 95, y - 25, FontType.NORMAL, 0xffffffff);
    		font.drawString("bottom right side of the screen.", x - 95, y - 15, FontType.NORMAL, 0xffffffff);
    	}
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if(!guibutton.enabled)
        {
            return;
        }
        if(guibutton.id < 100 && (guibutton instanceof GuiSmallButton))
        {
            guiGameSettings.setOptionValue(((GuiSmallButton)guibutton).returnEnumOptions(), 1);
            guibutton.displayString = guiGameSettings.getKeyBinding(EnumOptions.getEnumOptions(guibutton.id));
        }
        if(guibutton.id == 102) {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(new GuiCape(this));
        }
        if(guibutton.id == 200)
        {
            mc.gameSettings.saveOptions();
            mc.displayGuiScreen(field_22110_h);
        }
        ScaledResolution scaledresolution = new ScaledResolution(mc.gameSettings, mc.displayWidth, mc.displayHeight);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        setWorldAndResolution(mc, i, j);
    }

    public void drawScreen(int i, int j, float f)
    {
        drawDefaultBackground();
        drawCenteredString(fontRenderer, field_22107_a, width / 2, 20, 0xffffff);
        super.drawScreen(i, j, f);
    }

    private GuiScreen field_22110_h;
    protected String field_22107_a;
    private GameSettings guiGameSettings;
    private static EnumOptions field_22108_k[];

    static 
    {
        field_22108_k = (new EnumOptions[] {
            EnumOptions.RENDER_CLOUDS, EnumOptions.SHOW_BLOCK_INFO, EnumOptions.SHOW_ENTITY_INFO, EnumOptions.SHOW_PLAYER_INFO, EnumOptions.SHOW_ITEM_TOOLTIPS, EnumOptions.BRIGHTNESS, EnumOptions.STACKABLE_ITEMS,
            EnumOptions.FANCY_CHAT, EnumOptions.CLIENT_COMMANDS, EnumOptions.SHOW_COMPASS
        });
    }
}