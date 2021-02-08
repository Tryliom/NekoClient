package neko.module.modules.special;

import neko.module.Category;
import neko.module.Module;

public class Limit extends Module {
	private int limit = 80;
	private static Limit instance;
	
	public Limit() {
		super("Limit", -1, Category.Special, false);
		instance = this;
	}
	
	public static Limit getInstance() {
		return instance;
	}
	
	public void onEnabled() {	
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}	
	
	public void setValues() {
		this.values = "Limite de paquet: "+limit+"/sec";
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}
}
