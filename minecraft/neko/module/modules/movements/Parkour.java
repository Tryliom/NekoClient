package neko.module.modules.movements;

import neko.module.Category;
import neko.module.Module;

public class Parkour extends Module {
	private static Parkour instance;
	
	public Parkour() {
		super("Parkour", -1, Category.MOVEMENT);
		this.instance=this;
	}
	
	public static Parkour getParkour() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {		
		
	}
		
}
