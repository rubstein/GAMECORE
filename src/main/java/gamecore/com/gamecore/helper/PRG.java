package gamecore.com.gamecore.helper;

import gamecore.com.gamecore.exception.DangerException;
import gamecore.com.gamecore.exception.InfoException;

public class PRG {
    private PRG() 
    {

    }

    public static void info(String mensaje, String link) throws InfoException 
    {
        throw new InfoException(mensaje + "@" + link);
    }

    public static void info(String mensaje) throws InfoException 
    {
        throw new InfoException(mensaje + "@" + "/");
    }

    public static void error(String mensaje, String link) throws DangerException 
    {
        throw new DangerException(mensaje + "@" + link);
    }

    public static void error(String mensaje) throws DangerException 
    {
        throw new DangerException(mensaje + "@" + "/");
    }
}
