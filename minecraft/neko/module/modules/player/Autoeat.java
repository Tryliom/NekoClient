package neko.module.modules.player;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemFood;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;

public class Autoeat extends Module {
	
	public Autoeat() {
		super("Autoeat", -1, Category.PLAYER, false);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "";
	}
	
	public void onUpdate() {
		int foodSlot = getFoodSlotInHotbar();
	      if ((foodSlot != -1) && (this.mc.thePlayer.getFoodStats().getFoodLevel() <=19) && (this.mc.thePlayer.isCollidedVertically)) {
	    	  swap(foodSlot, this.mc.thePlayer.inventory.currentItem);
		      this.mc.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(this.mc.thePlayer.inventory.getCurrentItem()));
		      for (int i = 0; i < 32; i++) {
		    	  this.mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(false));
		      }
		      this.mc.thePlayer.stopUsingItem();
		      swap(foodSlot, this.mc.thePlayer.inventory.currentItem);
		      mc.playerController.syncCurrentPlayItem();
	      }
		
	}
	
	private int getFoodSlotInHotbar() {
		try {
		    for (int i = 9; i < 45; i++) {
		      if ((mc.thePlayer.inventoryContainer.getSlot(i) != null) && (mc.thePlayer.inventoryContainer.getSlot(i).getStack() != null) && (mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem() != null) && ((this.mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem() instanceof ItemFood))) {
		        return i;
		      }
		    }
		} catch (Exception e) {}
	    return -1;
	}
	
	protected void swap(int one, int two) {
		this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, one, two, 2, this.mc.thePlayer);
	}
	
}
