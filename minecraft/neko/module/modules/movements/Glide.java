package neko.module.modules.movements;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.block.material.Material;

public class Glide extends Module {
	private double speed = -0.125;
	private static Glide instance;		
	
	public Glide() {
		super("Glide", -1, Category.MOVEMENT, false);
		instance=this;
	}
	
	public static Glide getGlide() {
		return instance;
	}
		
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Speed:§7 "+speed;
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

