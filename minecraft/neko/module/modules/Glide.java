package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.block.material.Material;

public class Glide extends Module {
	private double speed = -0.125;
	private static Glide instance;		
	
	public Glide() {
		super("Glide", -1, Category.MOVEMENT);
		instance=this;
	}
	
	public static Glide getGlide() {
		return instance;
	}
		
	public void onEnabled() {
		if (u.display)
		u.addChat("�a�oGlide activ� !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("�c�oGlide d�sactiv� !");
		super.onDisabled();
	}
	
	public void onUpdate() {		
		if (mc.thePlayer.motionY < 0.0d && mc.thePlayer.isAirBorne && !mc.thePlayer.isInWater() && !mc.thePlayer.isOnLadder() && !mc.thePlayer.isInsideOfMaterial(Material.lava)){
			if (!mc.thePlayer.isInsideOfMaterial(Material.lava)){
				mc.thePlayer.motionY = speed;
				mc.thePlayer.jumpMovementFactor *= 1.12337f;
			}
		}
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}	
	
}

