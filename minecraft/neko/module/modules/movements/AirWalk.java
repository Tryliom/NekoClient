package neko.module.modules.movements;

import neko.module.Category;
import neko.module.Module;

public class AirWalk extends Module {
	private static AirWalk instance;
	
	public AirWalk() {
		super("AirWalk", -1, Category.MOVEMENT);
		this.instance=this;
	}
	
	public static AirWalk getAir() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public String getValues() {
		return "";
	}
	
	public void onUpdate() {		
		if (mc.thePlayer.isAirBorne)
			mc.thePlayer.onGround=true;
	}
		
}
