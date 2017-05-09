package neko.module.modules;

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
		if (u.display)
		u.addChat("§a§oAutoClic activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oAutoClic désactivé !");
		super.onDisabled();
	}					
	
	public void onUpdate() {
		for (int o=0;o<(AutoClic.cps/20);o++) 
			mc.clickMouse();
	}
}
