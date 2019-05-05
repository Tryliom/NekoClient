package neko.module.modules.render;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Radar extends Module {
	public static boolean fr=true;
	
	public Radar() {
		super("Radar", -1, Category.RENDER);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values =  "§6Affichage des amis:§7 "+Utils.displayBool(fr);
	}
	
	public void onRender2D() {
		if (!mc.gameSettings.showDebugInfo)
			g.renderRadar();
	}
	
	public static String getFr() {
		return fr ? "on" : "off";
	}
}
