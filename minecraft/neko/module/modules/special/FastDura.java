package neko.module.modules.special;

import java.util.ArrayList;

import neko.module.Category;
import neko.module.Module;
import neko.utils.Utils;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.network.play.client.C02PacketUseEntity.Action;

public class FastDura extends Module {
	public static Minecraft mc = Minecraft.getMinecraft();
	public static ArrayList<Integer> list = new ArrayList<>();
	public FastDura() {
		super("FastDura", -1, Category.Special);
	}
	
	public void onEnabled() {	
		if (u.isLock(this.getName()))
			return;
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}	
	
	public void setValues() {
		this.values = "";
	}
	
	public void onClick() {
		if (mc.pointedEntity!=null) {
			doDura(mc.pointedEntity);
		}
	}
	
	public void onUpdate() {
		if (u.isLock(this.getName())) {
			boolean display = u.display;
			u.display=false;
			this.isToggled=false;
			u.display=display;
			u.addWarn(this.getName());
			return;
		}			
	}
	
	
	// Fonctions swap et attack
	
	public static void doDura(Entity en) {
		int itemSlot;
		// 36 à 44 (En les comptant avec
		
		if (mc.thePlayer.getCurrentEquippedItem()!=null ? mc.thePlayer.getCurrentEquippedItem().getItem() instanceof ItemTool : false) {
			int i = getItemLess();
			int j = mc.thePlayer.inventory.currentItem;
			if (i!=-1) {
				swap(j, i);
				attack(en, false);
				attack(en, true);
				swap(j, i);
				attack(en, false);
				attack(en, true);
			} else {
				attack(en, false);
				attack(en, true);
			}
		} else {
			int i = getItemBest();
			int j = mc.thePlayer.inventory.currentItem;
			if (i!=-1) {				
				attack(en, false);
				attack(en, true);				
				swap(j, i);
				attack(en, false);
				attack(en, true);
				// Change seulement les deux item de places
				swap(j, i);
			} else {
				attack(en, false);
				attack(en, true);
			}
		}
		
	}
	
	public static void swap(int prochainItem, int ancienItem) {
		mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, ancienItem, prochainItem, 2, mc.thePlayer);
	}
	
	public static int getItemLess() {
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i) != null && mc.thePlayer.inventory.currentItem!=i) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				if (is!=null) {
					Item item = is.getItem();
					if (item instanceof ItemTool && !(item instanceof ItemSword)) {
						return i;
					} else if (!(item instanceof ItemSword)) {
						return i;
					}
				} else if (mc.thePlayer.inventory.currentItem!=i)
					return i;
			} else {
				return i;
			}
		}
		return -1;
	}
	
	public static int getItemBest() {
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i) != null && mc.thePlayer.inventory.currentItem!=i) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				if (is!=null) {
					Item item = is.getItem();
					if (item instanceof ItemSword) {
						return i;
					}
				}
			}
		}		
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i) != null && mc.thePlayer.inventory.currentItem!=i) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				if (is!=null) {
					Item item = is.getItem();
					if (item instanceof ItemSword) {
						return i;
					} else if (item instanceof ItemTool) {
						return i;
					}
				}
			}
		}
		return -1;
	}	
	
	public static void attack(Entity en, boolean crit) {
		if (crit || (crit && Utils.isToggle("Crit"))) 
			Utils.crit();		
		mc.getNetHandler().addToSendQueue(new C02PacketUseEntity(en, Action.ATTACK));		
	}
	
	
	
	
	
	
	

}
