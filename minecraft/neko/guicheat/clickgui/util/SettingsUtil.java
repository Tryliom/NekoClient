package neko.guicheat.clickgui.util;

import neko.Client;
import neko.module.modules.player.Nuker;

public class SettingsUtil {
	
	public static void SaveGuiSettings() {
		//Nuker.nukerRadius = 
	}
	
	//TODO: Nuker
	//Radius
	public static void setNukerRadius(double radius) {
		Nuker.nukerRadius = (int)radius;
		Client.Neko.settingsManager.getSettingByName("Radius").setValDouble(radius);
	}
	public static double getNukerRadius() {
		return Client.Neko.settingsManager.getSettingByName("Radius").getValDouble();
	}//OneHit
	public static boolean getNukerOneHit() {
		return Client.Neko.settingsManager.getSettingByName("OneHit").getValBoolean();
	}
	public static void setNukerOneHit(boolean onehit) {
		Nuker.onehit = onehit;
		Client.Neko.settingsManager.getSettingByName("OneHit").setValBoolean(onehit);
	}//Safe
	public static boolean getNukerSafe() {
		return Client.Neko.settingsManager.getSettingByName("Safe").getValBoolean();
	}
	public static void setNukerSafe(boolean safe) {
		Nuker.onehit = safe;
		Client.Neko.settingsManager.getSettingByName("Safe").setValBoolean(safe);
	}
	

}
