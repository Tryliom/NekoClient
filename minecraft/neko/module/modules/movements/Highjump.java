package neko.module.modules.movements;

import neko.module.Category;
import neko.module.Module;

public class Highjump extends Module {
	private static Highjump instance;
	private float height=2;
	
	public Highjump() {
		super("Highjump", -1, Category.MOVEMENT, false);
		this.instance=this;
	}
	
	public static Highjump getJump() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Hauteur:§7 "+height;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}
}
