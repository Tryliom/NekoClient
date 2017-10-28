package neko.module.modules.combat;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class AutoClic extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static int cps=20;
	
	public AutoClic() {
		super("AutoClic", -1, Category.COMBAT);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}					
	
	public void onUpdate() {
		for (int o=0;o<(AutoClic.cps/20);o++) 
			mc.clickMouse();
	}
	
	public String getValues() {
		return "§6Cps: §7"+cps;
	}
}
