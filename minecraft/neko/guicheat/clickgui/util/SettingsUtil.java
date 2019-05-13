package neko.guicheat.clickgui.util;

import neko.Client;
import neko.module.modules.params.HUD;
import neko.module.modules.player.Nuker;

public class SettingsUtil {
	
	public static void SaveGuiSettings() {
		//HUD
		HUD.coord = getHUDCoord(); HUD.fps = getHUDFPS(); HUD.fall = getHUDXP(); HUD.item = getHUDMs(); HUD.time = getHUDTime(); HUD.packet = getHUDPacket(); HUD.stuff = getHUDStuff(); HUD.select = getHUDSelect(); HUD.cG = getHUDGreen(); HUD.cR = getHUDRed(); HUD.cB = getHUDBlue();
		//Nuker
		Nuker.nukerRadius = (int)getNukerRadius(); Nuker.onehit = getNukerOneHit(); Nuker.safe = getNukerSafe();
		//Other
	}
	//TODO: HUD
	//Coord
	public static void setHUDCoord(boolean coord) {
		HUD.coord = coord;
		Client.Neko.settingsManager.getSettingByName("HUDCoord").setValBoolean(coord);
	}
	public static boolean getHUDCoord() {
		return Client.Neko.settingsManager.getSettingByName("HUDCoord").getValBoolean();
	}

	public static void setHUDFPS(boolean fps) {
		HUD.fps = fps;
		Client.Neko.settingsManager.getSettingByName("HUDFPS").setValBoolean(fps);
	}
	public static boolean getHUDFPS() {
		return Client.Neko.settingsManager.getSettingByName("HUDFPS").getValBoolean();
	}
	public static void setHUDXP(boolean fall) {
		HUD.fall = fall;
		Client.Neko.settingsManager.getSettingByName("HUDXP").setValBoolean(fall);
	}
	public static boolean getHUDXP() {
		return Client.Neko.settingsManager.getSettingByName("HUDXP").getValBoolean();
	}
	public static void setHUDMs(boolean item) {
		HUD.item = item;
		Client.Neko.settingsManager.getSettingByName("HUDMs").setValBoolean(item);
	}
	public static boolean getHUDMs() {
		return Client.Neko.settingsManager.getSettingByName("HUDMs").getValBoolean();
	}
	public static void setHUDTime(boolean time) {
		HUD.time = time;
		Client.Neko.settingsManager.getSettingByName("HUDTime").setValBoolean(time);
	}
	public static boolean getHUDTime() {
		return Client.Neko.settingsManager.getSettingByName("HUDTime").getValBoolean();
	}
	public static void setHUDPacket(boolean packet) {
		HUD.packet = packet;
		Client.Neko.settingsManager.getSettingByName("HUDPacket").setValBoolean(packet);
	}
	public static boolean getHUDPacket() {
		return Client.Neko.settingsManager.getSettingByName("HUDPacket").getValBoolean();
	}
	public static void setHUDStuff(boolean stuff) {
		HUD.stuff = stuff;
		Client.Neko.settingsManager.getSettingByName("HUDStuff").setValBoolean(stuff);
	}
	public static boolean getHUDStuff() {
		return Client.Neko.settingsManager.getSettingByName("HUDStuff").getValBoolean();
	}
	public static void setHUDSelect(boolean select) {
		HUD.select = select;
		Client.Neko.settingsManager.getSettingByName("HUDSelect").setValBoolean(select);
	}
	public static boolean getHUDSelect() {
		return Client.Neko.settingsManager.getSettingByName("HUDSelect").getValBoolean();
	}
	public static void setHUDRed(double red) {
		HUD.cR = red;
		Client.Neko.settingsManager.getSettingByName("HUDRed").setValDouble(red);
	}
	public static double getHUDRed() {
		return Client.Neko.settingsManager.getSettingByName("HUDRed").getValDouble();
	}
	public static void setHUDGreen(double green) {
		HUD.cG = green;
		Client.Neko.settingsManager.getSettingByName("HUDGreen").setValDouble(green);
	}
	public static double getHUDGreen() {
		return Client.Neko.settingsManager.getSettingByName("HUDGreen").getValDouble();
	}
	public static void setHUDBlue(double blue) {
		HUD.cB = blue;
		Client.Neko.settingsManager.getSettingByName("HUDBlue").setValDouble(blue);
	}
	public static double getHUDBlue() {
		return Client.Neko.settingsManager.getSettingByName("HUDBlue").getValDouble();
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
