package me.strugglingdoge.betteralpha.util;

import java.io.IOException;
import java.util.Properties;

public class StringTranslate
{

    private StringTranslate()
    {
        translateTable = new Properties();
        try
        {
            translateTable.load((StringTranslate.class).getResourceAsStream("/lang/en_US.lang"));
        }
        catch(IOException ioexception)
        {
            ioexception.printStackTrace();
        }
    }

    public static StringTranslate getInstance()
    {
        return instance;
    }

    public String translateKey(String s)
    {
        return translateTable.getProperty(s, s);
    }

    public String translateKeyFormat(String s, Object aobj[])
    {
        String s1 = translateTable.getProperty(s, s);
        return String.format(s1, aobj);
    }

    public String translateNamedKey(String s)
    {
        return translateTable.getProperty((new StringBuilder()).append(s).append(".name").toString(), "");
    }

    private static StringTranslate instance = new StringTranslate();
    private Properties translateTable;

}