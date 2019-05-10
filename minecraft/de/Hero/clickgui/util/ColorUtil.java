package de.Hero.clickgui.util;

import java.awt.Color;

/**
 *  Made by HeroCode
 *  it's free to use
 *  but you have to credit me
 *
 *  @author HeroCode
 */
public class ColorUtil {
	
	public static Color getClickGUIColor(){
		return new Color((int) neko.Client.getNeko().settingsManager.getSettingByName("GuiRed").getValDouble(),
				(int) neko.Client.getNeko().settingsManager.getSettingByName("GuiGreen").getValDouble(),
				  (int) neko.Client.getNeko().settingsManager.getSettingByName("GuiBlue").getValDouble());
	}
}
