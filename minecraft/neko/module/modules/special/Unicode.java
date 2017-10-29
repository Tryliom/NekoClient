package neko.module.modules.special;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Unicode extends Module {
	
	public Unicode() {
		super("Unicode", -1, Category.Special);
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
