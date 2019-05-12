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
		return new Color((int) Client.Neko.settingsManager.getSettingByName("GuiRed").getValDouble(),
				(int) Client.Neko.settingsManager.getSettingByName("GuiGreen").getValDouble(),
				  (int) Client.Neko.settingsManager.getSettingByName("GuiBlue").getValDouble());
	}
}
