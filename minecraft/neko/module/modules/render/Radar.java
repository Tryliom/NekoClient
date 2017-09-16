package neko.module.modules.render;

import neko.gui.InGameGui;
import neko.module.Category;
import neko.module.Module;

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
	
	public void onRender2D() {
		if (!mc.gameSettings.showDebugInfo)
			g.renderRadar();
	}
	
	public static String getFr() {
		return fr ? "on" : "off";
	}
}
