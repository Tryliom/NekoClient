package neko.module.modules.special;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;

public class Reflect extends Module {
	private static Reflect instance;
	private float power=-50;
	private float enColl;
	
	public Reflect() {
		super("Reflect", -1, Category.Special);
		this.instance=this;
	}
	
	public static Reflect getReflect() {
		return instance;
	}
	
	public void onEnabled() {		
		enColl=mc.thePlayer.entityCollisionReduction;
		super.onEnabled();
	}
	
	public void onDisabled() {
		mc.thePlayer.entityCollisionReduction=enColl;
		super.onDisabled();
	}
	
	public void onUpdate() {		
		mc.thePlayer.entityCollisionReduction=power;
		if (Utils.getPlayerInRange(1.0)!=null)
			mc.thePlayer.applyEntityCollision(Utils.getPlayerInRange(1.0));
	}

	public float getPower() {
		return power;
	}

	public void setPower(float power) {
		this.power = power;
	}
	
	
		
}