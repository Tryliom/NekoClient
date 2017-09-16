package neko.module.modules;

import neko.gui.InGameGui;
import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class ArrayList extends Module {
	
	public ArrayList() {
		super("ArrayList", -1, Category.RENDER);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onRender2D() {
		// Que si le debugFPS n'est pas actif
		if (!mc.gameSettings.showDebugInfo)
			g.render();
	}
}
