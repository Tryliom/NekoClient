package neko.module.modules.render;

import neko.Client;
import neko.guicheat.clickgui.settings.Setting;
import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Radar extends Module {
	public static boolean fr=true;
	public static boolean radarText=true;
	public static boolean radarMap=true;
	public static boolean radarRealName=true;
	
	public static Float[] friend = Utils.getFloatColorFromInt(2090765); 
    public static Float[] me = Utils.getFloatColorFromInt(3308968);
	public static boolean BoolME = true;
    public static Float[] enemies = Utils.getFloatColorFromInt(15278085); 
	public static boolean BoolENEMIES = true;
    public static Float[] mobs = Utils.getFloatColorFromInt(15165453); 
	public static boolean BoolMOBS = true;
    public static Float[] animals = Utils.getFloatColorFromInt(6473157); 
	public static boolean BoolANIMALS = true;
    public static Float[] golem = Utils.getFloatColorFromInt(10856100); 
	public static boolean BoolGOLEM = true;
    public static Float[] npc = Utils.getFloatColorFromInt(15557860);
	public static boolean boolNPC = true;
	
	
	public Radar() {
		super("Radar", -1, Category.RENDER, false);
	}
	
	public void onEnabled() {
		
		super.onEnabled();
	}
	
	@Override
	public void setup() {
		Client.getNeko().settingsManager.rSetting(new Setting("Radar_Friends", this, this.fr));
		Client.getNeko().settingsManager.rSetting(new Setting("Radar_Text", this, this.radarText));
		Client.getNeko().settingsManager.rSetting(new Setting("Radar_Map", this, this.radarMap));
		Client.getNeko().settingsManager.rSetting(new Setting("Radar_Enemies", this, this.BoolENEMIES));
		Client.getNeko().settingsManager.rSetting(new Setting("Radar_Mobs", this, this.BoolMOBS));
		Client.getNeko().settingsManager.rSetting(new Setting("Radar_Animals", this, this.BoolANIMALS));
		Client.getNeko().settingsManager.rSetting(new Setting("Radar_Golem", this, this.BoolGOLEM));
		Client.getNeko().settingsManager.rSetting(new Setting("Radar_NPC", this, this.boolNPC));
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values =  "§6Affichage des amis:§7 "+Utils.displayBool(fr);
	}
	
	public void onRender2D() {
		if (!mc.gameSettings.showDebugInfo) {
			if(this.radarText) {
				g.renderRadarText();
			}
			if(this.radarMap) {
				g.renderRadarMap();
				//g.renderRadarInfos(); à modifier
			}
		}
	}
	
	public static String getFr() {
		return fr ? "on" : "off";
	}
}
