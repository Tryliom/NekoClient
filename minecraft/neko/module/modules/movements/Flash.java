package neko.module.modules.movements;

import java.util.Vector;

import neko.module.Category;
import neko.module.Module;
import neko.utils.TpUtils;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.util.BlockPos;

public class Flash extends Module {
	private EntityOtherPlayerMP fakePlayer = null;
	private BlockPos lastPos;
	private static Flash instance=null;
	
	public Flash() {
		super("Flash", -1, Category.MOVEMENT);
		this.instance = this;
	}
	
	public static Flash getFlash() {
		return instance;
	}
	
	public void onEnabled() {
		this.lastPos=mc.thePlayer.getPosition();
	    this.fakePlayer = new EntityOtherPlayerMP(mc.theWorld, mc.thePlayer.getGameProfile());
	    this.fakePlayer.clonePlayer(mc.thePlayer, true);
	    this.fakePlayer.copyLocationAndAnglesFrom(mc.thePlayer);
	    this.fakePlayer.rotationYawHead = mc.thePlayer.rotationYawHead;
	    mc.theWorld.addEntityToWorld(-3, this.fakePlayer);
		super.onEnabled();
	}
	
	public void onDisabled() {
	    mc.theWorld.removeEntityFromWorld(-3);
	    this.fakePlayer = null;
	    back();
	    mc.theWorld.updateEntities();
		super.onDisabled();
	}		
	
	private void back() {
		TpUtils tp = new TpUtils();
		Vector<Double> v = tp.getTargetInPos(this.lastPos);	
		EntityWitch en1 = new EntityWitch(mc.theWorld);
		en1.setPosition(this.lastPos.getX(), this.lastPos.getY(), this.lastPos.getZ());
		tp.doTpAller(en1, v.get(0), v.get(1), v.get(2), false, tp.getK(this.lastPos));
		BlockPos actualPos = new BlockPos(this.lastPos.getX(), this.lastPos.getY(), this.lastPos.getZ());
		mc.thePlayer.setPosition(mc.thePlayer.posX, mc.thePlayer.posY+0.02*Math.random(), mc.thePlayer.posZ);
    	mc.thePlayer.playSound("mob.endermen.portal", 1.0F, 1.0F);
	}
}
