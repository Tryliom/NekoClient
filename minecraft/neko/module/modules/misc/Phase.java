package neko.module.modules.misc;

import neko.Client;
import neko.guicheat.clickgui.settings.Setting;
import neko.guicheat.clickgui.util.SettingsUtil;
import neko.module.Category;
import neko.module.Module;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.util.EnumFacing;

public class Phase extends Module {
	public static boolean vphase=false;
	private static Phase instance;
	
	public Phase () {
		super("Phase", -1, Category.MISC, false);
		instance=this;
	}
	
	public static Phase getPhase() {
		return instance;
	}
	
	public void onEnabled() {
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "?6Phase vertical:?7 "+(vphase ? "?aActiv?" : "?cD?sactiv?");
	}
	
	public void onUpdate() {
		double p = 4*Math.random();
		if (vphase && mc.thePlayer.isCollidedVertically) {
			mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY-p, mc.thePlayer.posZ, false));
			mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY-p, mc.thePlayer.posZ);
		} else if (mc.thePlayer.isCollidedHorizontally && mc.thePlayer.isMovingXZ()) {
			Entity var2 = this.mc.getRenderViewEntity();
	        EnumFacing face = var2.func_174811_aO();				        
	        switch (face.getIndex()) {
	        case 4:
	        	// X-1 
	        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX-p, mc.thePlayer.posY, mc.thePlayer.posZ, false));
	        	mc.thePlayer.setPosition(mc.thePlayer.posX-p, mc.thePlayer.posY, mc.thePlayer.posZ);
	        	break;
	        case 3:
	        	// Z+1
	        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ+p, false));
	        	mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ+p);
	        	break;
	        case 2:
	        	// Z-1
	        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ-p, false));
	        	mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ-p);
	        	break;
	        case 5:
	        	// X+1        	
	        	mc.getNetHandler().addToSendQueue(new C04PacketPlayerPosition(mc.thePlayer.posX+p, mc.thePlayer.posY, mc.thePlayer.posZ, false));
	        	mc.thePlayer.setPosition(mc.thePlayer.posX+p, mc.thePlayer.posY, mc.thePlayer.posZ);
	        	break;
	        }
		}
        
		
	}
	
	@Override
	public void setup() {
		Client.Neko.settingsManager.rSetting(new Setting("PHASE_Vertical", this, this.vphase));
	}

	public boolean isVphase() {
		return vphase;
	}

	public void setVphase(boolean vphase) {
		this.vphase = vphase;
		SettingsUtil.setPhaseVertical(this.vphase);
	}
	
}
