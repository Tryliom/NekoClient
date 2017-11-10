package neko.module.modules.combat;

import neko.module.modules.util.TimerUtils;
import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C09PacketHeldItemChange;

public class Autosoup extends Module {
	Minecraft mc = Minecraft.getMinecraft();
	public static int heal=17;
	public static boolean drop=false;
	private double delay = 500.0D;
	private TimerUtils time = new TimerUtils();
	
	public Autosoup() {
		super("Autosoup", -1, Category.COMBAT);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void setValues() {
		this.values = "§6Seuil de vie sensible:§7 "+AutoPot.heal+"\n"
				+ "§6Drop les soupes vide:§7 "+(Autosoup.drop ? "§aActivé" : "§cDésactivé");
	}
	
	public void onUpdate() {		
		int soupSlot = getSoupFromInventory();
		if (mc.thePlayer.getHealth()<=heal && (this.time.delay((float)this.delay)) && (soupSlot != -1)) {
			int prevSlot = this.mc.thePlayer.inventory.currentItem;
			if (soupSlot < 9) {
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(soupSlot));
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(Minecraft.thePlayer.inventory.getCurrentItem()));
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(prevSlot));
		        Minecraft.playerController.syncCurrentPlayItem();
		        
		        Minecraft.thePlayer.inventory.currentItem = prevSlot;
		      } else {
		        swap(soupSlot, Minecraft.thePlayer.inventory.currentItem + (Minecraft.thePlayer.inventory.currentItem < 8 ? 1 : -1));
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(Minecraft.thePlayer.inventory.currentItem + (Minecraft.thePlayer.inventory.currentItem < 8 ? 1 : -1)));
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C08PacketPlayerBlockPlacement(Minecraft.thePlayer.inventory.getCurrentItem()));
		        Minecraft.thePlayer.sendQueue.addToSendQueue(new C09PacketHeldItemChange(prevSlot));
		      }	        
	        this.time.reset();						
		}	
		if (drop)
	        this.dropBowl();
	}
	
	protected void swap(int one, int two) {
		this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, one, two, 2, this.mc.thePlayer);
	}
	  
	private int getSoupFromInventory() {
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() != null) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				if (is!=null) {
					Item item = is.getItem();
					if (Item.getIdFromItem(item) == 282) {
						return i;
					}
				}
			}
		}
		return -1;
	}	
	
	private void dropBowl() {
		for (int i = 0; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() != null) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				Item item = is.getItem();
				if (Item.getIdFromItem(item) == 281) {								
						mc.playerController.windowClick(0, i, 0, 0, mc.thePlayer);
			        	mc.playerController.windowClick(0, -999, 0, 0, mc.thePlayer);					
				}
			}
		}
	}	
	
}
