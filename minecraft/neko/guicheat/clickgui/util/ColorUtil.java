package neko.guicheat.clickgui.util;

import java.awt.Color;

import neko.Client;

/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit me
 *
 *  @author HeroCode
 */
public class ColorUtil {
	
	public static Color getClickGUIColor(){
		return new Color((int) Client.Neko.settingsManager.getSettingByName("Gui_Red").getValDouble(),
				(int) Client.Neko.settingsManager.getSettingByName("Gui_Green").getValDouble(),
				  (int) Client.Neko.settingsManager.getSettingByName("Gui_Blue").getValDouble());
	}
	
	public static Color rainbowEffekt(long offset, float fade) {
        float hue = (float)(System.nanoTime() + offset) / 1.0E10f % 1.0f;
        long color = Long.parseLong(Integer.toHexString(Color.HSBtoRGB(hue, 1.0f, 1.0f)), 16);
        Color c = new Color((int)color);
        return new Color((float)c.getRed() / 255.0f * fade, (float)c.getGreen() / 255.0f * fade, (float)c.getBlue() / 255.0f * fade, (float)c.getAlpha() / 255.0f);
    }
	
	public static Color getArrayUniqueColor(){
		return new Color((int) Client.Neko.settingsManager.getSettingByName("ArrayList_Array Red").getValDouble(),
				(int) Client.Neko.settingsManager.getSettingByName("ArrayList_Array Green").getValDouble(),
				  (int) Client.Neko.settingsManager.getSettingByName("ArrayList_Array Blue").getValDouble());
	}
	
	public static Color getHUDColor(){
		return new Color((int) Client.Neko.settingsManager.getSettingByName("HUD_Red").getValDouble(),
				(int) Client.Neko.settingsManager.getSettingByName("HUD_Green").getValDouble(),
				  (int) Client.Neko.settingsManager.getSettingByName("HUD_Blue").getValDouble());
	}
}
