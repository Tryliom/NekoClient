package neko.module.modules.special;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Unicode extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public Unicode() {
		super("Unicode", -1, Category.Special);
	}
	
	public void onEnabled() {	
		if (u.isLock(this.getName()))
			return;
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}	
	
	public void onUpdate() {
		if (u.isLock(this.getName())) {
			boolean display = u.display;
			u.display=false;
			this.isToggled=false;
			u.display=display;
			u.addWarn(this.getName());
			return;
		}
	}

}
