package neko.module.modules;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.module.other.Spoof;
import neko.utils.Utils;
import net.minecraft.entity.player.EntityPlayer;

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
		if (u.display)
		u.addChat("�a�oAirWalk activ� !");
		super.onEnabled();
	}
	
	public void onDisabled() {
		if (u.display)
		u.addChat("�c�oAirWalk d�sactiv� !");
		super.onDisabled();
	}
	
	public void onUpdate() {		
		if (mc.thePlayer.isAirBorne)
			mc.thePlayer.onGround=true;
	}
		
}
