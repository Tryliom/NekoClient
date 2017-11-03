package neko.module.modules.render;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumParticleTypes;

import org.lwjgl.input.Keyboard;

public class Water extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static boolean onLvl=false;
	public static int count=0;
	public static int p=1;

	public Water() {
		super("Water", -1, Category.RENDER);
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
		if (!this.getToggled())
			return;
		
		if (!onLvl) {
			u.doWater(p);
		} else if (count<=200 && onLvl) {
			u.doWater(p);
			count++;
		} else if (count>200 && onLvl) {
			count=0;
			onLvl=false;
			this.setToggled(false);
		}
		
		
	}
}
