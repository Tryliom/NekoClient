package neko.module.modules.render;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.gui.GuiNewChat;

public class NekoUI extends Module {
	private static NekoUI instance;		
	
	public NekoUI() {
		super("NekoUI", -1, Category.RENDER);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	
}
