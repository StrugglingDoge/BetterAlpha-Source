package me.strugglingdoge.betteralpha.util;

import java.util.regex.Matcher;
import java.io.IOException;
import java.util.regex.Pattern;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SkinFix
{
    private static final Map<String, String> uuids;
    
    static {
        uuids = new HashMap<String, String>();
    }
    
    public static String getSkinUrl(final String playerName) {
        final String urlBase = System.getProperty("net.piratjsk.skinfix.skinUrlBase", "https://crafatar.com/skins/");
        final String uuid = getUuidStringFromName(playerName);
        if (uuid == null) {
            return null;
        }
        return String.valueOf(urlBase) + uuid;
    }
    
    public static String getCapeUrl(final String playerName) {
        final String urlBase = System.getProperty("net.piratjsk.skinfix.capeUrlBase", "https://crafatar.com/capes/");
        final String uuid = getUuidStringFromName(playerName);
        if (uuid == null) {
            return null;
        }
        return String.valueOf(urlBase) + uuid;
    }
    
    private static String getUuidStringFromName(final String playerName) {
        if (SkinFix.uuids.containsKey(playerName)) {
            return SkinFix.uuids.get(playerName);
        }
        final String uuid = getUuidFomMojang(playerName);
        if (uuid != null) {
            SkinFix.uuids.put(playerName, uuid);
        }
        return uuid;
    }
    
    private static String getUuidFomMojang(final String playerName) {
        try {
            final URL url = new URL("https://api.mojang.com/users/profiles/minecraft/" + playerName);
            final BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            final Pattern pattern = Pattern.compile("([0-9a-f]{32})");
            String uuidString = null;
            String line;
            while ((line = in.readLine()) != null && uuidString == null) {
                if (line != null) {
                    if (line.isEmpty()) {
                        continue;
                    }
                    final Matcher matcher = pattern.matcher(line);
                    if (!matcher.find()) {
                        continue;
                    }
                    uuidString = matcher.group(1);
                }
            }
            in.close();
            return uuidString;
        }
        catch (IOException e) {
            return null;
        }
    }
}
