package neko.module.modules.special;

import neko.module.Category;
import neko.module.Module;

public class NoAnim extends Module {
	private static NoAnim instance;
	
	public NoAnim() {
		super("NoAnim", -1, Category.Special);
		this.instance=this;
	}
	
	public static NoAnim getAnim() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
}
