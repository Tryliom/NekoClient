package neko.module.modules.combat;

import neko.module.modules.util.TimerUtils;
import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class AutoPot extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static int heal=17;
	private double delay = 500.0D;
	private TimerUtils time = new TimerUtils();
	
	public AutoPot() {
		super("AutoPot", Keyboard.KEY_NONE, Category.COMBAT);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onUpdate() {		
		int Slot = getPotFromInventory();
		if (mc.thePlayer.getHealth()<=heal && (this.time.delay((float)this.delay)) && (Slot != -1)) {
			int prevSlot = mc.thePlayer.inventory.currentItem;
			if (Slot < 9) {
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(Slot));
		        mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(mc.thePlayer.rotationYaw, 95.0F, mc.thePlayer.onGround));	
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(Minecraft.thePlayer.inventory.getCurrentItem()));
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(prevSlot));
		        Minecraft.playerController.syncCurrentPlayItem();
		        
		        Minecraft.thePlayer.inventory.currentItem = prevSlot;
			} else {
		        swap(Slot, Minecraft.thePlayer.inventory.currentItem + (Minecraft.thePlayer.inventory.currentItem < 8 ? 1 : -1));
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(Minecraft.thePlayer.inventory.currentItem + (Minecraft.thePlayer.inventory.currentItem < 8 ? 1 : -1)));
		        mc.getNetHandler().addToSendQueue(new C03PacketPlayer.C05PacketPlayerLook(mc.thePlayer.rotationYaw, 95.0F, mc.thePlayer.onGround));	
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(Minecraft.thePlayer.inventory.getCurrentItem()));
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(prevSlot));
		    }
	        this.time.reset();						
		}	
	}
			
	protected void swap(int one, int two) {
		mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, one, two, 2, mc.thePlayer);
	}
	  
	private int getPotFromInventory() {
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() != null) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				if (isSHP(is)) {					
					return i;
				}
			}
		}
		return -1;
	}	

	
	private boolean isSHP(ItemStack stack) {
	    if (stack == null) {
	      return false;
	    }
	    if ((stack.getItem() instanceof ItemPotion))
	    {
	      ItemPotion potion = (ItemPotion)stack.getItem();
	      if (ItemPotion.isSplash(stack.getItemDamage())) {
	        for (Object o : potion.getEffects(stack))
	        {
	          PotionEffect effect = (PotionEffect)o;
	          if (effect.getPotionID() == Potion.heal.id) {
	            return true;
	          }
	        }
	      }
	    }
	    return false;
	  }
	
}
