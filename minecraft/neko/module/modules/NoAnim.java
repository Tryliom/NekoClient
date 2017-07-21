package neko.module.modules;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.module.other.Spoof;
import neko.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;

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
		if (u.display)
		u.addChat("§a§oNoAnim activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oNoAnim désactivé !");
		super.onDisabled();
	}
		
}
