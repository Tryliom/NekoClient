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
		u.nyah=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		u.nyah=false;
		super.onDisabled();
	}
}
