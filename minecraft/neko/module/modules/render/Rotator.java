package neko.module.modules.render;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Rotator extends Module {
	private Integer rotate = 0;
	private static Rotator instance = null;
	
	public Rotator() {
		super("Rotator", -1, Category.RENDER, false);
		instance = this;
	}
	
	public static Rotator getRotator() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {
		rotate += 20;
		if (rotate == 380)
			rotate = 0;
	}

	public Integer getRotate() {
		return rotate;
	}
}
