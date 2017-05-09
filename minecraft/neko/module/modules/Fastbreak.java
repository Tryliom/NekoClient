package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.mcleaks.MCLeaks;
import net.minecraft.entity.player.EntityPlayer;

public class Fastbreak extends Module {
	private static Fastbreak instance;
	
	public Fastbreak() {
		super("Fastbreak", -1, Category.PLAYER);
		this.instance=this;
	}
	
	public static Fastbreak getFastbreak() {
		return instance;
	}
	
	public void onEnabled() {		
		if (u.display)
		u.addChat("§a§oFastbreak activé !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("§c§oFastbreak désactivé !");
		super.onDisabled();
	}
	
	public void onUpdate() {		
		mc.playerController.blockHitDelay = 0;
		if (this.mc.playerController.curBlockDamageMP > 0.8F) {
			float time=net.minecraft.util.Timer.timerSpeed;
			net.minecraft.util.Timer.timerSpeed = 1.1F;
			mc.playerController.curBlockDamageMP = 1.0F;
			net.minecraft.util.Timer.timerSpeed=time;
		}
	}
	
	
		
}
