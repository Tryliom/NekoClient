package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class WorldTime extends Module {
	public static long time=13000;
	
	public WorldTime() {
		super("WorldTime", -1, Category.RENDER);
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oWorldTime activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oWorldTime désactivé !");
		super.onDisabled();
	}
	
	public void onUpdate() {
		mc.theWorld.setWorldTime(time);
	}
}
