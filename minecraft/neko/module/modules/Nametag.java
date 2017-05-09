package neko.module.modules;

import neko.Client;
import neko.module.Category;
import neko.module.Module;

public class Nametag extends Module {
	Client var = Client.getNeko();
	public static boolean isOn=false;
	
	public Nametag() {
		super("Nametag", -1, Category.RENDER);
	}
	
	public void onEnabled() {
		Nametag.isOn=true;
		var.renderOn=true;
		if (u.display)
		u.addChat("§a§oNametag activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		Nametag.isOn=false;
		var.renderOn=false;
		if (u.display)
		u.addChat("§c§oNametag désactivé !");
		super.onDisabled();
	}
}
