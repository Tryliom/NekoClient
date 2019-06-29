package neko.module.modules.player;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Fire extends Module {
	public static int p = 1;

	public Fire() {
		super("Fire", -1, Category.RENDER, false);
	}

	public void onEnabled() {
		super.onEnabled();
	}

	public void onDisabled() {
		super.onDisabled();
	}

	public void setValues() {
		this.values = "§6Nombre de particules:§7 "+p;
	}

	public void onUpdate() {
		Utils.doFlame(p);
	}
	
}
