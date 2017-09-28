package neko.module.modules.combat;

import org.lwjgl.input.Keyboard;

import neko.module.Category;
import neko.module.Module;
import net.minecraft.block.material.Material;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagList;

public class AutoSword extends Module {
	
	public AutoSword() {
		super("AutoSword", -1, Category.COMBAT);
	}
	
	public void onEnabled() {		
		super.onEnabled();
	}
	
	public void onDisabled() {
		super.onDisabled();
	}
	
	public void onClick() {
		boolean good=false;
		if (mc.thePlayer.isSneaking()) {
			good=true;
		}
		try {
			int swordSlot = getSwordFromInventory();
			if (swordSlot!=mc.thePlayer.inventory.currentItem && (swordSlot != -1) && good) {
				int prevSlot = mc.thePlayer.inventory.currentItem;
				swap(swordSlot, prevSlot);		
				mc.playerController.syncCurrentPlayItem();
			}	
		} catch (Exception e) {}
		
	}
	
	private void swap(int one, int two) {
		this.mc.playerController.windowClick(this.mc.thePlayer.inventoryContainer.windowId, one, two, 2, this.mc.thePlayer);
	}
	  
	private int getSwordFromInventory() {
		java.util.ArrayList<Integer> list = new java.util.ArrayList<>();
		for (int i = 9; i < 45; i++) {
			if (mc.thePlayer.inventoryContainer.getSlot(i).getStack() != null) {
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				Item item = is.getItem();
				if (item instanceof ItemSword) {
					list.add(i);
				}
			}
		}
		if (list.isEmpty()) {		
			return -1;
		} else {
			int choose = -1;
			double bestDamage=-1;
			int totalEnchant=-1;
			for (Integer i : list) {
				ItemSword item =(ItemSword) mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem();
				String material = item.getToolMaterialName().toLowerCase();
				int lvl = 0;
				double totalDamage=0;
				int count = 0;
				ItemStack is = mc.thePlayer.inventoryContainer.getSlot(i).getStack();
				if (is.isItemEnchanted()) {
					 NBTTagList NBTenchList = is.getEnchantmentTagList();
		              for (int o = 0; o < NBTenchList.tagCount(); o++) {
		            	  count++;
		            	  short id = NBTenchList.getCompoundTagAt(o).getShort("id");
		                  short niv = NBTenchList.getCompoundTagAt(o).getShort("lvl");
		                  if (Enchantment.func_180306_c(id).getName().equalsIgnoreCase("enchantment.damage.all")) {
		                	  lvl = niv;
		                  }
		              }
				}
				if (material.equalsIgnoreCase("emerald")) {
					totalDamage+=7;
				} else if (material.equalsIgnoreCase("iron")) {
					totalDamage+=6;
				} else if (material.equalsIgnoreCase("gold")) {
					totalDamage+=4;
				} else if (material.equalsIgnoreCase("stone")) {
					totalDamage+=5;
				} else if (material.equalsIgnoreCase("wood")) {
					totalDamage+=4;
				}
				totalDamage+=lvl*1.25;
				if (bestDamage<totalDamage || (bestDamage==totalDamage && count>totalEnchant)) {
					bestDamage=totalDamage;
					choose=i;
					totalEnchant=count;
				}
			}
			return choose;
		}
	}		
	
}
