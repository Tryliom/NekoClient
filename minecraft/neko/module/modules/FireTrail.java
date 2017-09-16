package neko.module.modules;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class FireTrail extends Module {
	private boolean large=false;
	private static FireTrail instance;
	
	public FireTrail() {
		super("FireTrail", -1, Category.Special);
		instance=this;
	}
	
	public static FireTrail getFireTrail() {
		return instance;
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}		
	
	public void onUpdate() {
		if (!hasFire())
			return;
		// A si large = false, B et C pour une trainée large
		BlockPos a = null;
		BlockPos b = null;
		BlockPos c = null;
		Entity var2 = this.mc.func_175606_aa();
        EnumFacing face = var2.func_174811_aO();				        
        switch (face.getIndex()) {
        case 4:
        	a = new BlockPos(mc.thePlayer.posX+2, mc.thePlayer.posY, mc.thePlayer.posZ); 
        	b = new BlockPos(mc.thePlayer.posX+2, mc.thePlayer.posY, mc.thePlayer.posZ+1);
        	c = new BlockPos(mc.thePlayer.posX+2, mc.thePlayer.posY, mc.thePlayer.posZ-1); 
        	break;
        case 3:
        	a = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ-2);
        	b = new BlockPos(mc.thePlayer.posX+1, mc.thePlayer.posY, mc.thePlayer.posZ-2);
        	c = new BlockPos(mc.thePlayer.posX-1, mc.thePlayer.posY, mc.thePlayer.posZ-2);
        	break;
        case 2:
        	a = new BlockPos(mc.thePlayer.posX, mc.thePlayer.posY, mc.thePlayer.posZ+2);
        	b = new BlockPos(mc.thePlayer.posX+1, mc.thePlayer.posY, mc.thePlayer.posZ+2);
        	c = new BlockPos(mc.thePlayer.posX-1, mc.thePlayer.posY, mc.thePlayer.posZ+2);
        	break;
        case 5:
        	a = new BlockPos(mc.thePlayer.posX-2, mc.thePlayer.posY, mc.thePlayer.posZ);
        	b = new BlockPos(mc.thePlayer.posX-2, mc.thePlayer.posY, mc.thePlayer.posZ+1);
        	c = new BlockPos(mc.thePlayer.posX-2, mc.thePlayer.posY, mc.thePlayer.posZ-1);
        	break;
        }
		if (mc.theWorld.getBlockState(a).getBlock().getMaterial()==Material.air) {
			int slot = mc.thePlayer.inventory.currentItem;			
			if (mc.theWorld.getBlockState(new BlockPos(a.getX(), a.getY()-1, a.getZ())).getBlock().getMaterial()!=Material.air)
				mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(a.getX(), a.getY()+(mc.isSingleplayer() ? +1 : -1), a.getZ()), 1, mc.thePlayer.inventory.getCurrentItem(), a.getX(), a.getY()+(mc.isSingleplayer() ? +1 : -1), a.getZ()));
			if (large) {
				if (mc.theWorld.getBlockState(new BlockPos(b.getX(), b.getY()-1, b.getZ())).getBlock().getMaterial()!=Material.air)
					mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(b.getX(), b.getY()+(mc.isSingleplayer() ? +1 : -1), b.getZ()), 1, mc.thePlayer.inventory.getCurrentItem(), b.getX(), b.getY()+(mc.isSingleplayer() ? +1 : -1), b.getZ()));
				if (mc.theWorld.getBlockState(new BlockPos(c.getX(), c.getY()-1, c.getZ())).getBlock().getMaterial()!=Material.air)
					mc.getNetHandler().addToSendQueue(new C08PacketPlayerBlockPlacement(new BlockPos(c.getX(), c.getY()+(mc.isSingleplayer() ? +1 : -1), c.getZ()), 1, mc.thePlayer.inventory.getCurrentItem(), c.getX(), c.getY()+(mc.isSingleplayer() ? +1 : -1), c.getZ()));
			}
		}
	}
	
	private boolean hasFire() {
		if (mc.thePlayer.getCurrentEquippedItem() != null) {
			ItemStack is = mc.thePlayer.getCurrentEquippedItem();
			if (is!=null) {
				Item item = is.getItem();
				if (Item.getIdFromItem(item) == 259 || Item.getIdFromItem(item) == 51 || Item.getIdFromItem(item) == 385) {
					return true;
				}
			}
		}
		return false;	
	}

	public boolean isLarge() {
		return large;
	}

	public void setLarge(boolean large) {
		this.large = large;
	}		
}








