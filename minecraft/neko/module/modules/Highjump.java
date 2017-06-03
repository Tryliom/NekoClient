package neko.module.modules;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.module.other.Spoof;
import neko.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;

public class Highjump extends Module {
	private static Highjump instance;
	
	public Highjump() {
		super("Highjump", -1, Category.MOVEMENT);
		this.instance=this;
	}
	
	public static Highjump getAir() {
		return instance;
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("�a�oHighjump activ� !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("�c�oHighjump d�sactiv� !");
		super.onDisabled();
	}
}
