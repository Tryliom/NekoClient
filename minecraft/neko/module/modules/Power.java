package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;

public class Power extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static boolean onLvl=false;
	public static int count=0;
	public static int p=1;

	public Power() {
		super("Power", -1, Category.RENDER);
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {
		if (!this.getToggled())
			return;
		
		if (!onLvl) {
			u.doPower(p);
		} else if (count<=200 && onLvl) {
			u.doPower(p);
			count++;
		} else if (count>200 && onLvl) {
			count=0;
			onLvl=false;
			this.setToggled(false);
		}
		
		
	}
}
