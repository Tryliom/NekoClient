package neko.module.modules.render;

import neko.Client;
import neko.module.Category;
import neko.module.Module;

public class Nametag extends Module {
	public static boolean isOn=false;
	
	public Nametag() {
		super("Nametag", -1, Category.RENDER);
	}
	
	public void onEnabled() {
		Nametag.isOn=true;
		var.renderOn=true;
		super.onEnabled();
	}
	
	public void onDisabled() {
		Nametag.isOn=false;
		var.renderOn=false;
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "�6Taille:�7 "+
				net.minecraft.client.renderer.entity.Render.varNeko;
	}
}
