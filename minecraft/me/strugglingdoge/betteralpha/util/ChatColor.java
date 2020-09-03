package me.strugglingdoge.betteralpha.util;

public class ChatColor
{
    public static final String BLACK = "\2470";
    public static final String DARK_BLUE = "\2471";
    public static final String DARK_GREEN = "\2472";
    public static final String DARK_AQUA = "\2473";
    public static final String DARK_RED = "\2474";
    public static final String DARK_PURPLE = "\2475";
    public static final String GOLD = "\2476";
    public static final String GRAY = "\2477";
    public static final String DARK_GRAY = "\2478";
    public static final String BLUE = "\2479";
    public static final String GREEN = "\247a";
    public static final String AQUA = "\247b";
    public static final String RED = "\247c";
    public static final String LIGHT_PURPLE = "\247d";
    public static final String YELLOW = "\247e";
    public static final String WHITE = "\247f";
    public static final String MAGIC = "\247k";
    public static final char COLOR_CHAR = '\247';
    public static final String BOLD = "\247l";
    public static final String STRIKETHROUGH = "\247m";
    public static final String UNDERLINE = "\247n";
    public static final String ITALIC = "\247o";
    public static final String RESET = "\247r";
    public static final String RANDOM = "\247k";
    
    public static String translateAlternateColorCodes(final char altColorChar, final String textToTranslate) {
        final char[] b = textToTranslate.toCharArray();
        for (int i = 0; i < b.length - 1; ++i) {
            if (b[i] == altColorChar && "0123456789AaBbCcDdEeFfKkLlMmNnOoRr".indexOf(b[i + 1]) > -1) {
                b[i] = '\247';
                b[i + 1] = Character.toLowerCase(b[i + 1]);
            }
        }
        return new String(b);
    }
}