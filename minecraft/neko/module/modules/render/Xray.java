package neko.module.modules.render;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public final class Xray extends Module {
	
	public Xray() {
		super("Xray", Keyboard.KEY_X, Category.RENDER);
	}

	public void onEnabled() {
		mc.renderGlobal.loadRenderers();
		Utils.addChat("§a§oXRay activé !");
		Utils.addChat("§6Le §d§lXRay §6comporte quelques bugs, nous vous conseillons d'utiliser le §d§l..search ");
		Utils.addChat("§d§l..help search §6pour connaître l'utilisation de ce module ! Meow.");
	}

	public void onDisabled() {
		mc.renderGlobal.loadRenderers();
		Utils.addChat("§c§oXRay désactivé !");
	}

	public void setValues() {
		this.values = "";
	}

}
