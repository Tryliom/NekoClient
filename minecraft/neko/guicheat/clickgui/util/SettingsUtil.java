package neko.guicheat.clickgui.util;

import neko.Client;
import neko.module.modules.combat.Reach;
import neko.module.modules.misc.Phase;
import neko.module.modules.misc.PlaceAndBreak;
import neko.module.modules.movements.Blink;
import neko.module.modules.movements.Longjump;
import neko.module.modules.params.Gui;
import neko.module.modules.params.HUD;
import neko.module.modules.player.Nuker;
import neko.module.modules.player.Velocity;
import neko.module.modules.render.Radar;
import neko.module.modules.render.Rotator;
import neko.module.modules.render.Tracers;
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
		//Velocity
		Velocity.hcoeff = getVelocityHorizontal(); Velocity.vcoeff = getVelocityVertical();
		//LongJump
		Longjump.speed = (float) getLongJumpSpeed();
		//Phase
		Phase.vphase = getPhaseVertical();
		//Reach
		Reach.dist = (float) getReachDistance(); Reach.aimbot = getReachAimbot(); Reach.bloc = getReachBloc();
		Reach.classic = getReachTPClassic(); Reach.fov = getReachFOV(); Reach.tnt = getReachTnT();
		Reach.multiaura = getReachMA(); Reach.knock = getReachKB(); Reach.mode = getReachModeNormal() ? Reach.mode.Normal : Reach.mode.Cage;
		Reach.pvp = getReachPvp();
		//Rotator
		Rotator rt = Rotator.getRotator(); rt.setSpeed(getRotatorSpeed());
		//Blink
		Blink.full = getBlinkFull();
		//Radar
		Radar.fr = getRadarFriends(); Radar.radarMap = getRadarMap(); Radar.radarText = getRadarText();
		Radar.BoolENEMIES = getRadar_Enemies(); Radar.BoolMOBS = getRadar_Mobs(); Radar.BoolANIMALS = getRadar_Animals();
		Radar.BoolGOLEM = getRadar_Golem(); Radar.boolNPC = getRadar_NPC();
		//Tracers
		Tracers.friend = getTracers_Friends();
		Tracers.BoolENEMIES = getTracers_Enemies(); Tracers.BoolMOBS = getTracers_Mobs(); Tracers.BoolANIMALS = getTracers_Animals();
		Tracers.BoolGOLEM = getTracers_Golem(); Tracers.boolNPC = getTracers_NPC(); Tracers.width = (float) getTracers_Width();
		// Place And Break
		PlaceAndBreak.getInstance().setSlotBreak(getBreakSlot());
		PlaceAndBreak.getInstance().setSlotPlace(getPlaceSlot());
		PlaceAndBreak.getInstance().setAuto(isAuto());
	}
	
	public static int getBreakSlot() {
		return (int) Client.Neko.settingsManager.getSettingByName("PlaceAndBreak_Break slot").getValDouble();
	}
	
	public static void setBreakSlot(int slot) {
		Client.Neko.settingsManager.getSettingByName("PlaceAndBreak_Break slot").setValDouble(slot);
	}
	
	public static int getPlaceSlot() {
		return (int) Client.Neko.settingsManager.getSettingByName("PlaceAndBreak_Place slot").getValDouble();
	}
	
	public static void setPlaceSlot(int slot) {
		Client.Neko.settingsManager.getSettingByName("PlaceAndBreak_P slot").setValDouble(slot);
	}
	
	public static boolean isAuto() {
		return Client.Neko.settingsManager.getSettingByName("PlaceAndBreak_Auto").getValBoolean();
	}
	
	public static void setAuto(boolean auto) {
		Client.Neko.settingsManager.getSettingByName("PlaceAndBreak_Auto").setValBoolean(auto);
	}
	
	//TODO : Blink
	public static boolean getBlinkFull() {
		return Client.Neko.settingsManager.getSettingByName("Blink_Full").getValBoolean();
	}
	
	public static void setBlinkFull(boolean full) {
		Client.Neko.settingsManager.getSettingByName("Blink_Full").setValBoolean(full);
	}
	//TODO : Rotator
	public static Double getRotatorSpeed() {
		return Client.Neko.settingsManager.getSettingByName("Rotator_Speed").getValDouble();
	}
	
	public static void setRotatorSpeed(Double speed) {
		Client.Neko.settingsManager.getSettingByName("Rotator_Speed").setValDouble(speed);
	}
	//TODO : Radar
	public static void setRadarFriends(boolean friends) {
		Radar.fr = friends;
		Client.Neko.settingsManager.getSettingByName("Radar_Friends").setValBoolean(friends);
	}
	
	public static boolean getRadarFriends() {
		return Client.Neko.settingsManager.getSettingByName("Radar_Friends").getValBoolean();
	}

	public static void setRadarMinimap(boolean minimap) {
		Radar.radarMap = minimap;
		Client.Neko.settingsManager.getSettingByName("Radar_Map").setValBoolean(minimap);
	}
	
	public static boolean getRadarMap() {
		return Client.Neko.settingsManager.getSettingByName("Radar_Map").getValBoolean();
	}

	public static void setRadarText(boolean text) {
		Radar.radarText = text;
		Client.Neko.settingsManager.getSettingByName("Radar_Text").setValBoolean(text);
	}
	
	public static boolean getRadarText() {
		return Client.Neko.settingsManager.getSettingByName("Radar_Text").getValBoolean();
	}
	
	public static void setRadar_Enemies(boolean enemies) {
		Radar.BoolENEMIES = enemies;
		Client.Neko.settingsManager.getSettingByName("Radar_Enemies").setValBoolean(enemies);
	}
	
	public static boolean getRadar_Enemies() {
		return Client.Neko.settingsManager.getSettingByName("Radar_Enemies").getValBoolean();
	}

	public static void setRadar_Mobs(boolean mobs) {
		Radar.BoolENEMIES = mobs;
		Client.Neko.settingsManager.getSettingByName("Radar_Mobs").setValBoolean(mobs);
	}
	
	public static boolean getRadar_Mobs() {
		return Client.Neko.settingsManager.getSettingByName("Radar_Mobs").getValBoolean();
	}

	public static void setRadar_Animals(boolean Animals) {
		Radar.BoolENEMIES = Animals;
		Client.Neko.settingsManager.getSettingByName("Radar_Animals").setValBoolean(Animals);
	}
	
	public static boolean getRadar_Animals() {
		return Client.Neko.settingsManager.getSettingByName("Radar_Animals").getValBoolean();
	}

	public static void setRadar_Golem(boolean Golem) {
		Radar.BoolENEMIES = Golem;
		Client.Neko.settingsManager.getSettingByName("Radar_Golem").setValBoolean(Golem);
	}
	
	public static boolean getRadar_Golem() {
		return Client.Neko.settingsManager.getSettingByName("Radar_Golem").getValBoolean();
	}

	public static void setRadar_NPC(boolean NPC) {
		Radar.BoolENEMIES = NPC;
		Client.Neko.settingsManager.getSettingByName("Radar_NPC").setValBoolean(NPC);
	}
	
	public static boolean getRadar_NPC() {
		return Client.Neko.settingsManager.getSettingByName("Radar_NPC").getValBoolean();
	}
	
	//TODO: Tracers
	public static void setTracers_Friends(boolean enemies) {
		Tracers.friend = enemies;
		Client.Neko.settingsManager.getSettingByName("Tracers_Friends").setValBoolean(enemies);
	}
	
	public static boolean getTracers_Friends() {
		return Client.Neko.settingsManager.getSettingByName("Tracers_Friends").getValBoolean();
	}
	
	public static void setTracers_Enemies(boolean enemies) {
		Tracers.BoolENEMIES = enemies;
		Client.Neko.settingsManager.getSettingByName("Tracers_Enemies").setValBoolean(enemies);
	}
	
	public static boolean getTracers_Enemies() {
		return Client.Neko.settingsManager.getSettingByName("Tracers_Enemies").getValBoolean();
	}

	public static void setTracers_Mobs(boolean mobs) {
		Tracers.BoolENEMIES = mobs;
		Client.Neko.settingsManager.getSettingByName("Tracers_Mobs").setValBoolean(mobs);
	}
	
	public static boolean getTracers_Mobs() {
		return Client.Neko.settingsManager.getSettingByName("Tracers_Mobs").getValBoolean();
	}

	public static void setTracers_Animals(boolean Animals) {
		Tracers.BoolENEMIES = Animals;
		Client.Neko.settingsManager.getSettingByName("Tracers_Animals").setValBoolean(Animals);
	}
	
	public static boolean getTracers_Animals() {
		return Client.Neko.settingsManager.getSettingByName("Tracers_Animals").getValBoolean();
	}

	public static void setTracers_Golem(boolean Golem) {
		Tracers.BoolENEMIES = Golem;
		Client.Neko.settingsManager.getSettingByName("Tracers_Golem").setValBoolean(Golem);
	}
	
	public static boolean getTracers_Golem() {
		return Client.Neko.settingsManager.getSettingByName("Tracers_Golem").getValBoolean();
	}

	public static void setTracers_NPC(boolean NPC) {
		Tracers.BoolENEMIES = NPC;
		Client.Neko.settingsManager.getSettingByName("Tracers_NPC").setValBoolean(NPC);
	}
	
	public static boolean getTracers_NPC() {
		return Client.Neko.settingsManager.getSettingByName("Tracers_NPC").getValBoolean();
	}
	
	public static void setTracers_Width(int width) {
		Tracers.width = width;
		Client.Neko.settingsManager.getSettingByName("Tracers_Width").setValDouble(width);
	}
	
	public static double getTracers_Width() {
		return Client.Neko.settingsManager.getSettingByName("Tracers_Width").getValDouble();
	}
	
	//TODO : Coord
	public static void setHUDCoord(boolean coord) {
		HUD.coord = coord;
		Client.Neko.settingsManager.getSettingByName("HUD_Coord").setValBoolean(coord);
	}
	public static boolean getHUDCoord() {
		return Client.Neko.settingsManager.getSettingByName("HUD_Coord").getValBoolean();
	}

	public static void setHUDFPS(boolean fps) {
		HUD.fps = fps;
		Client.Neko.settingsManager.getSettingByName("HUD_FPS").setValBoolean(fps);
	}
	public static boolean getHUDFPS() {
		return Client.Neko.settingsManager.getSettingByName("HUD_FPS").getValBoolean();
	}
	public static void setHUDXP(boolean fall) {
		HUD.fall = fall;
		Client.Neko.settingsManager.getSettingByName("HUD_XP").setValBoolean(fall);
	}
	public static boolean getHUDXP() {
		return Client.Neko.settingsManager.getSettingByName("HUD_XP").getValBoolean();
	}
	public static void setHUDMs(boolean item) {
		HUD.item = item;
		Client.Neko.settingsManager.getSettingByName("HUD_Ms").setValBoolean(item);
	}
	public static boolean getHUDMs() {
		return Client.Neko.settingsManager.getSettingByName("HUD_Ms").getValBoolean();
	}
	public static void setHUDTime(boolean time) {
		HUD.time = time;
		Client.Neko.settingsManager.getSettingByName("HUD_Time").setValBoolean(time);
	}
	public static boolean getHUDTime() {
		return Client.Neko.settingsManager.getSettingByName("HUD_Time").getValBoolean();
	}
	public static void setHUDPacket(boolean packet) {
		HUD.packet = packet;
		Client.Neko.settingsManager.getSettingByName("HUD_Packet").setValBoolean(packet);
	}
	public static boolean getHUDPacket() {
		return Client.Neko.settingsManager.getSettingByName("HUD_Packet").getValBoolean();
	}
	public static void setHUDStuff(boolean stuff) {
		HUD.stuff = stuff;
		Client.Neko.settingsManager.getSettingByName("HUD_Stuff").setValBoolean(stuff);
	}
	public static boolean getHUDStuff() {
		return Client.Neko.settingsManager.getSettingByName("HUD_Stuff").getValBoolean();
	}
	public static void setHUDSelect(boolean select) {
		HUD.select = select;
		Client.Neko.settingsManager.getSettingByName("HUD_Select").setValBoolean(select);
	}
	public static boolean getHUDSelect() {
		return Client.Neko.settingsManager.getSettingByName("HUD_Select").getValBoolean();
	}
	public static void setHUDRed(double red) {
		HUD.cR = red;
		Client.Neko.settingsManager.getSettingByName("HUD_Red").setValDouble(red);
	}
	public static double getHUDRed() {
		return Client.Neko.settingsManager.getSettingByName("HUD_Red").getValDouble();
	}
	public static void setHUDGreen(double green) {
		HUD.cG = green;
		Client.Neko.settingsManager.getSettingByName("HUD_Green").setValDouble(green);
	}
	public static double getHUDGreen() {
		return Client.Neko.settingsManager.getSettingByName("HUD_Green").getValDouble();
	}
	public static void setHUDBlue(double blue) {
		HUD.cB = blue;
		Client.Neko.settingsManager.getSettingByName("HUD_Blue").setValDouble(blue);
	}
	public static double getHUDBlue() {
		return Client.Neko.settingsManager.getSettingByName("HUD_Blue").getValDouble();
	}
	
	//TODO: Nuker
	//Radius
	public static void setNukerRadius(double radius) {
		Nuker.nukerRadius = radius;
		Client.Neko.settingsManager.getSettingByName("Nuker_Radius").setValDouble(radius);
	}
	public static double getNukerRadius() {
		return Client.Neko.settingsManager.getSettingByName("Nuker_Radius").getValDouble();
	}//OneHit
	public static boolean getNukerOneHit() {
		return Client.Neko.settingsManager.getSettingByName("Nuker_OneHit").getValBoolean();
	}
	public static void setNukerOneHit(boolean onehit) {
		Nuker.onehit = onehit;
		Client.Neko.settingsManager.getSettingByName("Nuker_OneHit").setValBoolean(onehit);
	}//Safe
	public static boolean getNukerSafe() {
		return Client.Neko.settingsManager.getSettingByName("Nuker_Safe").getValBoolean();
	}
	public static void setNukerSafe(boolean safe) {
		Nuker.onehit = safe;
		Client.Neko.settingsManager.getSettingByName("Nuker_Safe").setValBoolean(safe);
	}
	
	//TODO: Likaotique
	public static boolean getLikSafe() {
		return Client.Neko.settingsManager.getSettingByName("LIK_Safe").getValBoolean();
	}
	public static void setLikSafe(boolean safe) {
		Client.Neko.settingsManager.getSettingByName("LIK_Safe").setValBoolean(safe);
	}
	public static double getLikRadius() {
		return Client.Neko.settingsManager.getSettingByName("LIK_Radius").getValDouble();
	}
	public static void setLikRadius(int radius) {
		Client.Neko.settingsManager.getSettingByName("LIK_Radius").setValDouble(radius);
	}
	public static double getLikDelay() {
		return Client.Neko.settingsManager.getSettingByName("LIK_Delay").getValDouble();
	}
	public static void setLikDelay(int delay) {
		Client.Neko.settingsManager.getSettingByName("LIK_Delay").setValDouble(delay);
	}
	
	//TODO: Magnet
	public static boolean getMagnetClassic() {
		return Client.Neko.settingsManager.getSettingByName("MAGNET_Classic").getValBoolean();
	}
	public static void setMagnetClassic(boolean classic) {
		Client.Neko.settingsManager.getSettingByName("MAGNET_Classic").setValBoolean(classic);
	}
	public static boolean getMagnetModeSingle() {
		return Client.Neko.settingsManager.getSettingByName("MAGNET_Mode").getValString().equalsIgnoreCase("Single");
	}
	public static void setMagnetMode(String mode) {
		Client.Neko.settingsManager.getSettingByName("MAGNET_Mode").setValString(mode);
	}
	
	//TODO: Velocity
	public static double getVelocityHorizontal() {
		return Client.Neko.settingsManager.getSettingByName("Velocity_Horizontal").getValDouble();
	}
	public static void setVelocityHorizontal(double radius) {
		Client.Neko.settingsManager.getSettingByName("Velocity_Horizontal").setValDouble(radius);
	}
	public static double getVelocityVertical() {
		return Client.Neko.settingsManager.getSettingByName("Velocity_Vertical").getValDouble();
	}
	public static void setVelocityVertical(double delay) {
		Client.Neko.settingsManager.getSettingByName("Velocity_Vertical").setValDouble(delay);
	}
	//TODO: LongJump
	public static double getLongJumpSpeed() {
		return Client.Neko.settingsManager.getSettingByName("LongJump_Speed").getValDouble();
	}
	public static void setLongJumpSpeed(double radius) {
		Client.Neko.settingsManager.getSettingByName("LongJumpSpeed").setValDouble(radius);
	}
	//TODO: Phase
	public static boolean getPhaseVertical() {
		return Client.Neko.settingsManager.getSettingByName("PHASE_Vertical").getValBoolean();
	}
	public static void setPhaseVertical(boolean phase) {
		Client.Neko.settingsManager.getSettingByName("PHASE_Vertical").setValBoolean(phase);
	}
	
	//TODO: Reach
	public static double getReachDistance() {
		return Client.Neko.settingsManager.getSettingByName("REACH_Distance").getValDouble();
	}
	public static void setReachDistance(double distance) {
		Client.Neko.settingsManager.getSettingByName("REACH_Distance").setValDouble(distance);
	}
	public static boolean getReachAimbot() {
		return Client.Neko.settingsManager.getSettingByName("REACH_Aimbot").getValBoolean();
	}
	public static void setReachAimbot(boolean aimbot) {
		Client.Neko.settingsManager.getSettingByName("REACH_Aimbot").setValBoolean(aimbot);
	}
	public static boolean getReachPvp() {
		return Client.Neko.settingsManager.getSettingByName("REACH_Pvp").getValBoolean();
	}
	public static void setReachPvp(boolean pvp) {
		Client.Neko.settingsManager.getSettingByName("REACH_Pvp").setValBoolean(pvp);
	}
	public static boolean getReachBloc() {
		return Client.Neko.settingsManager.getSettingByName("REACH_Bloc").getValBoolean();
	}
	public static void setReachBloc(boolean bloc) {
		Client.Neko.settingsManager.getSettingByName("REACH_Bloc").setValBoolean(bloc);
	}
	public static boolean getReachTPClassic() {
		return Client.Neko.settingsManager.getSettingByName("REACH_Tp Classic").getValBoolean();
	}
	public static void setReachTPCLassic(boolean tpclassic) {
		Client.Neko.settingsManager.getSettingByName("REACH_Tp Classic").setValBoolean(tpclassic);
	}
	public static double getReachFOV() {
		return Client.Neko.settingsManager.getSettingByName("REACH_Fov").getValDouble();
	}
	public static void setReachFOV(double fov) {
		Client.Neko.settingsManager.getSettingByName("REACH_Fov").setValDouble(fov);
	}
	public static boolean getReachTnT() {
		return Client.Neko.settingsManager.getSettingByName("REACH_Tnt").getValBoolean();
	}
	public static void setReachTnT(boolean tnt) {
		Client.Neko.settingsManager.getSettingByName("REACH_Tnt").setValBoolean(tnt);
	}
	public static boolean getReachMA() {
		return Client.Neko.settingsManager.getSettingByName("REACH_Multiaura").getValBoolean();
	}
	public static void setReachMA(boolean multiaura) {
		Client.Neko.settingsManager.getSettingByName("REACH_Multiaura").setValBoolean(multiaura);
	}
	public static boolean getReachKB() {
		return Client.Neko.settingsManager.getSettingByName("REACH_Knockback").getValBoolean();
	}
	public static void setReachKB(boolean kb) {
		Client.Neko.settingsManager.getSettingByName("REACH_Knockback").setValBoolean(kb);
	}
	public static boolean getReachModeNormal() {
		return Client.Neko.settingsManager.getSettingByName("REACH_Mode (For Tnt)").getValString().equalsIgnoreCase("Normal");
	}
	public static void setReachMode(String mode) {
		Client.Neko.settingsManager.getSettingByName("REACH_Mode (For Tnt)").setValString(mode);
	}
	//TODO: KillAura
	
	//TODO: GUI
	//Rainbow
	public static boolean getRainbowGui() {
		return Client.Neko.settingsManager.getSettingByName("Gui_Rainbow").getValBoolean();
	}
	
	//TODO: ArrayList
	public static boolean getArrayAlphabetique() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Ordre").getValString().equalsIgnoreCase("Alphabétique");
	}
	public static boolean getArrayNameLength() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Ordre").getValString().equalsIgnoreCase("Taille");
	}
	public static void setArrayOrdre(String ordre) {
		Client.Neko.settingsManager.getSettingByName("Arraylist_Ordre").setValString(ordre);
	}
	public static boolean getArrayModuleBasic() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Rangement").getValString().equalsIgnoreCase("Module");
	}
	public static boolean getArrayModuleRandom() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Rangement").getValString().equalsIgnoreCase("Mélanger");
	}
	public static void setArrayRangement(String rangement) {
		Client.Neko.settingsManager.getSettingByName("Arraylist_Rangement").setValString(rangement);
	}
	public static boolean getArrayInvert() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Inverser").getValString().equalsIgnoreCase("Oui");
	}
	public static boolean getArrayNoInvert() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Inverser").getValString().equalsIgnoreCase("Non");
	}
	public static void setArrayInvsersé(String invert) {
		Client.Neko.settingsManager.getSettingByName("Arraylist_Inverser").setValString(invert);
	}
	public static boolean getArrayDrawNekoBox() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Shadow Box").getValString().equalsIgnoreCase("Neko Box");
	}
	public static boolean getArrayDrawNameBox() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Shadow Box").getValString().equalsIgnoreCase("Name Box");
	}
	public static boolean getArrayNoDrawBox() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Shadow Box").getValString().equalsIgnoreCase("Sans Box");
	}
	public static void setArrayShadowBox(String box) {
		Client.Neko.settingsManager.getSettingByName("Arraylist_Shadow Box").setValString(box);
	}
	public static boolean getRainbowArray() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Array Color").getValString().equalsIgnoreCase("Rainbow");
	}
	public static boolean getUniColorArray() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Array Color").getValString().equalsIgnoreCase("UniColor");
	}
	public static boolean getBasicColorsArray() {
		return Client.Neko.settingsManager.getSettingByName("Arraylist_Array Color").getValString().equalsIgnoreCase("Basique");
	}
	public static void setColorArray(String type) {
		Client.Neko.settingsManager.getSettingByName("Arraylist_Array Color").setValString(type);
	}
	public static void setArrayRed(double red) {
		Client.Neko.settingsManager.getSettingByName("Arraylist_Array Red").setValDouble(red);
	}
	public static void setArrayGreen(double green) {
		Client.Neko.settingsManager.getSettingByName("Arraylist_Array Green").setValDouble(green);
	}
	public static void setArrayBlue(double blue) {
		Client.Neko.settingsManager.getSettingByName("Arraylist_Array Blue").setValDouble(blue);
	}
	

}
