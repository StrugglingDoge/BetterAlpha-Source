package me.strugglingdoge.betteralpha.util;

public enum EnumOptions
{
    MUSIC("MUSIC", 0, "options.music", true, false),
    SOUND("SOUND", 1, "options.sound", true, false),
    INVERT_MOUSE("INVERT_MOUSE", 2, "options.invertMouse", false, true),
    SENSITIVITY("SENSITIVITY", 3, "options.sensitivity", true, false),
    RENDER_DISTANCE("RENDER_DISTANCE", 4, "options.renderDistance", false, false),
    VIEW_BOBBING("VIEW_BOBBING", 5, "options.viewBobbing", false, true),
    GUI_SCALE("GUI_SCALE", 6, "options.guiScale", false, false),
    FRAMERATE_LIMIT("FRAMERATE_LIMIT", 7, "options.framerateLimit", false, false),
    DIFFICULTY("DIFFICULTY", 8, "options.difficulty", false, false),
    GRAPHICS("GRAPHICS", 9, "options.graphics", false, false),
    FOV("FOV", 10, "options.fov", true, false),
    RENDER_CLOUDS("RENDER_CLOUDS", 11, "options.renderClouds", false, false),
    SHOW_BLOCK_INFO("SHOW_BLOCK_INFO", 12, "options.showBlockInfo", false, false),
    SHOW_ENTITY_INFO("SHOW_ENTITY_INFO", 13, "options.showEntityInfo", false, false),
    SHOW_PLAYER_INFO("SHOW_PLAYER_INFO", 14, "options.showPlayerInfo", false, false), 
    SHOW_ITEM_TOOLTIPS("SHOW_ITEM_TOOLTIPS", 15, "options.showItemTooltips", false, false),
    BRIGHTNESS("BRIGHTNESS", 16, "options.brightness", false, false),
    STACKABLE_ITEMS("ALLOW_STACKABLE_ITEMS", 17, "options.allowStackableItems", false, false),
    FANCY_CHAT("FANCY_CHAT", 18, "options.fancyChat", false, false),
    CLIENT_COMMANDS("CLIENT_COMMANDS", 19, "options.clientCommands", false, false),
	SHOW_COMPASS("SHOW_COMPASS", 20, "options.showCompassWatch", false, false);
	
    public static EnumOptions getEnumOptions(int i)
    {
        EnumOptions aenumoptions[] = values();
        int j = aenumoptions.length;
        for(int k = 0; k < j; k++)
        {
            EnumOptions enumoptions = aenumoptions[k];
            if(enumoptions.returnEnumOrdinal() == i)
            {
                return enumoptions;
            }
        }

        return null;
    }

    private EnumOptions(String s, int i, String s1, boolean flag, boolean flag1)
    {
        enumString = s1;
        enumFloat = flag;
        enumBoolean = flag1;
    }

    public boolean getEnumFloat()
    {
        return enumFloat;
    }

    public boolean getEnumBoolean()
    {
        return enumBoolean;
    }

    public int returnEnumOrdinal()
    {
        return ordinal();
    }

    public String getEnumString()
    {
        return enumString;
    }
/*
    public static final EnumOptions MUSIC;
    public static final EnumOptions SOUND;
    public static final EnumOptions INVERT_MOUSE;
    public static final EnumOptions SENSITIVITY;
    public static final EnumOptions RENDER_DISTANCE;
    public static final EnumOptions VIEW_BOBBING;
    public static final EnumOptions ANAGLYPH;
    public static final EnumOptions ADVANCED_OPENGL;
    public static final EnumOptions FRAMERATE_LIMIT;
    public static final EnumOptions DIFFICULTY;
    public static final EnumOptions GRAPHICS;
    public static final EnumOptions AMBIENT_OCCLUSION;
    public static final EnumOptions GUI_SCALE;
*/
    private final boolean enumFloat;
    private final boolean enumBoolean;
    private final String enumString;
    private static final EnumOptions field_20141_n[]; /* synthetic field */

    static 
    {
        field_20141_n = (new EnumOptions[] {
            MUSIC, SOUND, INVERT_MOUSE, SENSITIVITY, RENDER_DISTANCE, VIEW_BOBBING, GUI_SCALE, FRAMERATE_LIMIT, DIFFICULTY, 
            GRAPHICS, FOV, RENDER_CLOUDS, SHOW_BLOCK_INFO, SHOW_ENTITY_INFO, SHOW_PLAYER_INFO
        });
    }
}