package neko.module.modules.special;

import neko.module.Category;
import neko.module.Module;

public class Nausicaah extends Module {
	private static Nausicaah instance;
	
	public Nausicaah() {
		super("Nausicaah", -1, Category.Special);
		this.instance=this;
	}
	
	public static Nausicaah getNausi() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onClick() {
		
	}
		
}
