package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Autonyah extends Module {
	Minecraft mc = Minecraft.getMinecraft();

	public Autonyah() {
		super("Autonyah", -1, Category.OTHER);
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oAutonyah activé !");
		u.nyah=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oAutonyah désactivé !");
		u.nyah=false;
		super.onDisabled();
	}
}
