package neko.module.modules.params;

import neko.gui.InGameGui;
import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class ArrayList extends Module {
	
	public ArrayList() {
		super("ArrayList", -1, Category.PARAMS);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
	public void onRender2D() {
		// Que si le debugFPS n'est pas actif
		if (!mc.gameSettings.showDebugInfo)
			g.render();
	}
}
