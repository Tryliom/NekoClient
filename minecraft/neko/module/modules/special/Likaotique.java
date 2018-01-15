package neko.module.modules.special;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import neko.Client;
import neko.manager.ModuleManager;
import neko.module.Category;
import neko.module.Module;
import neko.module.other.enums.MagnetWay;
import neko.utils.RenderUtils;
import neko.utils.TpUtils;
import neko.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSword;
import net.minecraft.network.play.client.C03PacketPlayer.C04PacketPlayerPosition;
import net.minecraft.util.BlockPos;

public class Likaotique extends Module {
	private int delay = 300;
	private javax.swing.Timer timer = new javax.swing.Timer(delay, new tptimer());
	private BlockPos currPos = null;
	private int radius = 5;
	private static Likaotique instance;
	
	public Likaotique() {
		super("Likaotique", -1, Category.Special);
		instance=this;
	}
	
	public static Likaotique getLik() {
		return instance;
	}
    
	public void onEnabled() {
		getTimer().start();
		super.onEnabled();
	}
	
	public void onDisabled() {
		getTimer().stop();
		currPos = null;
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "�6Delay:�7 "+(delay/1000d)+"sec\n"
					+ "�6Radius de tp:�7 "+radius+" blocs";
	}
	
	public BlockPos getCurrPos() {
		return currPos;
	}

	public void setCurrPos(BlockPos currPos) {
		this.currPos = currPos;
	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Boolean isPositionValid(BlockPos bp) {
		if (mc.theWorld.getBlockState(bp).getBlock().isSolidFullCube() || mc.theWorld.getBlockState(new BlockPos(bp.getX(), bp.getY(), bp.getZ())).getBlock().isSolidFullCube())
			return false;
		return true;
	}
	
	public void tpToPlayer() {
		BlockPos cb = this.getCurrPos();
		BlockPos b = mc.thePlayer.getPosition();
		Likaotique.getLik().setCurrPos(b);
		TpUtils tp = new TpUtils();
		int k = (cb==null ? tp.getK(b) : tp.getK(b, cb));
		double px = (cb==null ? tp.getTargetInPos(b).get(0) : tp.getTargetInPos(b, cb).get(0));
		double pz = (cb==null ? tp.getTargetInPos(b).get(2) : tp.getTargetInPos(b, cb).get(2));
		double psx = (cb==null ? mc.thePlayer.posX : cb.getX());
		double psz = (cb==null ? mc.thePlayer.posZ : cb.getZ());
		if (k<=30)
		for (int j=0;j<k;j++)  {  
    		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(psx+px/k, 
    				mc.thePlayer.posY, psz+pz/k, true));
		}
	}

	public javax.swing.Timer getTimer() {
		return this.timer;
	}

	public void setTimer(javax.swing.Timer timer) {
		this.timer = timer;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	
}

class tptimer implements ActionListener {
	Minecraft mc = Minecraft.getMinecraft();
	Client var = Client.getNeko();
	TpUtils tp = new TpUtils();
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		try {
			boolean find = false;
			BlockPos b = null;
			for (int i=0;i<20;i++) {
				b = Utils.getRandBlock(Likaotique.getLik().getRadius(), 2d/100d);
				if (Likaotique.getLik().isPositionValid(b)) {
					find = true;
					break;
				}
			}
			if (find) {		
				Likaotique.getLik().tpToPlayer();
				Likaotique.getLik().setCurrPos(b);
				int k = tp.getK(b);
				double px = tp.getTargetInPos(b).get(0);
				double pz = tp.getTargetInPos(b).get(2);
				double psx = mc.thePlayer.posX;
				double psz = mc.thePlayer.posZ;
				if (k>30)
					Likaotique.getLik().setCurrPos(mc.thePlayer.getPosition());
				else
					for (int j=0;j<k;j++)  {  
		        		Minecraft.getMinecraft().thePlayer.sendQueue.addToSendQueue(new C04PacketPlayerPosition(psx+px/k, 
		        				mc.thePlayer.posY, psz+pz/k, true));
					}
			}
			
		} catch (Exception e) {}
	}	
	
}













