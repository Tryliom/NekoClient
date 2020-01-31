package neko.guicheat.clickgui.util;

import neko.Client;
import neko.module.modules.params.Gui;
import neko.module.modules.params.HUD;
import neko.module.modules.player.Nuker;
import neko.module.modules.special.Likaotique;
import neko.module.modules.special.Magnet;
import neko.module.other.enums.MagnetWay;

public class SettingsUtil {
	
	public static void SaveGuiSettings() {
		//HUD
		HUD.coord = getHUDCoord(); HUD.fps = getHUDFPS(); HUD.fall = getHUDXP(); HUD.item = getHUDMs(); HUD.time = getHUDTime(); HUD.packet = getHUDPacket(); HUD.stuff = getHUDStuff(); HUD.select = getHUDSelect(); HUD.cG = getHUDGreen(); HUD.cR = getHUDRed(); HUD.cB = getHUDBlue();
		//Nuker
		Nuker.nukerRadius = getNukerRadius(); Nuker.onehit = getNukerOneHit(); Nuker.safe = getNukerSafe();
		//Likaotique
		Likaotique.delay = (int) getLikDelay(); Likaotique.radius = (int) getLikRadius(); Likaotique.safe = getLikSafe();
		Likaotique.getLik().getTimer().setDelay((int) getLikDelay()*1000);
		//Magnet
		Magnet.classic = getMagnetClassic(); Magnet.mode = getMagnetModeSingle() ? MagnetWay.Single : MagnetWay.Multi;
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
		Nuker.nukerRadius = radius;
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
	
	//TODO: Likaotique
	public static boolean getLikSafe() {
		return Client.Neko.settingsManager.getSettingByName("LIKSafe").getValBoolean();
	}
	public static void setLikSafe(boolean safe) {
		Client.Neko.settingsManager.getSettingByName("LIKSafe").setValBoolean(safe);
	}
	public static double getLikRadius() {
		return Client.Neko.settingsManager.getSettingByName("LIKRadius").getValDouble();
	}
	public static void setLikRadius(int radius) {
		Client.Neko.settingsManager.getSettingByName("LIKRadius").setValDouble(radius);
	}
	public static double getLikDelay() {
		return Client.Neko.settingsManager.getSettingByName("LIKDelay").getValDouble();
	}
	public static void setLikDelay(int delay) {
		Client.Neko.settingsManager.getSettingByName("LIKDelay").setValDouble(delay);
	}
	
	//TODO: Magnet
	public static boolean getMagnetClassic() {
		return Client.Neko.settingsManager.getSettingByName("MAGNETClassic").getValBoolean();
	}
	public static void setMagnetClassic(boolean classic) {
		Client.Neko.settingsManager.getSettingByName("MAGNETClassic").setValBoolean(classic);
	}
	public static boolean getMagnetModeSingle() {
		return Client.Neko.settingsManager.getSettingByName("MAGNETMode").getValString().equalsIgnoreCase("Single");
	}
	public static void setMagnetMode(String mode) {
		Client.Neko.settingsManager.getSettingByName("MAGNETMode").setValString(mode);
	}
	
	//TODO: GUI
	//Rainbow
	public static boolean getRainbowGui() {
		return Client.Neko.settingsManager.getSettingByName("Gui Rainbow").getValBoolean();
	}
	
	//TODO: ArrayList
	public static boolean getArrayAlphabetique() {
		return Client.Neko.settingsManager.getSettingByName("Ordre").getValString().equalsIgnoreCase("Alphabétique");
	}
	public static boolean getArrayNameLength() {
		return Client.Neko.settingsManager.getSettingByName("Ordre").getValString().equalsIgnoreCase("Taille");
	}
	public static void setArrayOrdre(String ordre) {
		Client.Neko.settingsManager.getSettingByName("Ordre").setValString(ordre);
	}
	public static boolean getArrayModuleBasic() {
		return Client.Neko.settingsManager.getSettingByName("Rangement").getValString().equalsIgnoreCase("Module");
	}
	public static boolean getArrayModuleRandom() {
		return Client.Neko.settingsManager.getSettingByName("Rangement").getValString().equalsIgnoreCase("Mélanger");
	}
	public static void setArrayRangement(String rangement) {
		Client.Neko.settingsManager.getSettingByName("Rangement").setValString(rangement);
	}
	public static boolean getArrayInvert() {
		return Client.Neko.settingsManager.getSettingByName("Inverser").getValString().equalsIgnoreCase("Oui");
	}
	public static boolean getArrayNoInvert() {
		return Client.Neko.settingsManager.getSettingByName("Inverser").getValString().equalsIgnoreCase("Non");
	}
	public static void setArrayInvsersé(String invert) {
		Client.Neko.settingsManager.getSettingByName("Inverser").setValString(invert);
	}
	public static boolean getArrayDrawNekoBox() {
		return Client.Neko.settingsManager.getSettingByName("Shadow Box").getValString().equalsIgnoreCase("Neko Box");
	}
	public static boolean getArrayDrawNameBox() {
		return Client.Neko.settingsManager.getSettingByName("Shadow Box").getValString().equalsIgnoreCase("Name Box");
	}
	public static boolean getArrayNoDrawBox() {
		return Client.Neko.settingsManager.getSettingByName("Shadow Box").getValString().equalsIgnoreCase("Sans Box");
	}
	public static void setArrayShadowBox(String box) {
		Client.Neko.settingsManager.getSettingByName("Shadow Box").setValString(box);
	}
	public static boolean getRainbowArray() {
		return Client.Neko.settingsManager.getSettingByName("Array Color").getValString().equalsIgnoreCase("Rainbow");
	}
	public static boolean getUniColorArray() {
		return Client.Neko.settingsManager.getSettingByName("Array Color").getValString().equalsIgnoreCase("UniColor");
	}
	public static boolean getBasicColorsArray() {
		return Client.Neko.settingsManager.getSettingByName("Array Color").getValString().equalsIgnoreCase("Basique");
	}
	public static void setColorArray(String type) {
		Client.Neko.settingsManager.getSettingByName("Array Color").setValString(type);
	}
	public static void setArrayRed(double red) {
		Client.Neko.settingsManager.getSettingByName("Array Red").setValDouble(red);
	}
	public static void setArrayGreen(double green) {
		Client.Neko.settingsManager.getSettingByName("Array Green").setValDouble(green);
	}
	public static void setArrayBlue(double blue) {
		Client.Neko.settingsManager.getSettingByName("Array Blue").setValDouble(blue);
	}
	

}
