package neko.module.modules.player;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Build extends Module {
	private boolean down = true;
	private boolean up = false;
	private boolean wall = true;
	private boolean sneak = true;
	private ArrayList<BlockPos> list = new ArrayList<>();
	private int delay=0;
	private static Build instance;
	
	public Build() {
		super("Build", -1, Category.PLAYER, false);
		instance=this;
	}
	
	public static Build getBuild() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}		
	
	public void setValues() {
		this.values = "§6Down:§7 "+Utils.displayBool(down)+"\n"
				+ "§6Up:§7 "+Utils.displayBool(up)+"\n"
				+ "§6Wall:§7 "+Utils.displayBool(wall)+"\n"
				+ "§6Sneak:§7 "+Utils.displayBool(sneak);
	}
	
	public void onUpdate() {
		if (delay>20 && !list.isEmpty() && list.size()>7) {
			for (int i=0;i<list.size();i++) {
				list.remove(i);
				if (i==list.size()/3) 
					return;
			}
			delay=0;
		} else
			delay++;
		BlockPos bb = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY-1, mc.thePlayer.posZ);			
		try {
			Item i = mc.thePlayer.getCurrentEquippedItem().getItem();
			if (!(i instanceof ItemBlock) || i.isDamageable())
				return;
		} catch (Exception e) {
			return;
		}
		if (!sneak && mc.thePlayer.isSneaking()) {
			list.clear();
			return;
		}
		
		double a = bb.getX();
		double b = bb.getY();
		double c = bb.getZ();
		if (down) {
			sendPacket(a, b, c);
			sendPacket(a+1, b, c);
			sendPacket(a+1, b, c+1);
			sendPacket(a+1, b, c-1);
			sendPacket(a-1, b, c+1);
			sendPacket(a-1, b, c-1);
			sendPacket(a-1, b, c);
			sendPacket(a, b, c+1);
			sendPacket(a, b, c-1);
		}
		if (up) {
			sendPacket(a, b+5, c);
			sendPacket(a+1, b+5, c);
			sendPacket(a+1, b+5, c+1);
			sendPacket(a+1, b+5, c-1);
			sendPacket(a-1, b+5, c+1);
			sendPacket(a-1, b+5, c-1);
			sendPacket(a-1, b+5, c);
			sendPacket(a, b+5, c+1);
			sendPacket(a, b+5, c-1);
		}
		
		Entity var2 = this.mc.getRenderViewEntity();
        EnumFacing face = var2.func_174811_aO();
        if ((wall && sneak && mc.thePlayer.isSneaking()) || (wall && !sneak))
		    switch (face.getIndex()) {
		    case 4:
		    	// X-1 
		    	a-=2;
		    	b+=2;
		    	sendPacket(a, b+1, c);
		    	sendPacket(a, b+1, c+1);
		    	sendPacket(a, b+1, c-1);
		    	sendPacket(a, b-1, c);
		    	sendPacket(a, b-1, c-1);
		    	sendPacket(a, b-1, c+1);
		    	sendPacket(a, b, c-1);
		    	sendPacket(a, b, c+1);
		    	sendPacket(a, b, c);
		    	break;
		    case 3:
		    	// Z+1
		    	c+=2;
		    	b+=2;
		    	sendPacket(a, b+1, c);
		    	sendPacket(a+1, b+1, c);
		    	sendPacket(a-1, b+1, c);
		    	sendPacket(a, b-1, c);
		    	sendPacket(a-1, b-1, c);
		    	sendPacket(a+1, b-1, c);
		    	sendPacket(a-1, b, c);
		    	sendPacket(a+1, b, c);
		    	sendPacket(a, b, c);
		    	break;
		    case 2:
		    	// Z-1
		    	c-=2;
		    	b+=2;
		    	sendPacket(a, b+1, c);
		    	sendPacket(a+1, b+1, c);
		    	sendPacket(a-1, b+1, c);
		    	sendPacket(a, b-1, c);
		    	sendPacket(a-1, b-1, c);
		    	sendPacket(a+1, b-1, c);
		    	sendPacket(a-1, b, c);
		    	sendPacket(a+1, b, c);
		    	sendPacket(a, b, c);
		    	break;
		    case 5:
		    	// X+1        	
		    	a+=2;
		    	b+=2;
		    	sendPacket(a, b+1, c);
		    	sendPacket(a, b+1, c+1);
		    	sendPacket(a, b+1, c-1);
		    	sendPacket(a, b-1, c);
		    	sendPacket(a, b-1, c-1);
		    	sendPacket(a, b-1, c+1);
		    	sendPacket(a, b, c-1);
		    	sendPacket(a, b, c+1);
		    	sendPacket(a, b, c);
		    	break;
		    }
	}
	
	public void sendPacket(double a, double b, double c) {
		int p = (mc.isSingleplayer() ? 1 : 0);
		for (BlockPos bp : list) {
			if (bp.equals(new BlockPos(a, b+p, c))) {
				return;
			}
		}
		
		if (mc.theWorld.getBlockState(new BlockPos(a, b+p, c)).getBlock().getMaterial()==Material.air || mc.theWorld.getBlockState(new BlockPos(a, b+p, c)).getBlock().getMaterial()==Material.water || mc.theWorld.getBlockState(new BlockPos(a, b+p, c)).getBlock().getMaterial()==Material.lava) {
			mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(a, b+p, c), 0, mc.thePlayer.inventory.getCurrentItem(), (float) a, (float) b+p, (float) c));
			list.add(new BlockPos(a, b+p, c));
		}
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isWall() {
		return wall;
	}

	public void setWall(boolean wall) {
		this.wall = wall;
	}

	public boolean isSneak() {
		return sneak;
	}

	public void setSneak(boolean sneak) {
		this.sneak = sneak;
	}		
	
	
}








