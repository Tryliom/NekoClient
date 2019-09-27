package neko.module.modules.render;

import neko.module.Category;
import neko.module.Module;

public class UnclaimFinder extends Module {
	
	public UnclaimFinder() {
		super("UnclaimFinder", -1, Category.RENDER, false);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onRender2D() {
		if (!mc.gameSettings.showDebugInfo)
			g.renderUnclaimFinder();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	
}
